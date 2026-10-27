/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program DiscreteDistribution.java that takes an integer command-line argument m,
 *  followed by a sequence of positive integer command-line arguments a1,a2,…,an
 *  and prints m random indices, choosing each index i
 *  with probability proportional to ai.
 *
 *  ~/hello-java $ javac -d out introcs/DiscreteDistribution.java
 *
 *  ~/hello-java $ java -cp out DiscreteDistribution 30 10 10 10 20 40
 **************************************************************************** */
import java.util.Random;
import org.apache.arrow.memory.*;
import org.apache.arrow.vector.*;
import org.apache.arrow.vector.types.*;
import org.apache.arrow.vector.types.pojo.*;
import java.nio.channels.Channels;
import org.apache.arrow.vector.ipc.ArrowStreamWriter;

public class DiscreteDistribution {

    static int searchSorted(long value, long[] sortedArray) {
        int i = 0;
        int j = sortedArray.length;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (sortedArray[mid] >= value) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    static int[] discreteDistribution(int size, long[] cumWeights, Random rng) {
        int[] disDist  = new int[size];
        long last      = cumWeights[cumWeights.length - 1];
        for (int k = 0; k < size; k++) {
            long p     = 1 + rng.nextLong(last);
            disDist[k] = searchSorted(p, cumWeights);
        }
        return disDist;
    }

    static Field col(String name, ArrowType type) {
        return new Field(name, FieldType.notNullable(type), null);
    }

    public static void main(String[] args) {
        int n             = args.length;
        if (n < 2) {
            System.err.println("Uso: java DiscreteDistribution m a1 a2 ... an");
            System.exit(1);
        }
        int m             = Integer.parseInt(args[0]);
        if (m < 0) {
            System.err.println("Error: m must be non-negative");
            System.exit(1);
        }
        long[] cumWeights = new long[n];
        for (int i = 1; i < n; i++) {
            cumWeights[i] = cumWeights[i - 1] + Long.parseLong(args[i]);
        }
        Random rng        = new Random(); 
        int[] sample      = discreteDistribution(m, cumWeights, rng);
        Schema schema     = new Schema(java.util.Arrays.asList(
            col("sample", new ArrowType.Int(32, true))
        ));
        try(BufferAllocator alloc = new RootAllocator();
            VectorSchemaRoot root = VectorSchemaRoot.create(schema, alloc)) {
            IntVector sVec        = (IntVector) root.getVector("sample");
            sVec.allocateNew(m);
            for (int k = 0; k < m; k++) {
                sVec.set(k, sample[k]);
            }
            root.setRowCount(m);
            try (ArrowStreamWriter writer =
                new ArrowStreamWriter(root, null, Channels.newChannel(System.out))) {
                writer.start();
                writer.writeBatch();
                writer.end();
            } catch (java.io.IOException e) {
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }
            System.out.flush();
        }
    }
}

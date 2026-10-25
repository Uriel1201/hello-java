/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program Birthday.java that takes two integer command-line arguments n
 *  and trials and performs the following experiment, trials times:
 *
 *  - Choose a birthday for the next person, uniformly at random between 0 and n−1.
 *  - Have that person enter the room.
 *  - If that person shares a birthday with someone else in the room, stop; otherwise repeat.
 *
 *  In each experiment, count the number of people that enter the room.
 *  Print a table that summarizes the results (the count i, the number of times that
 *  exactly i people enter the room, and the fraction of times that i or fewer people enter the room)
 *  for each possible value of i from 1 until the fraction reaches (or exceeds) 50%.
 *
 *  ~/hello-java$ javac -cp "lib/*:lib-arrow/*" -d out introcs/Birthday.java
 *
 *  ~/hello-java$ java --add-opens=java.base/java.nio=ALL-UNNAMED -cp "out:lib/*:lib-arrow/*" Birthday 365 1000000
 **************************************************************************** */

import org.apache.arrow.memory.*;
import org.apache.arrow.vector.*;
import org.apache.arrow.vector.types.*;
import org.apache.arrow.vector.types.pojo.*;
import java.nio.channels.Channels;
import org.apache.arrow.vector.ipc.ArrowStreamWriter;

public class Birthday {

    static int[] birthdaySimulation(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (trials < 0) {
            throw new IllegalArgumentException("trials must be non-negative");
        }
        int[] s = new int[n + 2];
        for (int i = 0; i < trials; i++) {
            boolean[] birthday = new boolean[n];
            int count          = 0;
            for (int j = 0; j < n + 1; j++) {
                int p = (int) (Math.random() * n);
                count++;
                if (birthday[p]) break;
                birthday[p] = true;
            }
            s[count]++;
        }
        return s;
    }

    static Field col(String name, ArrowType type) {
        return new Field(name, FieldType.notNullable(type), null);
    }

    public static void main(String[] args) {
        int n         = Integer.parseInt(args[0]);
        int trials    = Integer.parseInt(args[1]);
        int[] s       = birthdaySimulation(n, trials);
        Schema schema = new Schema(java.util.Arrays.asList(
            col("k",         new ArrowType.Int(32, true)),
            col("frequency", new ArrowType.Int(32, true)),
            col("p_k",       new ArrowType.FloatingPoint(FloatingPointPrecision.DOUBLE))
        ));
        try(BufferAllocator alloc = new RootAllocator();
            VectorSchemaRoot root = VectorSchemaRoot.create(schema, alloc)) {
            IntVector kVec    = (IntVector) root.getVector("k");
            IntVector sVec    = (IntVector) root.getVector("frequency_k");
            Float8Vector pVec = (Float8Vector) root.getVector("p_k");
            double f          = 0.0;
            int row           = 0;
            kVec.allocateNew(n + 1);
            sVec.allocateNew(n + 1);
            pVec.allocateNew(n + 1);
            for (int k = 1; k < n + 2; k++) {
                f += 1.0 * s[k] / trials;
                kVec.set(row, k);
                sVec.set(row, s[k]);
                pVec.set(row, f);
                row++;
                if (f >= 0.5) break;
            }
            root.setRowCount(row);
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

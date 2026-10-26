/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program DiscreteDistribution.java that takes an integer command-line argument m, 
 *  followed by a sequence of positive integer command-line arguments a1,a2,…,an
 *  and prints m random indices (separated by whitespace), choosing each index i
 *  with probability proportional to ai.
 *
 *  ~/hello-java $ javac -d out introcs/arrays/DiscreteDistribution.java
 *
 *  ~/hello-java $ java -cp out DiscreteDistribution 30 10 10 10 20 40
 *  1  5  5  4  3  2  2  4  4  5  4  3  4  5  4  4  3  5  5  5  5  5  5  5  5  5  5  1  5  4  
 **************************************************************************** */

public class DiscreteDistribution {
   
    public static void main(String[] args) {
        
        int n = args.length;
        int m = Integer.parseInt(args[0]);
        
        int[] s = new int[n];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + Integer.parseInt(args[i]);
        }
        
        for (int k = 0; k < m; k++) {
            int p = (int) (Math.random() * s[n - 1]);
            int i = 0;
            int j = n;
            while (i < j) {
                int mid = i + (j - i) / 2;
                if (s[mid] <= p) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}

/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program ShannonEntropy.java that takes a command-line integer m; 
 *  reads a sequence of integers between 1 and m from standard input; 
 *  and prints the Shannon entropy to standard output, with 4 digits 
 *  after the decimal point.
 *
 *  ~/Desktop/io> javac-introcs ShannonEntropy.java
 *
 *  ~/Desktop/io> cat fair-coin.txt
 *  1 1 1 1 2 1 2 1 1 2
 *  2 2 2 2 1 2 1 2 2 1
 *
 *  ~/Desktop/io> java-introcs ShannonEntropy 2 < fair-coin.txt
 *  1.0000
 **************************************************************************** */

public class ShannonEntropy {
  
    public static void main(String[] args) {
       
      int m = Integer.parseInt(args[0]);
        int[] f = new int[m + 1];
        int n = 0;

        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            if (x < m + 1 && x > 0) {
                f[x]++;
                n++;
            }
        }
        double h = 0.0;
        for (int i = 1; i < m + 1; i++) {
            if (f[i] > 0) {
                double p = 1.0 * f[i] / n;
                double arg = -p * Math.log(p) / Math.log(2);
                h += arg;
            }
        }
        StdOut.printf("%.4f", h);
    }
}

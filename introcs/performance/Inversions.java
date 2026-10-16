/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Given an array of integers, a pair of elements a[i] and a[j] 
 *  are inverted if i < j and a[i] > a[j]. 
 *  For example, the array a[] has 1 inversion and the array b[] has 4 inversions.
 *
 *  Write a program Inversions.java that implements the following API:
 *
 *  public class Inversions {
 *
 *      // Return the number of inversions in the permutation a[].
 *      public static long count(int[] a)
 *
 *      // Return a permutation of length n with exactly k inversions.
 *      public static int[] generate(int n, long k)
 *
 *      // Takes an integer n and a long k as command-line arguments,
 *      // and prints a permutation of length n with exactly k inversions.
 *      public static void main(String[] args)
 *  }
 *
 *  ~/hello-data> javac -cp lib/stdlib.jar -d out introcs/performance/Inversions.java
 *
 *  ~/hello-data> java -cp out:lib/stdlib.jar Inversions 10 45
 *  
 *
 *  ~/hello-data> java -cp out:lib/stdlib.jar Inversions 10 20
 *
 **************************************************************************** */

public class Inversions {

    private static long merge(int[] a, int[] b, int first, int partition, int last) {

        long inversions = 0;

        for (int h = first; h <= last; h++) {
            b[h] = a[h];
        }

        int i = first;
        int j = partition + 1;

        for (int h = first; h <= last; h++) {
            if (i > partition) {
                a[h] = b[j];
                j++;
            }
            else if (j > last) {
                a[h] = b[i];
                i++;
            }
            else if (b[j] < b[i]) {
                a[h] = b[j];
                inversions += (partition + 1 - i);
                j++;
            }
            else {
                a[h] = b[i];
                i++;
            }
        }

        return inversions;
    }

    private static long mergesort(int[] a, int[] b, int first, int last) {

        long count = 0;

        if (last <= first) return 0;

        int partition = first + (last - first) / 2;

        count += mergesort(a, b, first, partition);
        count += mergesort(a, b, partition + 1, last);
        count += merge(a, b, first, partition, last);

        return count;
    }

    public static long count(int[] a) {

        int n = a.length;
        int[] c = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            c[i] = a[i];
        }

        return mergesort(c, b, 0, n - 1);
    }

    public static int[] generate(int n, long k) {

        int[] a = new int[n];
        int i = 0;
        
        long j = 0;
        while (i < n) {

            int p = n - 1 - i;

            if (k - j >= p) {
                a[i] = p;
                i++;
                j += p;
            }
            else {
                for (int h = i; h < n; h++) {
                    a[h] = h - i;
                }

                int m = (int) (k - j);
                if (m > 0) {
                    int[] b = new int[m];

                    for (int h = 0; h < m; h++) {
                        b[h] = a[n - m - 1 + h];
                    }

                    int q = a[n - 1];
                    a[n - m - 1] = q;

                    for (int h = n - m; h < n; h++) {
                        a[h] = b[m - n + h];
                    }
                }
                break;
            }
        }
        return a;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);

        if (k > (long) n * (n - 1) / 2 || k < 0) {
            StdOut.print("Error: k is outside the allowed limits.");
        }
        else {
            int[] a = generate(n, k);

            for (int i = 0; i < n; i++) {
                StdOut.print(a[i] + " ");
            }
            StdOut.print("Number of inversions: " + count(a));
        }
    }
}

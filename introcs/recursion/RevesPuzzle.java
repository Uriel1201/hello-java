/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program RevesPuzzle.java that takes an integer command-line argument n 
 *  and prints a solution to Reve’s puzzle. 
 *  Assume that the the discs are labeled in increasing order of size from 1 to n 
 *  and that the poles are labeled A, B, C, and D, with A representing the starting 
 *  pole and D representing the destination pole.
 *
 *  ~/hello-data> javac -d out introcs/recursion/RevesPuzzle.java
 *
 *  ~/hello-data> java -cp out RevesPuzzle 12
 *  Move disc 1 from A to C
 *  Move disc 2 from A to B
 *  Move disc 3 from A to D
 *  Move disc 2 from B to D
 *  Move disc 1 from C to D
 *  Move disc 4 from A to C
 *  Move disc 5 from A to B
 *  Move disc 4 from C to B
 *  Move disc 1 from D to C
 *  Move disc 2 from D to A
 *  Move disc 3 from D to B
 *  Move disc 2 from A to B
 *  Move disc 1 from C to B
 *  Move disc 6 from A to C
 *  Move disc 7 from A to D
 *  Move disc 6 from C to D
 *  Move disc 8 from A to C
 *  Move disc 6 from D to A
 *  Move disc 7 from D to C
 *  Move disc 6 from A to C
 *  Move disc 1 from B to A
 *  Move disc 2 from B to C
 *  Move disc 3 from B to D
 *  Move disc 2 from C to D
 *  Move disc 1 from A to D
 *  Move disc 4 from B to A
 *  Move disc 5 from B to C
 *  Move disc 4 from A to C
 *  Move disc 1 from D to A
 *  Move disc 2 from D to B
 *  Move disc 3 from D to C
 *  Move disc 2 from B to C
 *  Move disc 1 from A to C
 *  Move disc 9 from A to B
 *  Move disc 10 from A to D
 *  Move disc 9 from B to D
 *  Move disc 11 from A to B
 *  Move disc 9 from D to A
 *  Move disc 10 from D to B
 *  Move disc 9 from A to B
 *  Move disc 12 from A to D
 *  Move disc 9 from B to D
 *  Move disc 10 from B to A
 *  Move disc 9 from D to A
 *  Move disc 11 from B to D
 *  Move disc 9 from A to B
 *  Move disc 10 from A to D
 *  Move disc 9 from B to D
 *  Move disc 1 from C to D
 *  Move disc 2 from C to B
 *  Move disc 3 from C to A
 *  Move disc 2 from B to A
 *  Move disc 1 from D to A
 *  Move disc 4 from C to D
 *  Move disc 5 from C to B
 *  Move disc 4 from D to B
 *  Move disc 1 from A to D
 *  Move disc 2 from A to C
 *  Move disc 3 from A to B
 *  Move disc 2 from C to B
 *  Move disc 1 from D to B
 *  Move disc 6 from C to D
 *  Move disc 7 from C to A
 *  Move disc 6 from D to A
 *  Move disc 8 from C to D
 *  Move disc 6 from A to C
 *  Move disc 7 from A to D
 *  Move disc 6 from C to D
 *  Move disc 1 from B to C
 *  Move disc 2 from B to D
 *  Move disc 3 from B to A
 *  Move disc 2 from D to A
 *  Move disc 1 from C to A
 *  Move disc 4 from B to C
 *  Move disc 5 from B to D
 *  Move disc 4 from C to D
 *  Move disc 1 from A to C
 *  Move disc 2 from A to B
 *  Move disc 3 from A to D
 *  Move disc 2 from B to D
 *  Move disc 1 from C to D
 **************************************************************************** */

public class RevesPuzzle {

    private static void reves(int n, String f, String t1, String t2, String to) {
        if (n <= 0) return;
        else {
            int k = (int) Math.round(n + 1 - Math.sqrt(2 * n + 1));
            reves(k, f, to, t1, t2);
            hanoi(n, k, f, t1, to);
            reves(k, t2, f, t1, to);
        }
    }

    private static void hanoi(int n, int k, String f, String t, String to) {
        if (n <= k) return;
        else {
            hanoi(n - 1, k, f, to, t);
            System.out.println("Move disc " + n + " from " + f + " to " + to);
            hanoi(n - 1, k, t, f, to);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        reves(n, "A", "B", "C", "D");
    }
}

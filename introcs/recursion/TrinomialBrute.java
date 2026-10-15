/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program TrinomialBrute.java that takes two integer command-line 
 *  arguments n and k and computes the corresponding trinomial coefficient. 
 *  The trinomial coefficient T(n,k) is the coefficient of x^(n+k) in the expansion of 
 *  (1+x+x2)^n
 *
 *  ~/hello-data> javac -d out introcs/recursion/TrinomialBrute.java
 *
 *  ~/hello-data> java -cp out TrinomialBrute 24 12
 **************************************************************************** */

public class TrinomialBrute {

    public static long trinomial(int n, int k) {
        if (n == 0 && k == 0) return 1;
        else if (k < -n || n < k) return 0;
        else return trinomial(n - 1, k - 1) + trinomial(n - 1, k) + trinomial(n - 1, k + 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        System.out.println(trinomial(n, k));
    }
}

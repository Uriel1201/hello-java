/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program Divisors.java to compute the greatest common divisor 
 *  and related functions on integers:
 *
 *  ~/hello-data> javac -d out introcs/functions/Divisors.java
 *
 *  ~/hello-data> java -cp out Divisors 2018 8
 *  gcd(2018,8)                = 2
 *  lcm(2018,8)                = 8072
 *  areRelativelyPrime(2018,8) = false
 *  totient(2018)              = 1008
 *  totient(8)                 = 4
 **************************************************************************** */

public class Divisors {

    public static int gcd(int a, int b) {
        if (b < 0) b = -b;
        if (a < 0) a = -a;
        
        if (b > a) {
            int s = b;
            b = a;
            a = s;
        }
        
        if (b == 0) {
            return a;
        }
            
        else {
            while (b > 0) {
                int t = a;
                a = b;
                b = t % a;
            }
            
            return a;
        }
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) 
            return 0;
        else {
            return (Math.abs(a) / gcd(a, b) * Math.abs(b));
        }
    }

    public static boolean areRelativelyPrime(int a, int b) {
        int g = gcd(a, b);
        boolean rp = (g == 1);
       
        return rp;
    }

    public static int totient(int n) {
        int t = 0;
        int m = 1;
        
        while (m <= n) {
            if (areRelativelyPrime(m, n)) t++;
            m++;
        }
        
        return t;
    }

    public static void main(String[] args) {
       
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        
        System.out.println("gcd(" + x + "," + y + ")                = " + gcd(x, y));
        System.out.println("lcm(" + x + "," + y + ")                = " + lcm(x, y));
        System.out.println("areRelativelyPrime(" + x + "," + y + ") = " + areRelativelyPrime(x, y));
        System.out.println("totient(" + x + ")                 = " + totient(x));
        System.out.println("totient(" + y + ")                 = " + totient(y));
    }
}

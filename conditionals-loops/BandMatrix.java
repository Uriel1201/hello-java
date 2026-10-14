/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  Write a program BandMatrix.java that takes two integer command-line arguments 
 *  n and width and prints an n-by-n pattern, 
 *  with a zero (0) for each element whose distance from the main diagnonal is 
 *  strictly more than width, and an asterisk (*) for each entry that is not. 
 *  Here, distance means the minimum number of cells you have to move 
 *  (either left, right, up, or down) to reach the main diagonal.
 **************************************************************************** */

public class BandMatrix {
    
    public static void main(String[] args) {

        int n     = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);

        if (n >= 0 && width >= 0) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int k = j - i;
                    if (Math.abs(k) > width) {
                        System.out.print("0  ");
                    } else {
                        System.out.print("*  ");
                    }
                }
                System.out.println();
            }

        } else {
            System.out.println("n an width must be non-negative.");
        }
    }
}

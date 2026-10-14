/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *   Write a program RandomWalker.java that takes an integer command-line argument r 
 *   and simulates the motion of a random walker for r steps. 
 *   After each step, print the location of the random walker, 
 *   treating the lamp post as the origin (0, 0).
 **************************************************************************** */

public class RandomWalker {
    
    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        
        if (r >= 0) {
            
            int i = 0;
            int j = 0;
            System.out.println("(" + i + "," + j + ")");

            int manhattan = 0;
            int steps = 0;
            
            while (manhattan < r) {
                
                double p = Math.random();
                if (p < 0.25) {
                    i++;
                }
                else if (p < 0.5) {
                    i--;
                }
                else if (p < 0.75) {
                    j++;
                }
                else {
                    j--;
                }
                
                System.out.println("(" + i + "," + j + ")");
                int ai = Math.abs(i);
                int aj = Math.abs(j);
                manhattan = ai + aj;
                steps++;
            }
            System.out.println("Steps = " + steps);
        }
        else {
            System.out.println("r must to be no negative");
        }
    }
}

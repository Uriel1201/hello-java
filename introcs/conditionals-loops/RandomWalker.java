/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program RandomWalker.java that takes an integer command-line argument r 
 *  and simulates the motion of a random walk until the random walker is at Manhattan distance r 
 *  from the starting point. 
 *  Print the coordinates at each step of the walk (including the starting and ending points), 
 *  treating the starting point as (0, 0). 
 *  Also, print the total number of steps taken.
 *
 *  ~/hello-java> javac -d out introcs/conditionals-loops/RandomWalker.java
 *
 *  ~/hello-java> java -cp out RandomWalker 10
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
            System.out.println("r must be non-negative");
        }
    }
}

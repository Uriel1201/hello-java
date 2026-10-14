/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program Birthday.java that takes two integer command-line arguments n 
 *  and trials and performs the following experiment, trials times:
 *
 *  -Choose a birthday for the next person, uniformly at random between 0 and n−1.
 *  -Have that person enter the room.
 *  -If that person shares a birthday with someone else in the room, stop; otherwise repeat.
 *
 *  In each experiment, count the number of people that enter the room. 
 *  Print a table that summarizes the results (the count i, the number of times that 
 *  exactly i people enter the room, and the fraction of times that i or fewer people enter the room) 
 *  for each possible value of i from 1 until the fraction reaches (or exceeds) 50%.
 *
 *  ~/Desktop/arrays> java Birthday 365 1000000
 *  1	0	    0.0
 *  2	2710	0.00271
 *  3	5547	0.008257
 *  4	8105	0.016362
 *  5	10776	0.027138
 *  6	13413	0.040551
 *  7	15782	0.056333
 *  8	17816	0.074149
 *  9	20283	0.094432
 *  10	22297	0.116729
 *  11	24105	0.140834
 *  12	26013	0.166847
 *  13	27247	0.194094
 *  14	28405	0.222499
 *  15	29873	0.252372
 *  16	30447	0.282819
 *  17	31445	0.314264
 *  18	31837	0.346101
 *  19	32559	0.37866
 *  20	32244	0.410904
 *  21	32357	0.443261
 *  22	32020	0.475281
 *  23	31667	0.506948
 **************************************************************************** */

public class Birthday {

    public static void main(String[] args) {
       
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int[] s = new int[n + 2];
        
        for (int i = 0; i < trials; i++) {
            boolean[] birthday = new boolean[n];
            int count = 0;
            
            for (int j = 0; j < n + 1; j++) {
                int p = (int) (Math.random() * n);
                count++;
                if (birthday[p]) break;
                birthday[p] = true;
            }

            s[count]++;
        }
        
        double f = 0.0;
        
        for (int k = 1; k < m; k++) {
            f += 1.0 * s[k] / trials;
            System.out.println(k + "  " + s[k] + "  " + f);
            if (f >= 0.5) break;

        }
    }
}

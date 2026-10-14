/** *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  Write a program Minesweeper.java that takes three integer command-line arguments m, 
 *  n, and k and prints an m-by-n grid of cells with k mines, using asterisks for mines 
 *  and integers for the neighboring mine counts (with two space characters between each cell). 
 *  To do so,
 *  - Generate an m-by-n grid of cells, with exactly k of the mn cells containing mines, uniformly at random.
 *  - For each cell not containing a mine, count the number of neighboring mines 
 *    (above, below, left, right, or diagonal).
 *
 *  ~/Desktop/arrays> java Minesweeper 9 9 10
 *  0  1  *  1  0  0  0  1  *  
 *  1  3  2  2  0  0  0  1  1  
 *  *  2  *  1  0  0  1  1  1  
 *  1  2  2  2  1  0  1  *  1  
 *  0  1  2  *  1  0  1  1  1  
 *  1  2  *  3  3  1  1  0  0  
 *  1  *  3  *  2  *  1  0  0  
 *  1  1  2  1  2  1  1  0  0  
 *  0  0  0  0  0  0  0  0  0 
 **************************************************************************** */

public class Minesweeper {
    
    public static void main(String[] args) {
        
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int t = m * n;
        
        boolean[] shuffle = new boolean[t];
        for (int h = 0; h < k; h++)
            shuffle[h] = true;
        
        for (int h = 0; h < t; h++) {
            int p = h + (int) (Math.random() * (t - h));
            boolean s = shuffle[p];
            shuffle[p] = shuffle[h];
            shuffle[h] = s;
        }
        
        boolean[][] mines = new boolean[m + 2][n + 2];
        for (int h = 1; h < m + 1; h++) {
            for (int i = 1; i < n + 1; i++) {
                mines[h][i] = shuffle[n * (h - 1) + i - 1];
            }
        }
        
        int[][] count = new int[m + 2][n + 2];
        
        int row = 1;
        while (row < m + 1) {
            int col = 1;
            while (col < n + 1) {
                for (int i = row - 1; i < row + 2; i++)
                    for (int j = col - 1; j < col + 2; j++)
                        if (mines[i][j]) count[row][col]++;
                col++;
            }
            row++;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (mines[i][j]) System.out.print("*  ");
                else System.out.print(count[i][j] + "  ");
            }
            System.out.println();
        }
    }
}

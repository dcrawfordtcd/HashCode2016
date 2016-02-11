/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Dan
 */
public class HashCode2016 {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("tc1.txt");
        Scanner scanner = new Scanner(file);
        int R = scanner.nextInt();
        int C = scanner.nextInt();
        boolean[][] grid = new boolean[R][C];
        for(int i = 0; i < R; i++)
        {
            char[] line = scanner.next().toCharArray();
            for(int j = 0; j < C; j++)
                if(line[j] == '#')
                    grid[i][j] = true;
        }
        //printGrid(grid);
        //printGridWithIndexes(grid);
        
    }
        
    public static void printGrid(boolean[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        for(int i = 0; i < R; i++)
        {
            for(int j = 0; j < C; j++)
            {
                if(grid[i][j])
                    System.out.print("#");
                else
                    System.out.print(".");
                
            }
            System.out.println("");
        }
    }
    
    public static void printGridWithIndexes(boolean[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        System.out.print("  ");
        for(int i = 0; i < C; i++)
            System.out.print(" " + i + " ");
        System.out.println("");
        for(int i = 0; i < R; i++)
        {
            System.out.print(i + " ");
            for(int j = 0; j < C; j++)
            {
                if(grid[i][j])
                    System.out.print(" # ");
                else
                    System.out.print(" . ");
                
            }
            System.out.println("");
        }
    }
    
}

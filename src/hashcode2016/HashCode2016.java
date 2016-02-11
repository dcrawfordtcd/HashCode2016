/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dan
 */
public class HashCode2016 {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileWriter fileWriter = new FileWriter("output.txt");
        int inputFile = 1;
        File file;
        switch(inputFile)
        {
            case 0 : file = new File("busy_day.in");
                break;
            case 1 : file = new File("mother_of_all_warehouses.in");
                break;
            default : file = new File("redundancy.in");
                break;
        }
        
        Scanner scanner = new Scanner(file);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int D = scanner.nextInt();
        int deadline = scanner.nextInt();
        int maxLoad = scanner.nextInt();
        int P = scanner.nextInt();
        int[] productWeights = new int[P];
        for(int i = 0; i < P; i++)
        {
            productWeights[i] = scanner.nextInt();
        }
        int W = scanner.nextInt();
        
        for(int i = 0; i < W; i++)
        {
            int warehouseR = scanner.nextInt();
            int warehouseC = scanner.nextInt();
            int[] warehouseProducts = new int[P];
            for(int j = 0; j < P; j++)
                warehouseProducts[j] = scanner.nextInt();
        }
        
        int C = scanner.nextInt();
        for(int i = 0; i < C; i++)
        {
            int deliveryRow = scanner.nextInt();
            int deliveryColumn = scanner.nextInt();
            int numberOfItems = scanner.nextInt();
            for(int j = 0; j < numberOfItems; j++)
            {
                int productType = scanner.nextInt();
            }
        }
        System.out.println("I think that read in correctly...");
        
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

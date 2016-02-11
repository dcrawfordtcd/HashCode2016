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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

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
        int inputFile = 3;
        File file;
        switch(inputFile)
        {
            case 0 : file = new File("busy_day.in");
                break;
            case 1 : file = new File("mother_of_all_warehouses.in");
                break;
            case 2 : file = new File("redundancy.in");
                break;
            default : file = new File("test_case.in");
                break;
        }
        
        Scanner scanner = new Scanner(file);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int D = scanner.nextInt();
        Drone drones[] = new Drone[D];
        int deadline = scanner.nextInt();
        int maxLoad = scanner.nextInt();
        int P = scanner.nextInt();
        int[] productWeights = new int[P];
        for(int i = 0; i < P; i++)
        {
            productWeights[i] = scanner.nextInt();
        }
        ItemWeights.itemWeights = productWeights;
        int W = scanner.nextInt();
        Warehouse warehouses[] = new Warehouse[W];
        for(int i = 0; i < W; i++)
        {
            int warehouseR = scanner.nextInt();
            int warehouseC = scanner.nextInt();
            int[] warehouseProducts = new int[P];
            for(int j = 0; j < P; j++)
                warehouseProducts[j] = scanner.nextInt();
            warehouses[i] = new Warehouse(warehouseR, warehouseC, warehouseProducts, i);
        }
        
        for(int i = 0; i < D; i++){
            drones[i] = new Drone(i, 0, maxLoad, warehouses[0].getRow(), warehouses[0].getColumn());
        }
        
        int C = scanner.nextInt();
        
        /* Stack of orders  */
        LinkedList<Order> orders = new LinkedList<>();
        
        for(int i = 0; i < C; i++)
        {
            int deliveryRow = scanner.nextInt();
            int deliveryColumn = scanner.nextInt();
            int numberOfItems = scanner.nextInt();
            ArrayList<Integer> items = new ArrayList<>();
            for(int j = 0; j < numberOfItems; j++)
            {
                int productType = scanner.nextInt();
                items.add(productType);
            }
            Order order = new Order(i, deliveryRow, deliveryColumn, items, productWeights, warehouses[0]);
            orders.push(order);
        }
        
        //System.out.println("I think that read in correctly...");
        
        for(int i = 0; i < deadline; i++)
        {
            for(Drone theDrone : drones)
            {
                theDrone.incrementTurn();
                if(theDrone.available)
                {
                    if(!orders.isEmpty())
                    {
                        Order topOrder = orders.pop();
                        Order remainderOrder = theDrone.processOrder(topOrder, warehouses);
                        if(remainderOrder != null)
                        orders.push(remainderOrder);
                    }
                }
            }
        }
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

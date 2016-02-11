/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

/**
 *
 * @author Shane
 */
public class Drone {
    private int idNumber;
    private int[] inventory;
    private int currentWeight;
    private static int carryingCapacity;
    private int rowPos;
    private int colPos;

    
    public Drone(int idNumber, int numberOfItems, int capacity, int startingRow, int startingCol){
        this.idNumber = idNumber;
        inventory = new int[numberOfItems];
        currentWeight = 0;
        carryingCapacity = capacity;
        
        this.rowPos = startingRow;
        this.colPos = startingCol;
    }
    
    public boolean load(int numberOfItems, int itemType, int itemWeight){
        if(itemWeight*numberOfItems > carryingCapacity){
            return false;
        }
        inventory[itemType] += numberOfItems;
        currentWeight+= itemWeight*numberOfItems;
        return true;
    }
    
    public boolean unload(int numberOfItems, int itemType, int itemWeight){
        if(inventory[itemType]-numberOfItems <0){
            return false;
        }
        inventory[itemType]-=numberOfItems;
        return true;
    }
    
    
    // Returns int of distance to [rowB, colB]
    public int getDistance(int rowB, int colB){
        double form1 = (this.rowPos - rowB) * (this.rowPos - rowB);
        double form2 = (this.colPos - colB) * (this.colPos - colB);
        
        return (int)Math.ceil( ( Math.sqrt(form1 + form2) ) );
    }
}

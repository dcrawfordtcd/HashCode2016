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
    
    public Drone(int idNumber, int numberOfItems, int capacity){
        this.idNumber = idNumber;
        inventory = new int[numberOfItems];
        currentWeight = 0;
        carryingCapacity = capacity;
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
}

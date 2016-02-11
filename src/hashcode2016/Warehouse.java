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
public class Warehouse {
    private int row;
    private int column;
    private int[] inventory;
    private int idNumber;
    
    public Warehouse(int row, int column,  int[] inventory, int id){
        this.row = row;
        this.column = column;
        this.inventory = inventory;
        idNumber = id;
    }
    
    public boolean loadOntoDrone(int itemId, int quantity){
        if(inventory[itemId]-quantity < 0){
            return false;
        }
        inventory[itemId]-=quantity;
        return true;
    }
    
    public void unloadFromDrone(int itemId, int quantity){
        inventory[itemId] += quantity;
    }
    
    public int checkQuantity(int itemId){
        return inventory[itemId];
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
}

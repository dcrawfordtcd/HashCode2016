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
    
    public Warehouse(int row, int column,  int[] inventory){
        this.row = row;
        this.column = column;
        this.inventory = inventory;
    }
}

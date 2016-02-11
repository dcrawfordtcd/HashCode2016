
package hashcode2016;

import java.util.ArrayList;

/**
 *
 * @author aran
 */
public class Order {
    public int id;
    public int row, col;
    public ArrayList<Integer> items;
    public int priority;
    
    public Order(int id, int row, int col, ArrayList<Integer> items,
            int[] productWeights, Warehouse firstWarehouse, int maxLoad)
    {
        this.id = id;
        this.row = row;
        this.col = col;
        this.items = items;
        
        this.priority = getOrderPriority(productWeights, items, firstWarehouse, maxLoad);
    }
    
    public boolean takePortion(Drone drone) {
        
        return false;
    }
    
    private int getOrderPriority(int productWeights[], ArrayList<Integer> items, Warehouse firstWarehouse, int maxLoad){
        int orderPriority = 0;
        
        int orderWeight = 0;
        
        for(Integer item : items){
            orderWeight = productWeights[item];
        }
        
        int distanceToFirstWarehouse = getDistance(
                firstWarehouse.getRow(), firstWarehouse.getColumn()
        );
        
        orderPriority += ( orderWeight * (maxLoad / 10000) );
        orderPriority += distanceToFirstWarehouse;
        
        return orderPriority;
    }
   
    
    // Returns int of distance to [rowB, colB]
    public int getDistance(int rowB, int colB){
        double form1 = (this.row - rowB) * (this.row - rowB);
        double form2 = (this.col - colB) * (this.col - colB);
        
        return (int)Math.ceil( ( Math.sqrt(form1 + form2) ) );
    }
}

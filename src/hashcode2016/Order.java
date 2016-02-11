
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
            int[] productWeights, Warehouse firstWarehouse)
    {
        this.id = id;
        this.row = row;
        this.col = col;
        this.items = items;
        
        this.priority = getOrderPriority(productWeights, items, firstWarehouse);
    }
    
    public boolean takePortion(Drone drone) {
        
        return false;
    }
    
    private int getOrderPriority(int productWeights[], ArrayList<Integer> items, Warehouse firstWarehouse){
        int score = 0;
        
        int orderWeight = 0;
        
        for(Integer item : items){
            orderWeight = productWeights[item];
        }
        
        int distanceToFirstWarehouse = getDistance(
                firstWarehouse.getRow(), firstWarehouse.getColumn()
        );
        
        // THIS IS NOT DONE OR RIGHT PLS NO
        score += orderWeight;
        score += distanceToFirstWarehouse;
        
        if(score < 30)
            return 1;
        else if(score < 50)
            return 2;
        else if(score < 70)
            return 3;
        else
            return 4;
    }
   
    
    // Returns int of distance to [rowB, colB]
    public int getDistance(int rowB, int colB){
        double form1 = (this.row - rowB) * (this.row - rowB);
        double form2 = (this.col - colB) * (this.col - colB);
        
        return (int)Math.ceil( ( Math.sqrt(form1 + form2) ) );
    }
}

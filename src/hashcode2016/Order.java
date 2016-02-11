
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
    
    public Order(int id, int row, int col, int[] items) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.items = new ArrayList<>();
        for (int item : items) {
            this.items.add(item);
        }
    }
    
    public boolean takePortion(Drone drone) {
        
        return false;
    }
}


package hashcode2016;

/**
 *
 * @author aran
 */
public class Order {
    public int id;
    public int row, col;
    public int[] items;
    
    public Order(int id, int row, int col, int[] items) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.items = items;
    }
    
    public boolean takePortion() {
        return false;
    }
}

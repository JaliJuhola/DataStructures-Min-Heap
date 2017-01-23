   /**
     * class Pair contains int variable which is key and data to data that will be stored.
     * <p>
     * Coursework, Tietorakenteet, Fall 2016.
     * <p>
     * @author Jali Juhola (421960),
     */
public class Pair {
    private int key;
    private Object data;
    
    public Pair(int key, Object data) 
    {
        this.key = key;
        this.data = data;
    }
    public int key() {
        return key;
    }
    public Object data() {
        return data;
    }
    
}

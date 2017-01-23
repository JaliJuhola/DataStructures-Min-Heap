   /**
     * PriorityQueue class which stores data whit idea that
     * Smallest priority first out.
     * <p>
     * Coursework, Tietorakenteet, Fall 2016.
     * <p>
     * @author Jali Juhola (421960),
     */
public class PriorityQueue {

    private Pair[] pQueue;
    private int size;
    private int elements; 

    public PriorityQueue() {
        this.size = 1;
        this.pQueue = new Pair[1];
        this.elements = 0;
    }
   /** Adding item to priorityQueue.
     * item will be added to min-heap.
     *
     * @param key Priority of object.
     * @param data Object type data that is added to PriorityQueue according to key. 
     * @return true, if adding is succeed, false, if adding failed.
     */
    public boolean insert(int key, Object data) {
        if (key < 0 || data == null) {
            return false; // invalid data
        }
        Pair newPair = new Pair(key, data);
        this.resize();
        elements++;
        this.pQueue[elements] = newPair;
        this.percolateUp(elements);
        this.size++;
        return true; // adding succeed
    }
     /** Resizes PriorityQueue array
     * if priorityQueue  has no room size of array will be doubled.
     */
    private void resize() {
        // if amount of elements and size of array are equal double size.
        if(this.size == this.elements)
        {
            Pair[] newPQueue = new Pair[this.size * 2]; // new double sized array
            for(int i = 0; i <= size; i++) // copies items to new Array 
            {
                newPQueue[i] = pQueue[i];
            }
            this.size = this.size * 2; 
            this.pQueue = newPQueue;
        }
    }
       /** Checks if priorityQueue is empty
     * @return true, if PriorityQueue is empty, false, if PriorityQueue isn't empty
     */
    public boolean isEmpty() {
        if (this.elements == 0) {
            return true;
        }
        return false;
    }

    public boolean removeMinElement() throws PriorityQueueIsEmpty {
        if (isEmpty()) {
            throw new PriorityQueueIsEmpty();
        }
        return true;
    }
      /** Moves item to it's own place on priorityQueue.
     * Moves item to high as possible according to it's priority.
     *
     * @param idx Index of item that will be moved. 
     */
    private void percolateUp(int idx) {
        while (this.pQueue[parent(idx)].key() > this.pQueue[idx].key()) {
            if (idx == 0) {
                return;
            }
            swap(parent(idx), idx);
            idx = parent(idx);
        }
    }
       /** Swaps objects.
     *
     * @param o1 Object that will be swapped.
     * @param data second object that will be swapped. 
     */
    private static void swap(Object o1, Object o2) {
        Object o3 = o1;
        o1 = 02;
        o2 = o3;
    }
       /** Searches parent of parameter index. 
     *
     * @param i index of item which parent will be returned. 
     * @return index of parameter i:s parent.
     */
    private static int parent(int i) {
        return i / 2;
    }
       /** Searches left child of parameter index. 
     *
     * @param i index of item which left child will be returned. 
     * @return index of parameter i:s left child or if doesnt have left children returns, -1.
     */
    private static int leftChild(int i) {
        return i * 2;
    }
       /** Searches right child of parameter index. 
     *
     * @param i index of item which right child will be returned. 
     * @return index of parameter i:s right child or if doesnt have right children returns, -1.
     */
    private static int rightChild(int i) {
        return i * 2 + 1;
    }
}

/**
 * PriorityQueue class which stores data whit idea that
 * Smallest priority first out.
 * <p>
 * Coursework, Tietorakenteet, Fall 2016.
 * <p>
 * @author Jali Juhola (421960),
 */
package PriorityQueue;

import Files.Log;

public class PriorityQueue {

    /**
     * stores min-heap priorityqueue to array. pQueue starts on item 1
     */
    private Pair[] pQueue;
    /**
     * Stores size of priorityqueue array. resize method resizes priorityqueue.
     */
    private int size;
    /**
     * Stores amount of elements in priorityqueue. amount of elements is
     * elements - 1.
     */
    private int elements;

    /**
     * PriorityQueue constructor
     *
     */
    public PriorityQueue() {
        this.size = 3;
        this.pQueue = new Pair[3];
        this.elements = 1;
    }

    /**
     * Adding item to priorityQueue. item will be added to min-heap
     *
     * @return amount of elements in Pair[]
     */
    public int size() {
        return elements - 1;
    }

    /**
     * Adding item to priorityQueue. item will be added to min-heap.
     *
     * @param key Priority of object.
     * @param data Object type data that is added to PriorityQueue according to
     * key.
     * @return 1, if data or key is invalid, false, 2, if key already exist in
     * pQueue, 0, if adding succeed.
     */
    public int insert(int key, Object data) {
        if (key < 0 || data == null) {
            Log.writeLog("invalid given to priorityQueue key: " + key + " data: " + data);
            return 1; // invalid data
        }
        if (!duplicateKeyCheck(key)) {

            return 2; // duplicated key
        }
        Pair newPair = new Pair(key, data);
        this.resize();
        this.pQueue[elements] = newPair;
        this.percolateUp(elements);
        elements++;
        Log.writeLog("(" + key + ", " + data + ") added to priorityqueue succefully.");
        return 0; // adding succeed
    }

    /**
     * private method which checks if priorityqueue already has key.
     * <p>
     * @param key id that will be checked
     * @return false, if key is already on priorityqueue, true, if isn't in
     * priorityqueue.
     */
    private boolean duplicateKeyCheck(int key) {
        for (int i = 1; i < elements; i++) {
            if (key == this.pQueue[i].key()) {
                Log.writeLog("Tried to add duplicated key " + key + "to index " + i);
                return false;
            }
        }
        return true;
    }

    /**
     * Resizes pQueue array. if array is full doubles array size and copies
     * already existing items to new array replaces pQueue item whit new array.
     * will be doubled.
     */
    private void resize() {
        // if amount of elements and size of array are equal double size.
        if (this.size <= this.elements + 1) {
            Pair[] newPQueue = new Pair[this.size * 2]; // new double sized array
            for (int i = 0; i <= this.elements; i++) // copies items to new Array 
            {
                newPQueue[i] = pQueue[i];
            }
            this.size = this.size * 2;
            this.pQueue = newPQueue;
        }
    }

    /**
     * Checks if priorityQueue is empty
     *
     * @return true, if PriorityQueue is empty, false, if PriorityQueue isn't
     * empty
     */
    public boolean isEmpty() {
        if (this.elements == 1) {
            return true;
        }
        return false;
    }

    /**
     * Removes min element of pqueue. When min element is deleted rearranges
     * Pqueue.
     * <p>
     * @return Pair, if priorityqueue isn't null, null, if priorityqueue is null
     * @see Image
     */
    public Pair removeMinElement() {
        if (this.size() > 0) {
            Pair removed = pQueue[1];
            pQueue[1] = pQueue[elements - 1];
            pQueue[elements - 1] = null;
            elements--;
            percolateDown(1);
            return removed;

        }

        return null;
    }

    /**
     * Moves item to it's own place on priorityQueue. Moves item to high as
     * possible according to it's priority.
     *
     * @param idx Index of item that will be moved.
     */
    private void percolateUp(int idx) {
        while (idx > 1 && this.pQueue[parent(idx)].key() > this.pQueue[idx].key()) {
            //  Log.writeLog("percolated up idx " + idx + " value: " + this.pQueue[idx].key());
            swap(parent(idx), idx);
            // Log.writeLog("percolated new place " + parent(idx) + " value: " + this.pQueue[parent(idx)].key());
            idx = parent(idx);
        }
    }

    /**
     * Moves item to it's own place on priorityqueue. Moves item low as possible
     * on according to it's priority.
     * <p>
     * @param idx of item that will be moved.
     */
    private void percolateDown(int idx) {
        while (leftChild(idx) <= elements - 1 || rightChild(idx) <= elements - 1) { // while leftchild or rightchild != null
            if (idx >= elements - 1) {
                break;
            }

            if ((rightChild(idx) <= elements - 1 && leftChild(idx) <= elements - 1)) { // if left and right child exists 
                if (this.pQueue[rightChild(idx)].key() <= this.pQueue[leftChild(idx)].key()
                        && this.pQueue[rightChild(idx)].key() < this.pQueue[idx].key()) { // if key of rightchild is smaller than roots and leftchilds
                    swap(rightChild(idx), idx);
                    idx = rightChild(idx);
                } else if (this.pQueue[leftChild(idx)].key() < this.pQueue[idx].key()) { // if leftchilds key is smaller than rightchilds and roots
                    swap(leftChild(idx), idx);
                    idx = leftChild(idx);
                } else { // if priority of root is larger than leftchilds and rightchilds breaks loop (Priorityqueue is on right order)
                    break;
                }
            } else if (rightChild(idx) < elements - 1 && leftChild(idx) > elements - 1 // if only rightchild exists
                    && this.pQueue[idx].key() > this.pQueue[rightChild(idx)].key()) {
                swap(rightChild(idx), idx);
                idx = rightChild(idx);

            } else {
                break;
            }

        }

    }

    /**
     * Swaps objects.
     *
     * @param o1 Object that will be swapped.
     * @param o2 second object that will be swapped.
     */
    private void swap(int o1, int o2) {
        Pair pr = this.pQueue[o1];
        this.pQueue[o1] = this.pQueue[o2];
        this.pQueue[o2] = pr;
    }

    /**
     * Searches parent of parameter index.
     *
     * @param i index of item which parent will be returned.
     * @return index of parameter i:s parent.
     */
    public static int parent(int i) {
        return i / 2;
    }

    /**
     * Searches left child of parameter index.
     *
     * @param i index of item which left child will be returned.
     * @return index of parameter i:s left child or if doesnt have left children
     * returns, -1.
     */
    public static int leftChild(int i) {
        return i * 2;
    }

    /**
     * Searches right child of parameter index.
     *
     * @param i index of item which right child will be returned.
     * @return index of parameter i:s right child or if doesnt have right
     * children returns, -1.
     */
    public static int rightChild(int i) {
        return i * 2 + 1;
    }

    private String preorder = ""; // help varible for preorder recursion.

    /**
     * Gets tree as preorder. form of string is Root \t RightChild \t LeftChild
     * ... Calls method preOrderRec which gets preordered tree as recursively.
     * <p>
     * @return String that contains priorityqueue as preorder.
     */
    public String preOrder(int idx) {
        this.preorder = "";
        this.preOrderRec(1);
        return preorder;
    }

    /**
     * Private help method of preOrder(int idx). gets item from priorityqueue
     * and calls it's children recursively ends recursion when priorityqueue on
     * idx is null
     *
     * @param idx index of item in pQueue
     */
    private void preOrderRec(int idx) {
        if (idx < this.elements) {
            preorder = this.preorder + (this.pQueue[idx].data() + "(" + this.pQueue[idx].key() + ") " + "\t");
            preOrderRec(leftChild(idx));
            preOrderRec(rightChild(idx));
        }
    }

    /**
     * Returns priority of Pair in idx
     * <p>
     * @param idx an absolute URL giving the base location of the image
     * @return priority of Pair in idx
     */
    public int getKeyAt(int idx) {
        return this.pQueue[idx].key();
    }

    /**
     * Returns pair whit smallest priority.
     * <p>
     * @return pair whit smallest key in priorityqueue
     */
    public Pair extractMin() {
        return this.pQueue[1];
    }
/**
 * Prints Priorityqueue as tree form to Log
 * leaves method if priorityqueue is empty.
 */
    public void PrintTreeToLog() {
        if (isEmpty()) {
            return;
        }
        int rowSize = 3;
        int i = 1;
        int rowX = 0;
        int blankSpace = 1;
        while (i < elements) {
            if (rowX == rowSize) {
                rowSize = rowSize * 2;
                rowX = 0;
            }
            i++;
            rowX++;

        }
        blankSpace = rowSize;
        rowSize = 1;
        i = 2;
        rowX = 1;
        String blankSpaceS = "";
        for (int s = 0; s < blankSpace; s++) {
            blankSpaceS = blankSpaceS + " ";
        }
        Log.writeLogLine(blankSpaceS);
        Log.writeLogLine("  " + this.pQueue[1].key());
        blankSpace = blankSpace - 3;
        while (i < elements) {
            if (rowX == rowSize) {
                Log.writeLogLine("\r\n");
                blankSpaceS = "";
                rowX = 0;
                rowSize = rowSize * 2;
                for (int s = 0; s < blankSpace; s++) {
                    blankSpaceS = blankSpaceS + " ";
                }
                Log.writeLogLine(blankSpaceS);
                blankSpace = blankSpace - 3;
            }
            Log.writeLogLine("  " + this.pQueue[i].key());
            i++;
            rowX++;

        }
        Log.writeLogLine("\r\n");
        Log.writeLogLine("\r\n");
        Log.writeLogLine("\r\n");
    }
}

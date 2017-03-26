package PriorityQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Assert;
import PriorityQueue.*;
import Files.*;

public class PriorityQueueTest {

    public final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @BeforeClass
    public static void setUpClass() {
        Files.Log.logClear();
    }

    @Test
    public void PriorityQueueMake() {
        Random r = new Random();
        PriorityQueue pQueue = make();
        if (pQueue.size() < 2) {
            Assert.assertTrue("no elements added to priorityqueue", false);
        }
        Assert.assertTrue(true);
    }

    @Test // just inserts and checks if order is right
    public void CoupleInsertTests() {
        int s = 0;
        for (int i = 0; i < 100; i++) {
            checkOrder(make());
        }
        Assert.assertTrue(true);
    }

    @Test
    // inserts items and removes min element 10 times and after that checks order
    // does 100 priorityqueue and tests.
    public void CoupleInsertsAndDeletesTest() {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            PriorityQueue pQueue = make();
            for (int s = 10; s > 0; s--) {
                pQueue.removeMinElement();
            }
            if (!checkOrderRec(1, pQueue)) {
                Assert.assertTrue(false);
            }
        }
        Assert.assertTrue(true);
    }

    @Test
    // Tests that min value is right all the time
    // Makes 100 Priorityqueues length of 1000 id between [0, 100000] also
    public void testMinValue() {
        Random r = new Random();
        int smallest = Integer.MAX_VALUE;
        boolean failed = false;
        PriorityQueue pQueue = new PriorityQueue();
        for (int i = 100; i > 0; i--) {
            for (int s = 1000; s > 0; s--) {
                String object = "";
                for (int e = 0; e < 20; e++) {
                    object += alphabet.charAt(r.nextInt(25));
                }
                int key = r.nextInt(10000 + 1);
                if (key < smallest) {
                   smallest = key;
                }
                pQueue.insert(key, object);
            }
            if(smallest !=  pQueue.extractMin().key())
            {
                failed = true;
                Log.writeLog("smallest value failed: ");
                Log.writeLog(pQueue.preOrder(1));
                
                
            }

        }
        if(!failed)
        {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue("smallest value didn't match", false);
        }

    }

    @Test
    public void testSize() { // adds 99 items and checking if priorityqueues size is ok
        Random r = new Random();
        PriorityQueue pQueue = new PriorityQueue();
        for (int i = 100; i > 0; i--) {
            String object = "";
            for (int e = 0; e < 4; e++) {
                object += alphabet.charAt(r.nextInt(25));
            }
            pQueue.insert(i, object);

        }
        if (pQueue.size() == 100) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue("size should be 100 now " + pQueue.size(), false);
        }
    }

    @Test
    // inserts 100 items to priorityqueue and deletes min 100 times
    // makes 10 rounds
    // puts every remove to Log.txt file
    public void testRemoveMinElement() {

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            PriorityQueue pq = new PriorityQueue();
            String object = "";
            for (int e = 0; e < 4; e++) {
                object += alphabet.charAt(r.nextInt(25));
            }
            pq.insert(i, object);

            for (int e = 0; e < 100; e++) {
                pq.removeMinElement();
                Log.writeLog(pq.preOrder(i));
            }
        }

    }

    // Some help methods
    public PriorityQueue make() {
        Random r = new Random();
        PriorityQueue pQueue = new PriorityQueue();
        for (int i = 100; i > 0; i--) {
            String object = "";
            for (int e = 0; e < 4; e++) {
                object += alphabet.charAt(r.nextInt(25));
            }
            pQueue.insert(r.nextInt(1000), object);

        }
        return pQueue;
    }

    public boolean checkOrder(PriorityQueue pq) {
        if (!checkOrderRec(1, pq)) {
            return false;
        } else {
            return true;
        }

    }
    // checks order and if it isn't right exists program and gives error.

    public boolean checkOrderRec(int idx, PriorityQueue pque) {
        if (pque.size() < idx) {
            checkOrderRec(leftChild(idx), pque);
            checkOrderRec(rightChild(idx), pque);
        }
        if (pque.getKeyAt(idx) > pque.getKeyAt(leftChild(idx))
                || pque.getKeyAt(idx) > pque.getKeyAt(rightChild(idx))) {
            Assert.assertTrue("priorityQueue is on wrong order"
                    + "root: " + pque.getKeyAt(idx) + "leftChild: "
                    + pque.getKeyAt(leftChild(idx)) + "rightChild: "
                    + pque.getKeyAt(rightChild(idx)), false);
            System.exit(0); // exists virtual machine so makes error and stops recursion
        }
        return true;

    }

    public static int leftChild(int i) {
        return i * 2;
    }

    public static int rightChild(int i) {
        return i * 2 + 1;
    }

}

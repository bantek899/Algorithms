/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        n = 0;
    }

    private RandomizedQueue(Item[] items, int capacity) {
        q = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, q, 0, capacity);
        n = capacity;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == q.length)
            resize(q.length << 1);
        q[n++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randomNum = StdRandom.uniformInt(0, n); // random number 0 to n
        Item item = q[randomNum];
        q[randomNum] = q[--n];
        q[n] = null;
        if (n > 0 && n == q.length >> 2)
            resize(q.length >> 1);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randomNum = StdRandom.uniformInt(0, n); // random number 0 to n
        return q[randomNum];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private RandomizedQueue<Item> r = new RandomizedQueue<>((Item[]) q, n);


        public boolean hasNext() {
            return r.size() > 0;
        }

        public Item next() {
            if (isEmpty())
                throw new NoSuchElementException();

            return r.dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q1 = new RandomizedQueue<>();
        RandomizedQueue<String> q2 = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.println(q1.dequeue());
                StdOut.println(q2.dequeue());
            }
            else {
                q1.enqueue(s);
                q2.enqueue(s);
            }

            StdOut.println("q1 ");
            for (String item : q1) {
                StdOut.print(item + " ");
            }
            StdOut.println();
            StdOut.println("q2 ");
            for (String item : q2) {
                StdOut.print(item + " ");
            }
            StdOut.println();
        }
    }

}
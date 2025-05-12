/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private int N;
    private boolean[] site;
    private int[] connected;
    private int open;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        site = new boolean[n * n];
        connected = new int[n * n];
        N = n;
        for (int i = 0; i < n * n; i++) {
            site[i] = false;
            connected[i] = i;
        }
    }

    private int coordToIndex(int row, int col) {
        return N * (row - 1) + (col - 1);
    }

    private boolean validate(int row, int col) {
        return coordToIndex(row, col) >= 0 && coordToIndex(row, col) < N * N;
    }

    private int root(int i) {
        while (i != connected[i]) {
            i = connected[i];
        }
        return i;
    }

    private void union(int p, int q) {
        connected[q] = root(p);
    }

    private boolean find(int p, int q) {
        return root(p) == root(q);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        int i = coordToIndex(row, col);
        site[i] = true;
        open++;

        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            union(i, coordToIndex(row - 1, col));
        }

        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            union(i, coordToIndex(row + 1, col));
        }

        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            union(i, coordToIndex(row, col - 1));
        }

        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            union(i, coordToIndex(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }

        int i = coordToIndex(row, col);
        return site[i];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col))
            return false;

        int i = coordToIndex(row, col);
        for (int j = 0; j < N; j++) {
            if (isOpen(j / N + 1, j % N + 1) && find(i, j))
                return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = (N - 1) * N; i < N * N; i++) {
            for (int j = 0; j < N; j++) {
                if (find(i, j))
                    return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Percolation p = new Percolation(N);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            if (!p.isOpen(row, col)) {
                p.open(row, col);
            }
            int i = p.coordToIndex(row, col);
            StdOut.println(p.connected[i]);
            StdOut.println(p.isOpen(row, col));
            StdOut.println(p.isFull(row, col));
        }
    }
}
/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.00;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.00;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.00;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.00;
    }

    // test client (see below)
    public static void main(String[] args) {

    }

}

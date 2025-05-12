/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Comparator;

public class FastCollinearPoints {
    private double[] slopes;
    private LineSegment[] segments;
    private Comparator<Point> point_comparator = Point::compareTo;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        for (int i = 0, length = points.length; i < length; i++) {
            Point p = points[i];
            for (int j = i + 1; j < length; j++) {
                Point q = points[j];
                slopes[i] = p.slopeTo(q);
            }
        }
        mergesort(slopes, point_comparator);

    }

    private <T> void mergesort(T[] a, Comparator<T> c) {
        T[] aux = (T[]) new Object[a.length];
        sort(a, aux, 0, a.length - 1, c);
    }

    private <T> void sort(T[] a, T[] aux, int lo, int hi, Comparator c) {
        if (hi <= lo) return;
        int mid = lo + hi / 2;
        sort(a, aux, lo, mid, c);
        sort(a, aux, mid + 1, hi, c);
        merge(a, aux, lo, mid, hi, c);
    }

    private <T> void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator c) {
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (c.compare(aux[i], aux[j]) == -1) a[k] = aux[i++];
            else aux[k] = aux[j++];
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }

    public static void main(String[] args) {

    }
}

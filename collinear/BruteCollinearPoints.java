/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int size;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        size = 0;
        segments = new LineSegment[1];
        Arrays.sort(points);
        Point p, q, r, s;
        double slopePQ, slopePR, slopePS;
        for (int i = 0, length = points.length; i < length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            p = points[i];
            for (int j = i + 1; j < length; j++) {
                if (points[j] == null) {
                    throw new IllegalArgumentException();
                }
                q = points[j];
                slopePQ = p.slopeTo(q);
                for (int k = j + 1; k < length; k++) {
                    if (points[k] == null) {
                        throw new IllegalArgumentException();
                    }
                    r = points[k];
                    slopePR = p.slopeTo(r);
                    if (slopePQ != slopePR) {
                        continue;
                    }
                    for (int l = k + 1; l < length; l++) {
                        if (points[l] == null) {
                            throw new IllegalArgumentException();
                        }
                        s = points[l];
                        slopePS = p.slopeTo(s);
                        if (slopePQ == slopePS) {
                            enqueue(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    private void enqueue(LineSegment l) {
        if (segments.length == size) {
            LineSegment[] tmp = new LineSegment[size * 2];
            System.arraycopy(segments, 0, tmp, 0, segments.length);
            segments = tmp;
        }
        segments[size++] = l;
    }

    // the number of line segments
    public int numberOfSegments() {
        return size;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
        for (LineSegment segment : collinearPoints.segments()) {
            StdOut.println(segment);
        }
    }
}

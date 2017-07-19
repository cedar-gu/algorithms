import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tgu on 7/10/17.
 */
class Point implements Comparable<Point> {
    double x;
    double y;
    boolean isCaper;

    Point(double x, double y, boolean isCaper) {
        this.x = x;
        this.y = y;
        this.isCaper = isCaper;
    }

    @Override
    public int compareTo(Point other) {
        if (this.x == 0) {
            return -1; // if point is on y-axis, consider as negative infinity
        } else if (other.x == 0) {
            return 1;
        } else {
            if (this.y/this.x - other.y/other.x < 0) { // comparing y/x (tangent of the angle)
                return -1;
            } else {
                return 1;
            }
        }
    }
}

class Main {
    public static final int ME = 0;
    public static final int GF = 1;

    public static void main(String[] args) throws IOException {
        Main problem = new Main();
        problem.solve();
    }

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner in = new Scanner(System.in);

        while (true) {
            List<Point> points = new ArrayList<>();

            String[] nums = br.readLine().split(" ");
            int c = Integer.valueOf(nums[0]);
            int p = Integer.valueOf(nums[1]);

            if (c % 2 == 1 || p % 2 == 1) {
                System.out.println("NO");
                return;
            }
            if (c == -1 && p == -1) {
                break;
            }

            for (int i = 1; i <= c; i++) {
                nums = br.readLine().split(" ");
                double x = Double.valueOf(nums[0]);
                double y = Double.valueOf(nums[1]);
                points.add(new Point(x, y, true));
            }

            for (int i = 1; i <= p; i++) {
                nums = br.readLine().split(" ");
                double x = Double.valueOf(nums[0]);
                double y = Double.valueOf(nums[1]);
                points.add(new Point(x, y, false));
            }

            br.readLine();
            Collections.sort(points);
            pizzaCut(points);
        }
    }

    void pizzaCut(List<Point> points) {
        int[] caper_count = new int[2];
        int[] peppercorn_count = new int[2];

        // base case: when cut through first point, counting capers and peppercorns for each half
        // (not including the point that got cut)
        double a = points.get(0).x;
        double b = points.get(0).y;

        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);

            int MEorGF = linePartition(a, b, point.x, point.y);

            if (point.isCaper) {
                caper_count[MEorGF]++;
            } else {
                peppercorn_count[MEorGF]++;
            }
        }

        if (canCut(caper_count, peppercorn_count, points.get(0).isCaper)) {
            System.out.println("YES");
            return;
        }

        // counter-clockwise rotate cutting line using each point
        for (int i = 1; i < points.size(); i++) {
            Point prev = points.get(i-1);
            Point curr = points.get(i);

            int MEorGF = linePartition(prev.x, prev.y, curr.x, curr.y);

            if (curr.isCaper) {
                caper_count[MEorGF]--;
            } else {
                peppercorn_count[MEorGF]--;
            }

            MEorGF = linePartition(curr.x, curr.y, prev.x, prev.y);
            if (prev.isCaper) {
                caper_count[MEorGF]++;
            } else {
                peppercorn_count[MEorGF]++;
            }

            if (canCut(caper_count, peppercorn_count, curr.isCaper)) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    int linePartition(double a, double b, double x, double y) {
        double partition;

        if (a == 0) {
            partition = x;
        } else if (b == 0) {
            partition = y;
        } else if (a * b < 0) {
            partition = Math.abs(b) * x + Math.abs(a) * y;
        } else {  // a * b > 0
            partition = -Math.abs(b) * x + Math.abs(a) * y;
        }

        if (partition < 0) {
            return ME;
        } else { // partition > 0
            return GF;
        }
    }

    boolean canCut(int[] caper_count, int[] peppercorn_count, boolean cutCaper) {
        if (cutCaper) {
            return (peppercorn_count[ME] == peppercorn_count[GF]) && (Math.abs(caper_count[ME] - caper_count[GF]) == 1);
        } else {
            return (caper_count[ME] == caper_count[GF]) && (Math.abs(peppercorn_count[ME] - peppercorn_count[GF]) == 1);
        }
    }
}
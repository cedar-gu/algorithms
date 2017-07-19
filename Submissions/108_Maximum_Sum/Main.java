import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main problem = new Main();
        problem.solve();
    }

    public void solve() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        System.out.println(maxSubRectangle(matrix));
    }

    public int maxSubRectangle(int[][] matrix) {
        int n = matrix.length;
        int globalMax = Integer.MIN_VALUE;

        for (int bottomRow = 0; bottomRow < n; bottomRow++) {
            int[] buffer = new int[n];

            for (int row = bottomRow; row >= 0; row--) {
                int minSum = 0;
                int prefixSum = 0;

                for (int col = 0; col < n; col++) {
                    buffer[col] += matrix[row][col];
                    prefixSum += buffer[col];
                    globalMax = Math.max(globalMax, prefixSum - minSum);
                    minSum = Math.min(minSum, prefixSum);
                }
            }
        }
        return globalMax;
    }
}

package uva.maximum_sum_108;

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

        System.out.println(maxSubRectangle2(matrix));
    }

    public int maxSubRectangle2(int[][] matrix) {
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

    public int maxSubRectangle(int[][] matrix) {
        int n = matrix.length;
        int[][] prefixSum = new int[n][n];
        int max = Integer.MIN_VALUE;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                prefixSum[row][col] = matrix[row][col];

                if (row >= 1) {
                    prefixSum[row][col] += prefixSum[row-1][col];
                }
                if (col >= 1) {
                    prefixSum[row][col] += prefixSum[row][col-1];
                }
                if (row >= 1 && col >=1) {
                    prefixSum[row][col] -= prefixSum[row-1][col-1];
                }
            }
        }


        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < n; c1++) {
                for (int r2 = r1; r2 < n; r2++) {
                    for (int c2 = c1; c2 < n; c2++) {
                        int subRectangle = prefixSum[r2][c2];

                        if (r1 >= 1) {
                            subRectangle -= prefixSum[r1-1][c2];
                        }
                        if (c1 >= 1) {
                            subRectangle -= prefixSum[r2][c1-1];
                        }
                        if (r1 >= 1 && c1 >= 1) {
                            subRectangle += prefixSum[r1-1][c1-1];
                        }
                        max = Math.max(max, subRectangle);
                    }
                }
            }
        }

        return max;
    }
}



import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main problem = new Main();
        problem.solve();
    }

    public void solve() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        while (n != 0) {

            int[] weights = new int[n];
            int[] capacity = new int[n];

            for (int i = n - 1; i >= 0; i--) {
                weights[i] = in.nextInt();
                capacity[i] = in.nextInt();
            }
            System.out.println(maxNumBoxesDP(weights, capacity));
            n = in.nextInt();
        }
        in.close();
    }

    public int maxNumBoxesDP(int[] weights, int[] capacity) {
        int N = weights.length;
        int C = 3000 * 2;
        int[][] dp = new int[N][C + 1];

        // initialization
        for (int j = weights[0]; j <= C; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i-1][Math.min(j - weights[i], capacity[i])]);
                }
            }
        }
        return dp[N - 1][C];
    }
}


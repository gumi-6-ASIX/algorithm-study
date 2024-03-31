import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] nums = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최대 점수 dp[0][][];
        // 최소 점수 dp[1][][];
        int[][][] dp = new int[2][n][3];
        dp[0][0] = nums[0];
        dp[1][0] = nums[0];

        for (int i = 1; i < n; i++) {
            // 최대 점수
            dp[0][i][0] = Math.max(dp[0][i - 1][0], dp[0][i - 1][1]) + nums[i][0];
            dp[0][i][1] = Math.max(Math.max(dp[0][i - 1][0], dp[0][i - 1][1]), dp[0][i - 1][2]) + nums[i][1];
            dp[0][i][2] = Math.max(dp[0][i - 1][1], dp[0][i - 1][2]) + nums[i][2];

            // 최소 점수
            dp[1][i][0] = Math.min(dp[1][i - 1][0], dp[1][i - 1][1]) + nums[i][0];
            dp[1][i][1] = Math.min(Math.min(dp[1][i - 1][0], dp[1][i - 1][1]), dp[1][i - 1][2]) + nums[i][1];
            dp[1][i][2] = Math.min(dp[1][i - 1][1], dp[1][i - 1][2]) + nums[i][2];
        }

        int max_val = 0;
        for (int i : dp[0][n - 1]) {
            max_val = Math.max(max_val, i);
        }

        int min_val = Integer.MAX_VALUE;
        for (int i : dp[1][n - 1]) {
            min_val = Math.min(min_val, i);
        }

        System.out.println(max_val + " " + min_val);
    }
}
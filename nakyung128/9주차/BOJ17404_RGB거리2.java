import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404_RGB거리2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] rgb = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[0][j] = rgb[0][i];

                } else
                    dp[0][j] = 99999;
            }

            for (int j = 1; j < n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + rgb[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + rgb[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + rgb[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i == j)
                    continue;
                answer = Math.min(answer, dp[n - 1][j]);
            }
        }

        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        if (!st.hasMoreTokens()) {
          dp[i][j] = -1;
          continue;
        }
        dp[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (dp[i][j] == -1)
          break;
        if (j == 0) {
          dp[i][j] += dp[i - 1][j];
        } else if (j == n - 1) {
          dp[i][j] += dp[i - 1][j - 1];
        } else {
          dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
        }
      }
    }

    int max_result = 0;
    // 최댓값 찾기
    for (int i = 0; i < n; i++) {
      max_result = Math.max(max_result, dp[n - 1][i]);
    }

    System.out.println(max_result);
  }
}

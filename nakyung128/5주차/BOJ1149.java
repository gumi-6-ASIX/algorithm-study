import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {
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

    dp[0] = rgb[0];

    for (int i = 1; i < n; i++) {
      dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
      dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
      dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
    }

    int min_price = Integer.MAX_VALUE;

    for (int i = 0; i < 3; i++) {
      if (dp[n - 1][i] < min_price)
        min_price = dp[n - 1][i];
    }

    System.out.println(min_price);
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1463 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int num = Integer.parseInt(st.nextToken());

    int[] dp = new int[num + 1];

    dp[0] = 0;
    dp[1] = 0;

    for (int i = 2; i <= num; i++) {
      if (i % 6 == 0) {
        dp[i] = Math.min(Math.min(dp[i / 3] + 1, dp[i / 2] + 1), dp[i - 1] + 1);
      } else if (i % 3 == 0) {
        dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);
      } else if (i % 2 == 0) {
        dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);
      } else {
        dp[i] = 1 + dp[i - 1];
      }
    }
    System.out.println(dp[num]);
  }
}
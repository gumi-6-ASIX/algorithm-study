import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[] t = new int[n + 1];
    int[] p = new int[n + 1];
    int[] dp = new int[n + 2];

    for (int i = 1; i < n + 1; i++) {
      st = new StringTokenizer(br.readLine());
      t[i] = Integer.parseInt(st.nextToken());
      p[i] = Integer.parseInt(st.nextToken());
    }

    int result = 0;

    for (int i = 1; i < n + 1; i++) {
      if (i + t[i] > n + 1) {
        continue;
      } else {
        dp[i + t[i]] = Math.max(p[i] + dp[i], dp[i + t[i]]);
        for (int j = i + t[i] + 1; j <= n + 1; j++) {
          if (dp[j] < dp[i + t[i]]) {
            dp[j] = dp[i + t[i]];
          }
        }
      }
    }

    // 최댓값 찾기
    for (int i = 0; i < dp.length; i++) {
      if (dp[i] > result)
        result = dp[i];
    }

    System.out.println(result);

  }
}

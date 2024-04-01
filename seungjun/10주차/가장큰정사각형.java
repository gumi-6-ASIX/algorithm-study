import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int n, m, val, flag, miin = Integer.MAX_VALUE, maax = Integer.MIN_VALUE;
  static int[][] num, dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    ArrayList<int[]> list = new ArrayList<>();
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    num = new int[n][m];
    dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      String arr = br.readLine();
      for (int j = 0; j < m; j++) {
        num[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
        dp[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
      }
      for (int j = 0; j < m; j++) {
        if (num[i][j] == 1) {
          val = 1;
          if (j - 1 >= 0) {
            dp[i][j] = dp[i][j - 1] + num[i][j];
          }
          list.add(new int[] { i, j });

        }
      }
    }
    if (n == 1 || m == 1) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (num[i][j] == 1) {
            System.out.println("1");
            return;
          }
        }
      }
      System.out.println("0");
      return;

    }

    for (int i = 0; i < list.size(); i++) {
      if (dp[list.get(i)[0]][list.get(i)[1]] != 0) {
        int a = list.get(i)[0];
        int b = list.get(i)[1];
        flag = 0;
        miin = dp[a][b];
        for (int t = 1; t < miin; t++) {
          if (t + a < n && dp[a + t][b] != 0) {
            miin = Math.min(dp[a + t][b], miin);
          } else {
            flag = 1;
            break;
          }
        }
        if (flag == 0) {
          maax = Math.max(miin, maax);
        }
      }
    }
    if (maax == Integer.MIN_VALUE) {
      if (val == 1) {
        System.out.println("1");
        return;
      }
      System.out.println("0");
    } else {
      System.out.println(maax * maax);
    }

  }
}

import java.util.*;
import java.io.*;

class 가장_큰_정사각형 {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][m + 1];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                int tmp = row.charAt(j - 1) - '0';
                if (i == 1 && j == 1) {
                    dp[i][j] = tmp;
                } else {
                    if (tmp == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }

        if (n == 1 && m == 1) {
            if (dp[1][1] == 1) {
                System.out.println(1);
                return;
            } else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(max * max);
        // StringBuilder sb = new StringBuilder();

    }
}
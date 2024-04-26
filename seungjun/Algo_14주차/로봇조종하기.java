package Baekjoon.Algo_14주차;

import java.io.*;
import java.util.*;

public class 로봇조종하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] num = new int[n + 1][m + 1];
        int[][][] dp = new int[n + 1][m + 1][3];
        for (int i = 0; i <= n; i++) {
            if (i > 0) {
                st = new StringTokenizer(br.readLine());
            }
            for (int j = 0; j <= m; j++) {
                if (i >= 1 && j >= 1) {
                    num[i][j] = Integer.parseInt(st.nextToken());
                }
                dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -2000000000;
            }

        }
        dp[1][1][0] = dp[1][1][1] = dp[1][1][2] = num[1][1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j > 1) {
                    dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1]) + num[i][j];
                }
                if (i > 1) {
                    dp[i][j][1] = Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i - 1][j][2]) + num[i][j];
                }

            }
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j][2] = Math.max(dp[i][j + 1][2], dp[i][j + 1][1]) + num[i][j];
            }
        }

        System.out.println(Math.max(dp[n][m][0], dp[n][m][1]));
    }
}

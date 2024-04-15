package Baekjoon.Algo_12주차;

import java.io.*;
import java.util.*;

public class 자두나무 {
    static int[] num;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        num = new int[T + 1];
        dp = new int[T + 1][W + 2][3];
        for (int i = 1; i <= T; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= W + 1; j++) {
                if (num[i] == 1) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]);
                } else {
                    if (i == 1 && j == 1)
                        continue;
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]) + 1;
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= W + 1; i++) {
            sum = Math.max(sum, Math.max(dp[T][i][1], dp[T][i][2]));
        }
        System.out.println(sum);

    }
}

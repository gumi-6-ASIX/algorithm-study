package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2240_자두나무 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기 데이터 입력 받기
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] dp = new int[W + 1][T + 1];

        int result = 0;
        for (int t = 1; t <= T; t++) {
            int value = Integer.parseInt(br.readLine());
            for (int w = 0; w <= W; w++) {
                if (t < w)
                    continue;

                int treeOk = (value == w % 2 + 1) ? 1 : 0;
                if (w == 0) {
                    dp[w][t] = dp[w][t - 1] + treeOk;
                } else {
                    dp[w][t] = Math.max(dp[w - 1][t - 1] + treeOk, dp[w][t - 1] + treeOk);
                }

                if (t == T && dp[w][t] > result) {
                    result = dp[w][t];
                }
            }
        }
        System.out.println(result);
    }

}

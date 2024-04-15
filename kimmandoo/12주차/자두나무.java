import java.util.*;
import java.io.*;

class 자두나무 {
    static int t, w;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        dp = new int[w + 1][t + 1]; // w는 0~w까지, t는 1부터 t까지
        for (int i = 1; i <= t; i++) {
            int plum = Integer.parseInt(br.readLine());
            for (int p = 0; p <= w; p++) {
                if (plum == 1) {
                    if (p == 0)
                        dp[p][i] = dp[p][i - 1] + 1; // 첫 번째 나무에서는 따로 고려
                    else
                        dp[p][i] = Math.max(dp[p - 1][i - 1], dp[p][i - 1]) + (p % 2 == 0 ? 1 : 0); // 짝수일때는 1나무에 있을
                                                                                                    // 것이다.
                } else {
                    if (p == 0)
                        dp[p][i] = dp[p][i - 1];
                    else
                        dp[p][i] = Math.max(dp[p - 1][i - 1], dp[p][i - 1]) + (p % 2 == 0 ? 0 : 1);
                }
            }
        }
        int max = -1;
        for (int i = 0; i <= t; i++) {
            for (int p = 0; p <= w; p++) {
                max = Math.max(max, dp[p][i]);
            }
        }
        System.out.println(max);
    }
}
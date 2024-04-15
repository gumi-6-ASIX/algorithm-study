import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240_자두나무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken()); // 초
        int w = Integer.parseInt(st.nextToken()); // 최대 움직일 수 있는 횟수

        int[][] dp = new int[t + 1][w + 1];

        for (int i = 1; i < t + 1; i++) {
            int tree = Integer.parseInt(br.readLine());
            for (int j = 0; j < w + 1; j++) {
                // 첫 시작
                if (j == 0) {
                    // 1번 나무면 + 1
                    if (tree == 1) {
                        dp[i][j] = dp[i - 1][j] + 1;
                    } else { // 2번 나무면 아무 것도 아님
                        dp[i][j] = dp[i - 1][j];
                    }
                    continue;
                }

                // 짝수번 이동했으면 1번 나무
                if (j % 2 == 0) {
                    // 1번 나무면
                    if (tree == 1) {
                        // 이전 1번 나무 + 1 vs 이전 2번 나무
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                    } else {
                        // 이전 2번 나무 + 1 vs 이전 1번 나무
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    }
                } else { // 홀수번 이동했으면 2번 나무
                    if (tree == 2) {
                        // 이전 2번 나무 + 1 vs 이전 1번 나무
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                    } else {
                        // 이전 1번 나무 + 1 vs 이전 2번 나무
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    }
                }
            }
        }

        int answer = 0;
        for (int jadu : dp[t]) {
            answer = Math.max(answer, jadu);
        }

        System.out.println(answer);
    }
}

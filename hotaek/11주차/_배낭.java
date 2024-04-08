import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _배낭 {

    static class Thing {
        int weight, value;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Thing[] things = new Thing[n + 1];

        things[0] = new Thing(0, 0);
        for (int i = 1; i <= n; i++) {
            StringTokenizer stg = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stg.nextToken());
            int b = Integer.parseInt(stg.nextToken());
            things[i] = new Thing(a, b);
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 무게가 크면 이전꺼 그대로 가져오기
                if (things[i].weight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - things[i].weight] + things[i].value);
                }

            }
        }

        System.out.println(dp[n][k]);

    }
}

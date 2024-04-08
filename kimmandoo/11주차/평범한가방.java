import java.util.*;
import java.io.*;

public class 평범한가방 {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 물품의 수
        int k = Integer.parseInt(st.nextToken()); // 최대 무게

        int[][] dp = new int[n + 1][k + 1]; // 용량이 k인 배낭이고, 각 row의 인덱스에는 해당 용량일 때의 가치값이 들어가있다.
        int[][] input = new int[n + 1][2]; // 각 물건의 0-무게, 1-가치
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            input[i][0] = weight;
            input[i][1] = value;
        }

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= k; w++) { // 최대 k 무게를 담을 때
                if (input[i][0] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 이전 물건을 담은 상태가 더 크면, 물건을 안담고 넘어감
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - input[i][0]] + input[i][1]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
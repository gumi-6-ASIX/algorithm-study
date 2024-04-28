package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * temp 배열 -> 왼쪽에서 출발 temp[0][m], 오른쪽 출발 temp[1][m] 둘이 비교 해서 최대값으로 dp를 갱신해줌
 * dp는 2차원 배열임.
 * dp[0][m] 값들을 오른쪽으로 더해준 값이 최대값.
 * dp[1] 부터는 temp값과 dp 위에 값들을 비교해주며 최대값에 현재 graph상 좌표를 더해준 temp의 0과 1중 최대값을 갱신!
 * 
 */

public class _2169_로봇조종하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];
        int[][] dp = new int[n][m];
        int[][] temp = new int[2][m];

        // 그래프 값 넣기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 dp[0] 채우기
        dp[0][0] = graph[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + graph[0][i];
        }
        System.out.println(Arrays.toString(dp[0]));
        for (int i = 1; i < n; i++) {
            System.out.println("야호");
            // 왼쪽부터 시작 -> 첫 번째줄 탬프값 넣기
            temp[0][0] = dp[i - 1][0] + graph[i][0];
            // 위의 값과 왼쪽 값을 비교!! 하고 현재 graph 값을 더해줌.
            for (int j = 1; j < m; j++) {
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + graph[i][j];
            }

            // 오른쪽부터 시작 -> 두 번째줄 탬프값 넣기
            temp[1][m - 1] = dp[i - 1][m - 1] + graph[i][m - 1];
            for (int j = (m - 2); j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + graph[i][j];
            }

            // dp값 갱신
            for (int j = 0; j < m; j++) {
                if (temp[0][j] > temp[1][j]) {
                    dp[i][j] = temp[0][j];
                } else {
                    dp[i][j] = temp[1][j];
                }
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }

}

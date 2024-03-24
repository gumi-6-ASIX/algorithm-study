import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class RGB거리2 {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; // new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(br.readLine());
        int[][] in = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            in[i][0] = Integer.parseInt(st.nextToken());
            in[i][1] = Integer.parseInt(st.nextToken());
            in[i][2] = Integer.parseInt(st.nextToken());
        }
        // 입력 끝
        int min = 1000000;

        for (int c = 0; c < 3; c++) {
            int[][] dp = new int[n + 1][3];
            dp[1][c] = in[1][c];
            for (int j = 0; j < 3; j++) { //
                if (j == c) {
                    dp[1][c] = in[1][c];
                } else {
                    dp[1][j] = 1000000; // 이게 필요한 이유는 아예 큰 값으로 해서 dp[2][]의 경우에 다른 색상이 선택되지않도록 하는 것
                }
            }
            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + in[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + in[i][1];
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + in[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (i != c) {
                    min = Math.min(min, dp[n][i]);
                }
            }
        }
        System.out.println(min);
    }
}
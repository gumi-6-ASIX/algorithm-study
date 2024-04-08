import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, 1, -1, 0 };
    static int[][] num, dp;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dfs(0, 0, num[0][0]);
        System.out.println(dp[0][0]);
    }

    public static void dfs(int x, int y, int num1) {
        if (x == n - 1 && y == m - 1) {
            dp[x][y] = 1;
            return;
        }
        if (dp[x][y] == -1) {
            dp[x][y] += 1;
            for (int i = 0; i < 4; i++) {
                int a = dx[i] + x;
                int b = dy[i] + y;
                if (check(a, b) && num1 > num[a][b]) {
                    dfs(a, b, num[a][b]);
                    dp[x][y] += dp[a][b];
                }
            }
        }

    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }
}

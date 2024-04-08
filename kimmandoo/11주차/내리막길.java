import java.io.*;
import java.util.*;

class Main {
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };
    static int n, m;
    static int[][] map;
    static int[][] dp;

    public static int dfs(int ci, int cj) {
        if (ci == n - 1 && cj == m - 1) {
            return 1;
        }
        if (dp[ci][cj] != -1)
            return dp[ci][cj]; // 방문했으니까 계산하지않고 반환한다.
        dp[ci][cj] = 0; // 방문처리
        for (int i = 0; i < 4; i++) {
            int ni = di[i] + ci;
            int nj = dj[i] + cj;
            if (isValid(ci, cj, ni, nj)) {
                dp[ci][cj] += dfs(ni, nj);
            }
        }
        return dp[ci][cj];
    }

    public static boolean isValid(int ci, int cj, int ni, int nj) {
        return ni < n && nj < m && ni >= 0 && nj >= 0 && map[ci][cj] > map[ni][nj];
    }

    public static void main(String args[]) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
}
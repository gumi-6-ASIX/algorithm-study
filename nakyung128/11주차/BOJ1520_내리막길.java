import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520_내리막길 {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int m, n, answer;
    static int[][] map, dp;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(findRoot(0, 0));
    }

    static int findRoot(int x, int y) {
        // 목적지 도달했으면
        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        // 방문한 적 없으면
        if (dp[x][y] == -1) {
            dp[x][y] = 0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (map[nx][ny] < map[x][y]) {
                        dp[x][y] += findRoot(nx, ny);
                    }
                }
            }
        }
        return dp[x][y];
    }
}

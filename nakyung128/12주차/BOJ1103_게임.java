import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1103_게임 {
    static char[][] board;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int n, m, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == 'H') {
                    board[i][j] = row.charAt(j);
                } else {
                    board[i][j] = row.charAt(j);
                }
            }
        }

        answer = 1;
        dp[0][0] = 1;
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int x, int y) {
        if (visited[x][y]) {
            System.out.println(-1);
            System.exit(0);
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * (board[x][y] - '0');
            int ny = y + dy[i] * (board[x][y] - '0');

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != 'H') {
                if (dp[nx][ny] < dp[x][y] + 1) {
                    dp[nx][ny] = dp[x][y] + 1;
                    answer = Math.max(answer, dp[nx][ny]);
                    dfs(nx, ny);
                }
            }
        }
        visited[x][y] = false;
    }
}

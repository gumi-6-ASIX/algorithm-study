import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 옮기기
public class BOJ17070_파이프옮기기1 {
    static int[][] pipe;
    static int n;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        pipe = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pipe[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        dfs(0, 1, 0);
        System.out.println(cnt);
    }

    // iam
    // 0: 가로, 1: 세로, 2: 대각선
    static void dfs(int x, int y, int iam) {
        // 오른쪽, 오른쪽 아래 대각선, 아래
        int[] dx = new int[] { 0, 1, 1 };
        int[] dy = new int[] { 1, 1, 0 };

        // n, n에 도착했으면 cnt + 1 해 주고 return
        if (x == n - 1 && y == n - 1) {
            cnt++;
            return;
        }

        // 가로면
        if (iam == 0) {
            for (int i = 0; i < 2; i++) { // 가로, 대각선 확인
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (pipe[nx][ny] == 0) {
                        if (i == 0) {
                            dfs(nx, ny, 0); // 그대로 가로
                        } else {
                            if (pipe[nx - 1][ny] == 0 && pipe[nx][ny - 1] == 0) {
                                dfs(nx, ny, 2); // 대각선
                            }
                        }
                    }
                }
            }
        } else if (iam == 1) { // 세로면
            for (int i = 1; i < 3; i++) { // 대각선, 세로 확인
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (pipe[nx][ny] == 0) {
                        if (i == 2) {
                            dfs(nx, ny, 1); // 그대로 세로
                        } else {
                            if (pipe[nx - 1][ny] == 0 && pipe[nx][ny - 1] == 0) {
                                dfs(nx, ny, 2); // 대각선
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 3; i++) { // 가로, 세로, 대각선 모두 확인
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (pipe[nx][ny] == 0) {
                        if (i == 0) {
                            dfs(nx, ny, 0); // 가로
                        } else if (i == 2) {
                            dfs(nx, ny, 1); // 세로
                        } else {
                            if (pipe[nx - 1][ny] == 0 && pipe[nx][ny - 1] == 0) {
                                dfs(nx, ny, 2); // 대각선
                            }
                        }
                    }
                }
            }
        }
    }
}

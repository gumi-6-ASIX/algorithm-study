import java.util.*;
import java.io.*;

class 게임 {
    static int n, m;
    static int[][] map;
    static int[][] dp;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static boolean INF = false;

    static int dfs(int ci, int cj) {
        if (visited[ci][cj]) {
            return -1;
        }

        if (dp[ci][cj] != 0) { // 이미 계산한 결과가 있는 경우 그 값을 반환
            return dp[ci][cj];
        }

        visited[ci][cj] = true;

        int warp = map[ci][cj];
        int max = 0;
        for (int d = 0; d < 4; d++) {
            int ni = ci + di[d] * warp;
            int nj = cj + dj[d] * warp;
            if (isValid(ni, nj)) {
                int result = dfs(ni, nj); // 다음 위치에서의 최대 이동 횟수를 구함
                if (result == -1) {
                    return -1;
                }
                max = Math.max(max, result + 1); // 현재 위치의 최대 이동 횟수 갱신
            }
        }

        visited[ci][cj] = false;
        return dp[ci][cj] = max; // 현재 위치의 최대 이동 횟수를 저장하고 반환
    }

    static boolean isValid(int ni, int nj) {
        return ni >= 0 && nj >= 0 && ni < n && nj < m && map[ni][nj] != -1; // 이러면 갈 수 있는 곳
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                char tmp = row.charAt(j);
                if (tmp == 'H') {
                    map[i][j] = -1; // 구멍을 -1로 만든다.
                } else {
                    map[i][j] = tmp - '0';
                }
            }
        }

        int result = dfs(0, 0); // 시작 위치에서의 최대 이동 횟수 구하기
        if (result == -1) {
            System.out.println(-1);
        } else {
            System.out.println(result + 1);
        }

    }
}
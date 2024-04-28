import java.util.*;
import java.io.*;

class 로봇조종하기 {
    static int n, m;
    // static int[] di = { 0, 1, 0 }; // 오, 아, 왼
    // static int[] dj = { 1, 0, -1 };
    static int[][] map;
    static int[][] dp;

    // 실패
    // static int dfs(int ci, int cj) {
    // if (ci == n - 1 && cj == m - 1) { // 종료조건
    // return map[ci][cj];
    // }
    // if (dp[ci][cj] != -1000)
    // return dp[ci][cj]; // 이미 계산한 경우 리턴
    //
    // dp[ci][cj] = map[ci][cj]; // 현재 위치 값으로 초기화
    // for (int i = 0; i < 3; i++) {
    // int ni = di[i] + ci;
    // int nj = dj[i] + cj;
    // if (isValid(ni, nj)) {
    // dp[ci][cj] = Math.max(map[ci][cj], dfs(ni, nj) + map[ci][cj]); // 다음 위치의 최대값에
    // 현재 위치 값을 더해줌
    // }
    // }
    // return dp[ci][cj];
    // }
    //
    // public static boolean isValid(int ni, int nj) {
    // return ni < n && nj < m && ni >= 0 && nj >= 0;
    // }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int dir[][] = new int[2][m + 2];

        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = map[1][1]; // 시작지점 초기화
        for (int i = 2; i <= m; i++) {
            // 맨 윗줄에서 가장 큰 값 찾기 (내려가면서 탐색할거니까)
            dp[1][i] = dp[1][i - 1] + map[1][i];
        }

        for (int i = 2; i <= n; i++) {
            // 맨 왼쪽이 가질 값은 위에서 내려오는 값
            // tmp는 왼쪽에서 오는 경로, 오른쪽에서 오는 경로 두 가지로 나눈다.
            dir[0][0] = dp[i - 1][1]; // 왼쪽부터 출발

            for (int j = 1; j <= m; j++) {
                dir[0][j] = Math.max(dir[0][j - 1], dp[i - 1][j]) + map[i][j];
            }
            dir[1][m + 1] = dp[i - 1][m]; // 이제 오른쪽 부터 훑는다.
            for (int j = m; j >= 1; j--) {
                dir[1][j] = Math.max(dir[1][j + 1], dp[i - 1][j]) + map[i][j];
            }

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dir[0][j], dir[1][j]);
            }
        }
        System.out.println(dp[n][m]);
    }
}
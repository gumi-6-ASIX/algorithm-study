import java.util.*;
import java.io.*;

class 내려가기 {
    static int n;
    static int[][] map;
    static boolean[][] v;
    static int min;
    static int max;

    static void dfs(int ci, int cj, int acc) {
        // 양옆으로 보내기
        if (ci == n) {
            min = Math.min(min, acc);
            max = Math.max(max, acc);
            return;
        }

        if (isVaild(ci + 1, cj + 1)) {
            v[ci + 1][cj + 1] = true;
            dfs(ci + 1, cj + 1, acc + map[ci + 1][cj + 1]);
            v[ci + 1][cj + 1] = false;
        }
        if (isVaild(ci + 1, cj - 1)) {
            v[ci + 1][cj - 1] = true;
            dfs(ci + 1, cj - 1, acc + map[ci + 1][cj - 1]);
            v[ci + 1][cj - 1] = false;
        }
        if (isVaild(ci + 1, cj)) {
            v[ci + 1][cj] = true;
            dfs(ci + 1, cj, acc + map[ci + 1][cj]);
            v[ci + 1][cj] = false;
        }
    }

    static boolean isVaild(int ni, int nj) {
        if (ni <= n && ni > 0 && nj < 3 && nj >= 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][3];
        v = new boolean[n + 1][3];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
        // 입력 끝
        // dfs(0, 0, 0);
        // dfs(0, 1, 0);
        // dfs(0, 2, 0);
        int[][] minDp = new int[n + 1][3];
        int[][] maxDp = new int[n + 1][3];

        minDp[0][0] = map[0][0];
        minDp[0][1] = map[0][1];
        minDp[0][2] = map[0][2];
        maxDp[0][0] = map[0][0];
        maxDp[0][1] = map[0][1];
        maxDp[0][2] = map[0][2];

        for (int i = 1; i <= n; i++) {
            minDp[i][0] += Math.min(minDp[i - 1][0], minDp[i - 1][1]) + map[i][0];
            minDp[i][1] += Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + map[i][1];
            minDp[i][2] += Math.min(minDp[i - 1][1], minDp[i - 1][2]) + map[i][2];
        }

        for (int i = 1; i <= n; i++) {
            maxDp[i][0] += Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + map[i][0];
            maxDp[i][1] += Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + map[i][1];
            maxDp[i][2] += Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + map[i][2];
        }
        max = maxDp[n][0];
        for (int i = 0; i < 3; i++) {
            max = Math.max(maxDp[n][i], max);
            min = Math.min(minDp[n][i], min);
        }

        System.out.println(max + " " + min);
    }
}
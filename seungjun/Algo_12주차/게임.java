package Baekjoon.Algo_12주차;

import java.io.*;
import java.util.*;

public class 게임 {

    static int n, m, result = Integer.MIN_VALUE;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[][] num, visited;
    static boolean[][] me;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        visited = new int[n][m];
        me = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String arr = br.readLine();
            for (int j = 0; j < m; j++) {
                if (arr.charAt(j) == 'H') {
                    num[i][j] = 0;
                } else {
                    num[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
                }

            }
        }
        me[0][0] = true;
        dfs(0, 0, 1);
        System.out.println(result == Integer.MIN_VALUE ? 1 : result);
    }

    public static void dfs(int a, int b, int cnt) {
        visited[a][b] = cnt;
        for (int i = 0; i < 4; i++) {
            int x = (dx[i] * num[a][b]) + a;
            int y = (dy[i] * num[a][b]) + b;
            if (check(x, y)) {
                if (me[x][y] == true) {
                    System.out.println(-1);
                    System.exit(0);
                }
                if (num[x][y] != 0) {
                    if (visited[x][y] < cnt + 1) {
                        visited[x][y] = cnt + 1;
                        me[x][y] = true;
                        result = Math.max(visited[x][y], result);
                        dfs(x, y, cnt + 1);
                    }
                }
            }
        }
        me[a][b] = false;
    }

    public static boolean check(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}

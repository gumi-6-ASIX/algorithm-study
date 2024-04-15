package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1103_게임 {
    static int[][] graph;
    static int[] dx;
    static int[] dy;

    static int n;
    static int m;
    static int result;
    static int[][] dp;
    static boolean[][] visited;
    static boolean flag;

    public static class Node {
        int x, y, value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];
        flag = false;
        for (int i = 0; i < n; i++) {
            String[] stg = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (stg[j].equals("H")) {
                    graph[i][j] = -1;
                } else {
                    graph[i][j] = Integer.parseInt(stg[j]);
                }
            }

        }

        result = 0;
        dfs(new Node(0, 0, graph[0][0]), 1);

        if (flag) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    public static void dfs(Node start, int depth) {
        if (result < depth) {
            result = depth;
        }

        dp[start.x][start.y] = depth;
        int[] dx = makeDist(false, start.value);
        int[] dy = makeDist(true, start.value);
        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];

            if (nx < 0 | nx >= n | ny < 0 | ny >= m)
                continue;

            if (visited[nx][ny]) {
                flag = true;
                return;
            }

            if (dp[nx][ny] > depth)
                continue;

            if (graph[nx][ny] != -1) {
                visited[nx][ny] = true;
                dfs(new Node(nx, ny, graph[nx][ny]), depth + 1);
                visited[nx][ny] = false;
            }
        }

    }

    public static int[] makeDist(boolean flag, int value) {
        if (flag) {
            return new int[] { 0, 0, -value, value };
        } else {
            return new int[] { -value, value, 0, 0 };
        }
    }
}

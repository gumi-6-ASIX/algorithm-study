package 백준.java.골드;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1245_농장관리 {

    static int[][] graph;
    static boolean[][] visited;

    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int n;
    static int m;

    static int answer;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    bfs(new Node(i, j), graph[i][j]);
                }
            }
        }

        System.out.println(answer);
    }

    public static void bfs(Node start, int value) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.x][start.y] = true;

        boolean flag = true;

        while (!queue.isEmpty()) {
            Node q = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = q.x + dx[i];
                int ny = q.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (graph[nx][ny] > value) {
                    flag = false;
                }

                if (graph[nx][ny] == value && !visited[nx][ny]) {
                    queue.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        if (flag) {
            answer++;
        }
    }
}

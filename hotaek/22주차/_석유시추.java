package 프로그래머스.java.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class _석유시추 {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[] answer = new int[500];
    static int real = 0;
    static boolean[][] visited;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;

        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0 || visited[i][j])
                    continue;
                bfs(n, m, land, new Node(i, j));

            }
        }

        return real;
    }

    public static void bfs(int n, int m, int[][] land, Node start) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(start);

        visited[start.x][start.y] = true;
        int[] tmp = { start.y, 0 };

        int result = 1;
        while (!queue.isEmpty()) {
            Node q = queue.poll();

            if (q.y < tmp[0]) {
                tmp[0] = q.y;
            }

            if (q.y > tmp[1]) {
                tmp[1] = q.y;
            }

            for (int i = 0; i < 4; i++) {
                int nx = q.x + dx[i];
                int ny = q.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (land[nx][ny] == 0 || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny));
                result++;
            }
        }

        for (int i = tmp[0]; i <= tmp[1]; i++) {
            answer[i] += result;
            if (real < answer[i]) {
                real = answer[i];
            }
        }
    }
}

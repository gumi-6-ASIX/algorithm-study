
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _17836_공주님을구해라 {
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static int[][] graph;
    static int n;
    static int m;
    static int T;
    static int answer;

    static class Node {
        int x, y;

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
        T = Integer.parseInt(st.nextToken());
        answer = T + 1;
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] visited = new int[n][m];
        bfs(visited);

        if (answer > T) {
            System.out.println("Fail");
        } else {
            System.out.println(answer);
        }
    }

    public static void bfs(int[][] visited) {
        Queue<Node> queue = new LinkedList<>();

        int[] sword = new int[2];
        boolean swordCheck = false;
        queue.offer(new Node(0, 0));
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Node q = queue.poll();

            int time = visited[q.x][q.y] + 1;

            if (time >= T) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = q.x + dx[i];
                int ny = q.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (graph[nx][ny] == 1)
                    continue;

                if (graph[nx][ny] == 2) {
                    swordCheck = true;
                    sword[0] = nx;
                    sword[1] = ny;
                }

                if (visited[nx][ny] == 0 | visited[nx][ny] > time) {
                    visited[nx][ny] = time;
                    queue.offer(new Node(nx, ny));
                }

            }
        }

        if (visited[n - 1][m - 1] != 0 && visited[n - 1][m - 1] < answer) {
            answer = visited[n - 1][m - 1];
        }
        int swordValue = visited[sword[0]][sword[1]] + (n - 1 - sword[0]) + (m - 1 - sword[1]);

        if (answer > swordValue && swordCheck == true) {
            answer = swordValue;
        }

    }

}

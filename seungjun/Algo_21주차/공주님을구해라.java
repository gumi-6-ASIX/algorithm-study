import java.io.*;
import java.util.*;

public class 공주님을구해라 {

    static int n, m, t, p;
    static int[][] num;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static Queue<Node> queue = new LinkedList<>();

    static class Node {
        int x;
        int y;
        int dep;

        Node(int x, int y, int dep) {
            this.x = x;
            this.y = y;
            this.dep = dep;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        num = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        queue.offer(new Node(0, 0, 0));
        visited[0][0] = true;
        int result = bfs();
        if (t >= result && result != 0) {
            System.out.println(result);
        } else {
            System.out.println("Fail");
        }

    }

    public static int bfs() {
        while (!queue.isEmpty()) {
            Node where = queue.poll();
            if (num[where.x][where.y] == 2) {
                p = where.dep + n - 1 - where.x + m - 1 - where.y;
            }
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + where.x;
                int y = dy[i] + where.y;
                if (check(x, y)) {
                    if (!visited[x][y] && (num[x][y] == 0 || num[x][y] == 2)) {
                        if (x == n - 1 && y == m - 1) {
                            if (p != 0) {
                                return Math.min(where.dep + 1, p);
                            } else {
                                return where.dep + 1;
                            }

                        }
                        queue.offer(new Node(x, y, where.dep + 1));
                        visited[x][y] = true;
                    }
                }
            }
        }
        return p;
    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

}

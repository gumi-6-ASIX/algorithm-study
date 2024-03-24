import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class 벽부수고이동하기 {
    static class Node {
        int x;
        int y;
        boolean bomb;
        int cnt;

        Node(int x, int y, boolean bomb, int cnt) {
            this.x = x;
            this.y = y;
            this.bomb = bomb;
            this.cnt = cnt;
        }

    }

    static int[][] num;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int n, m;
    static boolean[][][] vistied;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        vistied = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            String arr = br.readLine();
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        queue.offer(new Node(0, 0, false, 1));
        vistied[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node data = queue.poll();

            if (data.x == (n - 1) && data.y == (m - 1)) {
                return data.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int x = dx[i] + data.x;
                int y = dy[i] + data.y;
                if (check(x, y)) {
                    if (data.bomb) {
                        if (num[x][y] == 0 && !vistied[x][y][1]) {
                            vistied[x][y][1] = true;
                            queue.offer(new Node(x, y, true, data.cnt + 1));
                        }
                    } else {
                        if (num[x][y] == 1) {
                            vistied[x][y][1] = true;
                            queue.offer(new Node(x, y, true, data.cnt + 1));
                        } else if (!vistied[x][y][0]) {
                            vistied[x][y][0] = true;
                            queue.offer(new Node(x, y, false, data.cnt + 1));
                        }

                    }
                }
            }

        }
        return -1;
    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }
}

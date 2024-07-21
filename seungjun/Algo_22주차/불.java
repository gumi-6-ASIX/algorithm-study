import java.io.*;
import java.util.*;

public class 불 {

    static int n, w, h;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Node me;
    static char[][] map;
    static Queue<Node> q;

    static class Node {
        int x;
        int y;
        int me;

        Node(int x, int y, int me) {
            this.x = x;
            this.y = y;
            this.me = me;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int j = 0; j < h; j++) {
                String arr = br.readLine();
                map[j] = arr.toCharArray();
                for (int k = 0; k < w; k++) {
                    if (map[j][k] == '@') {
                        me = new Node(j, k, 1);
                    } else if (map[j][k] == '*') {
                        q.offer(new Node(j, k, 0));
                    }
                }
            }
            q.offer(me);
            int result = bfs();
            if (result == 0) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }
        }

    }

    public static int bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + now.x;
                int y = dy[i] + now.y;
                if (now.me == 0) {
                    if (check(x, y) && (map[x][y] == '.' || map[x][y] == '@')) {
                        map[x][y] = '*';
                        q.offer(new Node(x, y, 0));
                    }
                } else {
                    if (!check(x, y)) {
                        return now.me;
                    } else {
                        if (map[x][y] == '.') {
                            map[x][y] = '@';
                            q.offer(new Node(x, y, now.me + 1));
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < h && y >= 0 && y < w) {
            return true;
        }
        return false;
    }
}

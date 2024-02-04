import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 아기상어2 {

    public static int n;
    public static int m;
    public static int[][] num;
    public static int[][] dis;
    public static int[] where = new int[3];
    public static int miin = Integer.MIN_VALUE;
    public static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
    public static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
    public static boolean[][] visited;
    public static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        num = new int[n][m];
        dis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (num[i][j] == 1) {
                    visited = new boolean[n][m];
                    bfs(i, j);

                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (num[i][j] == 0) {
                    miin = Math.max(miin, dis[i][j]);
                }

            }
        }
        System.out.println(miin);

    }

    public static void bfs(int a, int b) {
        int move = 0;
        queue.offer(new int[] { a, b, move });
        visited[a][b] = true;

        while (!queue.isEmpty()) {

            where = queue.poll();
            for (int i = 0; i < 8; i++) {
                int x = where[0] + dx[i];
                int y = where[1] + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
                    if (num[x][y] == 0) {
                        if (dis[x][y] == 0) {
                            dis[x][y] = where[2] + 1;
                        } else {
                            dis[x][y] = Math.min(where[2] + 1, dis[x][y]);
                        }
                        visited[x][y] = true;
                        queue.offer(new int[] { x, y, where[2] + 1 });
                    }
                }
            }
        }
    }
}
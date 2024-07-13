import java.io.*;
import java.util.*;

public class 농장관리 {
    static int n, m, cnt;
    static int[][] num;
    static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static boolean[][] visited;
    static boolean flag;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    queue.offer(new int[] { i, j });
                    if (bfs(num[i][j])) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
        ;
    }

    static boolean bfs(int maax) {
        flag = true;
        while (!queue.isEmpty()) {
            int[] where = queue.poll();
            for (int i = 0; i < 8; i++) {
                int x = dx[i] + where[0];
                int y = dy[i] + where[1];
                if (check(x, y) && num[x][y] != 0) {
                    if (num[x][y] > maax) {
                        flag = false;
                    } else if (!visited[x][y] && num[x][y] == num[where[0]][where[1]]) {
                        queue.offer(new int[] { x, y });
                        visited[x][y] = true;
                    }
                }
            }
        }
        return flag;
    }

    static boolean check(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }
}

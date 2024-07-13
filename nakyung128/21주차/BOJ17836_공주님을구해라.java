import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836_공주님을구해라 {

    static int n, m;
    static int[][] map;
    static int t;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        int[] knife = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    knife[0] = i;
                    knife[1] = j;
                }
            }
        }

        q = new LinkedList<>();
        q.add(new int[] { 0, 0 });
        visited[0][0] = true;

        bfs();

        int using_knife = 0;
        int answer = map[n - 1][m - 1];

        if (visited[knife[0]][knife[1]]) {
            using_knife = map[knife[0]][knife[1]] + Math.abs(knife[0] - (n - 1)) + Math.abs(knife[1] - (m - 1));
            if (answer == 0 && using_knife <= t) {
                System.out.println(using_knife);
            } else if (answer > 0) {
                answer = Math.min(using_knife, map[n - 1][m - 1]);
                if (answer <= t) {
                    System.out.println(answer);
                } else {
                    System.out.println("Fail");
                }
            } else {
                System.out.println("Fail");
            }
        } else {
            if (answer > 0 && answer <= t) {
                System.out.println(map[n - 1][m - 1]);
            } else {
                System.out.println("Fail");
            }
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (isValid(nx, ny) && map[nx][ny] != 1) {
                    q.add(new int[] { nx, ny });
                    map[nx][ny] = map[now[0]][now[1]] + 1;
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
            return true;
        }
        return false;
    }
}
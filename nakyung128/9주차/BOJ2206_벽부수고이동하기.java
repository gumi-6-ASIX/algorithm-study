import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    boolean broken;
    int cnt;

    Point(int x, int y, boolean broken, int cnt) {
        this.x = x;
        this.y = y;
        this.broken = broken;
        this.cnt = cnt;
    }
}

public class BOJ2206_벽부수고이동하기 {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Point> queue;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        // visited[][][0] -> 벽 부순 적 없을 때의 visited
        // visited[][][1] -> 벽 부순 적 있을 때의 visited
        visited = new boolean[n][m][2];
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.offer(new Point(0, 0, false, 1)); // 시작하는 칸 포함하니까 1
        System.out.println(bfs());

    }

    static int bfs() {
        while (!queue.isEmpty()) {
            Point me = queue.poll();

            if (me.x == n - 1 && me.y == m - 1) {
                return me.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = me.x + dx[i];
                int ny = me.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 벽 부순 적 있으면
                    if (me.broken) {
                        // 방문한 적 없고 벽이 아니면 이동
                        if (!visited[nx][ny][1] && map[nx][ny] == 0) {
                            visited[nx][ny][1] = true;
                            queue.offer(new Point(nx, ny, true, me.cnt + 1));
                        }
                    } else { // 지금까지 벽 부순 적 없으면
                        if (map[nx][ny] == 1) { // 벽이면 부수기
                            visited[nx][ny][1] = true;
                            queue.offer(new Point(nx, ny, true, me.cnt + 1));
                        } else if (!visited[nx][ny][0] && map[nx][ny] == 0) {
                            // 방문한 적 없고 벽이 아니면
                            visited[nx][ny][0] = true;
                            queue.offer(new Point(nx, ny, false, me.cnt + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}
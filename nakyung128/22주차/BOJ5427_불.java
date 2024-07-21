import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427_불 {
    static int n, h, w;
    static char[][] map;
    static int[][] meSec, fireSec;
    static Queue<int[]> q, fire;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < n; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            meSec = new int[h][w];
            fireSec = new int[h][w];

            q = new LinkedList<>();
            fire = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '@') {
                        q.add(new int[] { i, j });
                    } else if (map[i][j] == '*') {
                        fire.add(new int[] { i, j });
                    }
                }
            }

            answer = Integer.MAX_VALUE;

            bfs();

            if (answer == Integer.MAX_VALUE) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(answer);
            }
        }
    }

    static void bfs() {

        // 불 시간
        while (!fire.isEmpty()) {
            int[] f = fire.poll();

            for (int i = 0; i < 4; i++) {
                int nx = f[0] + dx[i];
                int ny = f[1] + dy[i];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                    if (fireSec[nx][ny] == 0 && map[nx][ny] != '#') {
                        fireSec[nx][ny] = fireSec[f[0]][f[1]] + 1;
                        fire.add(new int[] { nx, ny });
                    }
                }
            }
        }

        // 상근이 시간
        while (!q.isEmpty()) {
            int[] me = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = me[0] + dx[i];
                int ny = me[1] + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    answer = Math.min(answer, meSec[me[0]][me[1]] + 1);
                    break;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = me[0] + dx[i];
                int ny = me[1] + dy[i];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                    if (map[nx][ny] == '.' && meSec[nx][ny] == 0) {
                        // 불 안 붙었거나 상근이가 가는 시간보다 나중에 붙은 불이라면 움직일 수 있음
                        if (fireSec[nx][ny] == 0 || fireSec[nx][ny] > meSec[me[0]][me[1]] + 1) {
                            meSec[nx][ny] = meSec[me[0]][me[1]] + 1;
                            q.add(new int[] { nx, ny });
                        }
                    }
                }
            }
        }
    }
}

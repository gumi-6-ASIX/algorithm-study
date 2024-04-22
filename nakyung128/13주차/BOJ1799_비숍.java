import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1799_비숍 {
    static int n;
    static int[][] map;
    static boolean[] visited, visited2;
    static int black_sum, white_sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        black_sum = 0;
        white_sum = 0;

        visited = new boolean[2 * n - 1];
        visited2 = new boolean[2 * n - 1];
        black_choice(0, 0, 0);
        white_choice(0, 1, 0);

        System.out.println(black_sum + white_sum);
    }

    static void black_choice(int x, int y, int cnt) {
        if (cnt > 2 * n - 2) {
            return;
        } else {
            black_sum = Math.max(black_sum, cnt);
        }

        for (int i = x; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j += 2) {
                    if (!visited[i + j] && !visited2[n - 1 + i - j] && map[i][j] == 1) {
                        visited[i + j] = true;
                        visited2[n - 1 + i - j] = true;
                        black_choice(i, j, cnt + 1);
                        visited[i + j] = false;
                        visited2[n - 1 + i - j] = false;
                    }
                }
            } else {
                for (int j = 1; j < n; j += 2) {
                    if (!visited[i + j] && !visited2[n - 1 + i - j] && map[i][j] == 1) {
                        visited[i + j] = true;
                        visited2[n - 1 + i - j] = true;
                        black_choice(i, j, cnt + 1);
                        visited[i + j] = false;
                        visited2[n - 1 + i - j] = false;
                    }
                }
            }
        }
    }

    static void white_choice(int x, int y, int cnt) {
        if (cnt > 2 * n - 2) {
            return;
        } else {
            white_sum = Math.max(white_sum, cnt);
        }
        for (int i = x; i < n; i++) {
            if (i % 2 != 0) {
                for (int j = 0; j < n; j += 2) {
                    if (!visited[i + j] && !visited2[n - 1 + i - j] && map[i][j] == 1) {
                        visited[i + j] = true;
                        visited2[n - 1 + i - j] = true;
                        white_choice(i, j, cnt + 1);
                        visited[i + j] = false;
                        visited2[n - 1 + i - j] = false;
                    }
                }
            } else {
                for (int j = 1; j < n; j += 2) {
                    if (!visited[i + j] && !visited2[n - 1 + i - j] && map[i][j] == 1) {
                        visited[i + j] = true;
                        visited2[n - 1 + i - j] = true;
                        white_choice(i, j, cnt + 1);
                        visited[i + j] = false;
                        visited2[n - 1 + i - j] = false;
                    }
                }
            }
        }
    }
}
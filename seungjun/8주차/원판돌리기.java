import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, t;
    static int[][] num;
    static int[] arr;
    static int[][] roll;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Queue<int[]> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        roll = new int[t][3];
        arr = new int[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                roll[i][j] = Integer.parseInt(st.nextToken());
            }
            spin(roll[i][0], roll[i][1], roll[i][2]);
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += num[i][j];
            }
        }
        System.out.println(result);

    }

    public static void spin(int x, int d, int k) {
        for (int i = 0; i < n; i++) {
            if ((i + 1) % x == 0) {
                for (int j = 0; j < m; j++) {
                    if (d == 0) {
                        arr[(j + k) % m] = num[i][j];
                    } else {
                        arr[(j + (m - k)) % m] = num[i][j];
                    }
                }
                num[i] = arr.clone();
            }
        }
        // System.out.println("돌린후");
        // for (int i = 0; i < n; i++) {

        // for (int j = 0; j < m; j++) {
        // System.out.printf("%d ", num[i][j]);
        // }
        // System.out.println();
        // }
        visited = new boolean[n][m];
        queue = new LinkedList<>();
        find();
    }

    public static void find() {
        int cnt = 0;
        int sum = 0;
        int no = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (num[i][j] != 0) {
                    no++;
                }
                sum += num[i][j];
                if (num[i][j] != 0) {
                    queue.offer(new int[] { i, j });
                    int flag = num[i][j];
                    while (!queue.isEmpty()) {
                        int[] where = queue.poll();
                        for (int q = 0; q < 4; q++) {
                            int x = dx[q] + where[0];
                            int b = dy[q] + where[1];
                            int y = b % m;
                            if (y == -1)
                                y = m - 1;
                            // System.out.printf("%d %d \n", x, y);
                            if (checked(x) && num[x][y] == flag) {
                                num[i][j] = 0;
                                cnt++;
                                queue.offer(new int[] { x, y });
                                num[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
        if (cnt == 0) {

            double my = (double) sum / (double) no;
            // System.out.println(no + "남아잇는 개수" + sum + "합" + my);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (num[i][j] != 0) {
                        if (num[i][j] > my) {
                            num[i][j]--;
                        } else if (num[i][j] < my) {
                            num[i][j]++;
                        }

                    }
                }
            }
        }
        // System.out.println("지운후");
        // for (int i = 0; i < n; i++) {

        // for (int j = 0; j < m; j++) {
        // System.out.printf("%d ", num[i][j]);
        // }
        // System.out.println();
        // }
    }

    public static boolean checked(int x) {
        if (x >= 0 && x < n) {
            return true;
        }
        return false;
    }
}

import java.io.*;
import java.util.*;

public class 원판돌리기17822 {

    static int n;
    static int m;
    static int[][] circle;
    static int cnt;

    /**
     * 맨 뒤 빼서 맨 앞으로 가져오고 하나씩 다 밀면 시계방향 회전
     * 
     * @param arr
     */
    public static void clock(int[] arr) {
        int tmp = arr[m - 1];
        for (int i = m - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;
    }

    /*
     * 반시계
     * 
     * @param arr
     */
    public static void counterClock(int[] arr) {
        int tmp = arr[0];
        for (int i = 0; i < m - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[m - 1] = tmp;
    }

    public static void roll(int di, int xi, int ki) {
        if (di == 0) {
            // 시계방향 xi배수인 원판을 ki칸 회전
            for (int j = 0; j < n; j++) {
                // j+1번째가 x배수냐?
                if ((j + 1) % xi == 0) {
                    for (int k = 0; k < ki; k++) {
                        clock(circle[j]);
                    }
                }
            }
        } else {
            // 반시계방향
            for (int j = 0; j < n; j++) {
                // j+1번째가 x배수냐?
                if ((j + 1) % xi == 0) {
                    for (int k = 0; k < ki; k++) {
                        counterClock(circle[j]);
                    }
                }
            }
        }
    }

    /**
     * column 쭉 돌면서 인접한수가 같으면 둘다 없애기
     */
    public static boolean check() {
        int[][] adj = new int[n][m]; // 1로 표시하고, 나중에 1인거 싹다 0으로 밀기
        boolean find = false;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                // 같은 원판에서 인접하는 것도 포함...
                if (circle[i][j] != 1001 && circle[i][j] == circle[i + 1][j]) {
                    adj[i][j] = 1;
                    adj[i + 1][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != 1001 && j < m - 1 && circle[i][j] == circle[i][j + 1]) {
                    adj[i][j] = 1;
                    adj[i][j + 1] = 1;
                }
                if (j == m - 1) {
                    if (circle[i][j] != 1001 && circle[i][j] == circle[i][0]) {
                        adj[i][j] = 1;
                        adj[i][0] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (adj[i][j] == 1) {
                    circle[i][j] = 1001;
                    find = true;
                }
            }
        }
        return find;
    }

    public static int sum() {
        cnt = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != 1001) {
                    cnt++;
                    res += circle[i][j];
                }
            }
        }
        return res;
    }

    /**
     * 원판 적힌 수 평균 구해서 평균보다 큰수에서 1빼고, 작은수에 1더함
     * 
     * @param avg
     */
    public static void process(double avg) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != 1001 && circle[i][j] > avg) {
                    circle[i][j]--;
                } else if (circle[i][j] != 1001 && circle[i][j] < avg) {
                    circle[i][j]++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int result = 0;
        circle = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] xdk = new int[t][3]; // i번째의 x, d, k
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                xdk[i][j] = Integer.parseInt(st.nextToken()); // x, d, k 순서대로 들어옴
            }
            int xi = xdk[i][0];
            int di = xdk[i][1];
            int ki = xdk[i][2];
            roll(di, xi, ki);
            boolean isAdj = check();
            double res = sum();

            if (!isAdj) {
                // 원판 적힌 수 평균 구해서 평균보다 큰수에서 1빼고, 작은수에 1더함
                double avg = res / cnt;
                process(avg);
                result = (int) sum();
            } else {
                result = (int) res;
            }
        }
        System.out.println(result);
    }
}
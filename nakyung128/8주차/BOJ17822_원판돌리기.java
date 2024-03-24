import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17822_원판돌리기 {
    static int n, m, t;
    static int[][] circle;
    static int[][] check;
    static ArrayList<ArrayList<Integer>> dirs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        circle = new int[n + 1][m];
        dirs = new ArrayList<>();

        // 원판이 1번부터 시작하니까 0번째 행은 -1로 채워주기
        Arrays.fill(circle[0], -1);

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 방법 dirs에 담기
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            dirs.add(arr);
        }

        for (ArrayList<Integer> arr : dirs) {
            int x = arr.get(0); // x의 배수
            int d = arr.get(1); // 방향
            int k = arr.get(2); // 도는 횟수

            if (d == 0) {
                // 원판 돌리기 (시계)
                clockwise(x, k);
            } else {
                // 원판 돌리기 (반시계)
                anclockwise(x, k);
            }
        }

        // T번 회전했으면 적힌 수의 합 구하기
        int answer = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != -1) {
                    answer += circle[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    // 인접하면서 수가 같은 것 찾기
    static boolean same(int x, int y) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // (i, 0) 일 때 인접한 것 인덱스 바꿔 주기
        if (y == 0) {
            dy[2] = m - 1;
        }

        // (i, m) 일 때 인접한 것 인덱스 바꿔 주기
        if (y == m - 1) {
            dy[3] = 1 - m;
        }

        boolean flag = false;

        // 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내일 때
            if (nx >= 0 && nx < n + 1 && ny >= 0 && ny < m) {
                if (circle[nx][ny] == circle[x][y]) {
                    flag = true;
                    check[nx][ny] = 1; // 인접하고 수가 같은 곳 check 배열 갱신해 주기
                }
            }
        }

        // 인접하면서 같은 수가 있는 경우
        if (flag) {
            check[x][y] = 1; // 나 자신도 1로
        }

        return flag;
    }

    // 시계 방향 회전
    static void clockwise(int x, int k) {

        for (int i = 1; i < n + 1; i++) {
            if (i % x == 0) { // 돌릴 원판이면
                // k칸 회전 (돌려돌려)
                for (int j = 0; j < k; j++) {
                    int temp = circle[i][m - 1];

                    for (int l = m - 1; l > 0; l--) {
                        circle[i][l] = circle[i][l - 1];
                    }

                    circle[i][0] = temp;
                }
            }
        }

        // 전체 돌면서 인접하면서 수 같은 것 지워주기
        boolean change = false;
        check = new int[n + 1][m];

        for (int r = 1; r < n + 1; r++) {
            for (int c = 0; c < m; c++) {
                if (circle[r][c] != -1) {
                    if (same(r, c)) {
                        change = true;
                    }
                }
            }
        }

        // 지워진 것이 없으면 평균
        if (!change) {
            avg();
        } else { // 있으면 check 된 것 -1로 변경
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < m; j++) {
                    if (check[i][j] == 1) {
                        circle[i][j] = -1;
                    }
                }
            }
        }
    }

    // 반시계 방향 회전
    static void anclockwise(int x, int k) {
        for (int i = 1; i < n + 1; i++) {
            if (i % x == 0) { // 돌릴 원판이면
                // k칸 회전 (돌려돌려)
                for (int j = 0; j < k; j++) {
                    int temp = circle[i][0];

                    for (int l = 1; l < m; l++) {
                        circle[i][l - 1] = circle[i][l];
                    }

                    circle[i][m - 1] = temp;
                }
            }
        }

        // 전체 돌면서 인접하면서 수 같은 것 지워주기
        boolean change = false;
        check = new int[n + 1][m];

        for (int r = 1; r < n + 1; r++) {
            for (int c = 0; c < m; c++) {
                if (circle[r][c] != -1) {
                    if (same(r, c)) {
                        change = true;
                    }
                }
            }
        }

        if (!change) {
            avg();
        } else {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < m; j++) {
                    if (check[i][j] == 1) {
                        circle[i][j] = -1;
                    }
                }
            }
        }
    }

    static void avg() {
        // 전체 평균보다 작으면 + 1
        // 크면 - 1

        double sum = 0;
        double cnt = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != -1) {
                    sum += circle[i][j];
                    cnt++;
                }
            }
        }

        sum /= cnt;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (circle[i][j] != -1) {
                    if (circle[i][j] > sum) {
                        circle[i][j]--;
                    } else if (circle[i][j] == sum) {
                        continue;
                    } else {
                        circle[i][j]++;
                    }
                }
            }
        }
    }
}
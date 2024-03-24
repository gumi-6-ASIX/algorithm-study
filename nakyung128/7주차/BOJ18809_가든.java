import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 예제 5번, 10번 틀림
 */
public class BOJ18809_가든 {
    static int n, m, g, r;
    static int[][] garden;
    static int[][] red;
    static int[][] green;
    static boolean[][] visitedR;
    static boolean[][] visitedG;
    static ArrayList<int[]> can;
    static ArrayList<int[]> selG;
    static ArrayList<int[]> selR;
    static boolean[] visited;
    static Queue<int[]> queue;
    static Queue<int[]> queue2;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        garden = new int[n][m];
        red = new int[n][m];
        green = new int[n][m];
        visitedR = new boolean[n][m];
        visitedG = new boolean[n][m];
        can = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
                if (garden[i][j] == 0) {
                    red[i][j] = -1;
                    green[i][j] = -1;
                } else if (garden[i][j] == 2) {
                    can.add(new int[] { i, j });
                }
            }
        }

        visited = new boolean[can.size()];
        answer = 0;
        selG = new ArrayList<>();
        selR = new ArrayList<>();

        selectG(0, 0);

        System.out.println(answer);
    }

    // 초록색 배양액 놓는 곳 정하기
    static void selectG(int start, int depth) {
        if (depth == g) {
            selectR(0, 0);
            return;
        }

        for (int i = start; i < can.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                selG.add(can.get(i));
                selectG(i + 1, depth + 1);
                selG.remove(selG.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 빨간색 배양액 놓는 곳 정하기
    static void selectR(int start, int depth) {
        if (depth == r) {
            // 초록색 배양액, 빨간색 배양액 둘 다 위치 정해졌으니 bfs 돌리기
            reset(); // 초기화
            int flowers = bfs(); // 배양액 뿌린 후
            answer = Math.max(answer, flowers); // 더 꽃이 많이 핀 것으로 갱신
            return;
        }

        for (int i = start; i < can.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                selR.add(can.get(i));
                selectR(i + 1, depth + 1);
                selR.remove(selR.size() - 1);
                visited[i] = false;
            }
        }

    }

    static int bfs() {
        int cnt = 0;
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // 초록색
        for (int[] s : selG) {
            queue.add(s);
            visitedG[s[0]][s[1]] = true;
        }

        // 빨간색
        for (int[] s : selR) {
            queue2.add(s);
            visitedR[s[0]][s[1]] = true;
        }

        while (!queue.isEmpty() && !queue2.isEmpty()) {
            int[] myG = queue.poll();
            int[] myR = queue2.poll();

            for (int i = 0; i < 4; i++) {
                int gx = myG[0] + dx[i];
                int gy = myG[1] + dy[i];
                int rx = myR[0] + dx[i];
                int ry = myR[1] + dy[i];

                if (gx >= 0 && gx < n && gy >= 0 && gy < m) {
                    if (!visitedG[gx][gy] && green[gx][gy] == 0) {
                        visitedG[gx][gy] = true;
                        green[gx][gy] = green[myG[0]][myG[1]] + 1;
                        queue.offer(new int[] { gx, gy });
                    }
                }

                if (rx >= 0 && rx < n && ry >= 0 && ry < m) {
                    if (!visitedR[rx][ry] && red[rx][ry] == 0) {
                        visitedR[rx][ry] = true;
                        red[rx][ry] = red[myR[0]][myR[1]] + 1;
                        queue2.offer(new int[] { rx, ry });
                    }
                }
            }

            cnt = flowers(); // 꽃의 개수 세기
        }
        return cnt;
    }

    static int flowers() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (green[i][j] == red[i][j]) {
                    if (green[i][j] > 0 && red[i][j] > 0) {
                        cnt++;
                        block(i, j); // 꽃이 피었으면 그 주변에 퍼지지 못하도록 막기
                    }
                }
            }
        }

        return cnt;
    }

    // 꽃이 핀 곳 4방향 visited true로 변경
    static void block(int x, int y) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visitedG[nx][ny]) {
                    visitedG[nx][ny] = true;
                }
                if (!visitedR[nx][ny]) {
                    visitedR[nx][ny] = true;
                }
            }
        }
    }

    // 초기화 함수
    static void reset() {
        queue = new LinkedList<>();
        queue2 = new LinkedList<>();
        red = new int[n][m];
        green = new int[n][m];
        visitedR = new boolean[n][m];
        visitedG = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (garden[i][j] == 0) {
                    red[i][j] = -1;
                    green[i][j] = -1;
                }
            }
        }
    }

}

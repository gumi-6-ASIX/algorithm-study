import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ17135_캐슬디펜스 {
    static int n, m, d;
    static int[][] graph;
    static int[][] origin;
    static ArrayList<int[]> attack;
    static boolean visited[];
    static int answer;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][m];
        origin = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        attack = new ArrayList<>();
        visited = new boolean[m];
        answer = 0;
        back(0, 0);
        System.out.println(answer);
    }

    static void back(int start, int depth) {
        if (depth == 3) {
            cnt = 0;
            reset();
            attack();
            answer = Math.max(answer, cnt);
            return;
        }

        for (int i = start; i < m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                attack.add(new int[] { n, i });
                back(i + 1, depth + 1);
                visited[i] = false;
                attack.remove(attack.size() - 1);
            }
        }
    }

    static void attack() {
        while (!isEnd()) {
            HashSet<ArrayList<Integer>> can = new HashSet<>();
            for (int[] where : attack) {
                ArrayList<int[]> goals = new ArrayList<>();
                int[] final_goal = new int[2];
                int min_dis = Integer.MAX_VALUE;
                boolean able = false;

                // 궁수의 위치
                int ax = where[0];
                int ay = where[1];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (graph[i][j] == 1) {
                            int dis = Math.abs(ax - i) + Math.abs(ay - j);
                            if (dis <= d) { // 거리가 d보다 작거나 같으면 공격 가능함
                                goals.add(new int[] { i, j });
                            }
                        }
                    }
                }

                // 공격하는 적은 d 이하인 적 중 가장 가까운 적
                // 만약 가장 가까운 적이 여러명이면 가장 왼쪽에 있는 적을 공격함
                for (int[] g : goals) {
                    // 거리
                    int dis = Math.abs(ax - g[0]) + Math.abs(ay - g[1]);
                    if (dis < min_dis) {
                        able = true;
                        min_dis = dis;
                        final_goal[0] = g[0];
                        final_goal[1] = g[1];
                    } else if (dis == min_dis) {
                        if (g[1] < final_goal[1]) {
                            able = true;
                            final_goal[0] = g[0];
                            final_goal[1] = g[1];
                        }
                    }
                }
                if (able) {
                    ArrayList<Integer> goal = new ArrayList<>();
                    goal.add(final_goal[0]);
                    goal.add(final_goal[1]);
                    can.add(goal);
                }
            }

            // 공격
            for (ArrayList<Integer> c : can) {
                graph[c.get(0)][c.get(1)] = 0;
                cnt++;
            }

            // 공격 끝났으면 적들이 한 칸 아래로 내려옴
            down();
        }

    }

    static void down() {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                graph[i + 1][j] = graph[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            graph[0][i] = 0;
        }
    }

    // 새로운 곳에 궁수 놓기 전에 그래프 처음으로 초기화
    static void reset() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = origin[i][j];
            }
        }
    }

    // 적들이 N+1 행으로 모두 내려오면 끝
    // [n][m]이 다 0이면 끝!
    static boolean isEnd() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1)
                    return false;
            }
        }
        return true;
    }
}

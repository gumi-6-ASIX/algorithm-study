import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683_감시 {
    static int n, m;
    static int[][] cctv;
    static boolean[][] see;
    static boolean[][] temp_see;
    static int[] dir;
    static ArrayList<int[]> idxs;
    static boolean[] visited;
    static ArrayList<Integer> p;
    static int answer;
    static int min_value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cctv = new int[n][m];
        see = new boolean[n][m];

        dir = new int[] { 0, 4, 2, 4, 4, 1 };
        idxs = new ArrayList<>(); // 씨씨티비 위치 넣기

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cctv[i][j] = Integer.parseInt(st.nextToken());
                if (cctv[i][j] == 1 || cctv[i][j] == 2 || cctv[i][j] == 3 || cctv[i][j] == 4 || cctv[i][j] == 5) {
                    idxs.add(new int[] { i, j });
                    see[i][j] = true;
                } else if (cctv[i][j] == 6) {
                    see[i][j] = true;
                }
            }
        }

        p = new ArrayList<>();
        visited = new boolean[idxs.size()];
        min_value = Integer.MAX_VALUE;
        select(0, idxs.size(), 0);

        System.out.println(min_value);
    }

    // cnt: 골라야 하는 개수
    // depth: 몇 번 골랐는가
    // dirList: 순열을 만들 배열
    static void select(int start, int cnt, int depth) {

        if (depth == cnt) {
            // 감시 후 남은 개수 계산하기 (가장 값이 작은 것으로 갱신하기)
            reset_temp();
            gamsi(idxs, p);
            return;
        }

        for (int i = start; i < idxs.size(); i++) {
            int[] idx = idxs.get(i);
            int num = cctv[idx[0]][idx[1]]; // cctv 번호

            for (int j = 0; j < dir[num]; j++) {
                if (!visited[i]) {
                    visited[i] = true;
                    p.add(j + 1);
                    select(start + 1, cnt, depth + 1);
                    p.remove(p.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    // cctv 번호와 방향 이용해서 감시하기
    // dir 1: 오른쪽 2: 아래 3: 왼쪽 4: 위
    static void gamsi(ArrayList<int[]> idxList, ArrayList<Integer> p) {
        for (int i = 0; i < idxList.size(); i++) {
            int[] idx = idxList.get(i); // cctv 위치
            int no = cctv[idx[0]][idx[1]]; // cctv 번호
            int how = p.get(i); // 가야 하는 방향

            int x = idx[0];
            int y = idx[1];

            if (no == 1) {
                go(x, y, how);
            } else if (no == 2) {
                if (how == 1) { // 가로로 쭈욱
                    // 좌
                    go(x, y, 3);
                    // 우
                    go(x, y, 4);
                } else { // 세로로 쭈욱
                    // 상
                    go(x, y, 1);
                    // 하
                    go(x, y, 2);
                }
            } else if (no == 3) {
                if (how == 1) { // 상, 우
                    go(x, y, 1);
                    go(x, y, 4);
                } else if (how == 2) { // 우, 하
                    go(x, y, 4);
                    go(x, y, 2);
                } else if (how == 3) { // 하, 좌
                    go(x, y, 2);
                    go(x, y, 3);
                } else { // 좌, 상
                    go(x, y, 3);
                    go(x, y, 1);
                }
            } else if (no == 4) {
                if (how == 1) { // 좌, 상, 우
                    go(x, y, 3);
                    go(x, y, 1);
                    go(x, y, 4);
                } else if (how == 2) { // 상, 우, 하
                    go(x, y, 1);
                    go(x, y, 4);
                    go(x, y, 2);
                } else if (how == 3) { // 우, 하, 좌
                    go(x, y, 4);
                    go(x, y, 2);
                    go(x, y, 3);
                } else { // 하, 좌, 상
                    go(x, y, 2);
                    go(x, y, 3);
                    go(x, y, 1);
                }
            } else {
                // 상하좌우 쭉
                go(x, y, 1);
                go(x, y, 2);
                go(x, y, 3);
                go(x, y, 4);
            }
        }

        // value: 현재 감시 끝나고 남은 false 개수
        int value = count_false();
        if (min_value > value)
            min_value = value;
    }

    static void go(int x, int y, int dir) {
        switch (dir) {
            case 1:
                // 상
                while (x >= 0 && cctv[x][y] != 6) {
                    temp_see[x][y] = true;
                    x--;
                }
                break;
            case 2:
                // 하
                while (x < n && cctv[x][y] != 6) {
                    temp_see[x][y] = true;
                    x++;
                }
                break;
            case 3:
                // 좌
                while (y >= 0 && cctv[x][y] != 6) {
                    temp_see[x][y] = true;
                    y--;
                }
                break;
            case 4:
                // 우
                while (y < m && cctv[x][y] != 6) {
                    temp_see[x][y] = true;
                    y++;
                }
                break;
        }
    }

    static int count_false() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp_see[i][j] == false)
                    result++;
            }
        }
        return result;
    }

    // temp_see 리셋
    static void reset_temp() {
        temp_see = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp_see[i][j] = see[i][j];
            }
        }
    }
}
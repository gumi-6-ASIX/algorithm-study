import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] v;
    static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8방 돌리기
    static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                // 각 칸은 격자의 높이를 의미한다.
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(sanBongWoori());
    }

    public static boolean isValid(int ni, int nj) {
        return ni < n && nj < m && ni >= 0 && nj >= 0;
    }

    static class Node {
        int i, j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static int sanBongWoori() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j] && isSanBong(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static boolean isSanBong(int si, int sj) {
        // 8방향에 다 최고높이 보다 작거나 같아야됨.
        // -> 각 산봉우리가 몇개 있는지 찾아야됨
        // 방문하지 않은 곳이 더 커지면 그거는 다른 산봉우리라고 봐야됨
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(si, sj));
        v[si][sj] = true;
        int height = map[si][sj];
        boolean isSanBong = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int d = 0; d < 8; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];
                if (isValid(ni, nj)) {
                    if (map[ni][nj] > height) {
                        // 인접한 격자 중 더 높은 곳이 있으면 산봉우리가 아님
                        isSanBong = false;
                    } else if (map[ni][nj] == height && !v[ni][nj]) {
                        v[ni][nj] = true;
                        q.add(new Node(ni, nj));
                    }
                }
            }
        }
        return isSanBong;
    }
}
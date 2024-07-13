import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, t;
    static int[][] map;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int gi = 0, gj = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int cell = Integer.parseInt(st.nextToken());
                map[i][j] = cell;
                if (cell == 2) {
                    gi = i;
                    gj = j;
                }
            }
            // 공주에게 도착할 수 있는 최단 시간을 구해야됨.
            // T시간 안에 도착하지 못한다면 FAIL
            // 0은 빈 공간, 1은 마법의 벽, 2는 그람이 놓여있는 공간을 의미한다
            // (1,1)과 (N,M)은 0이다.
        }

        int directPath = bfs(0, 0, n - 1, m - 1, false);
        int pathWithGram = findGram();

        if (directPath == -1 && pathWithGram == -1) {
            System.out.println("Fail");
        } else if (directPath == -1) {
            System.out.println(pathWithGram <= t ? pathWithGram : "Fail");
        } else if (pathWithGram == -1) {
            System.out.println(directPath <= t ? directPath : "Fail");
        } else {
            int result = Math.min(directPath, pathWithGram);
            System.out.println(result <= t ? result : "Fail");
        }
    }

    static int bfs(int startI, int startJ, int endI, int endJ, boolean hasGram) {
        boolean[][] v = new boolean[n][m];
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(startI, startJ, 0));
        v[startI][startJ] = true;

        while (!q.isEmpty()) {
            Node c = q.poll();
            if (c.i == endI && c.j == endJ) {
                return c.cost;
            }
            for (int d = 0; d < 4; d++) {
                int ni = c.i + di[d];
                int nj = c.j + dj[d];
                if (isValid(ni, nj) && !v[ni][nj] && (hasGram || map[ni][nj] != 1)) {
                    q.add(new Node(ni, nj, c.cost + 1));
                    v[ni][nj] = true;
                }
            }
        }
        return -1; // 경로를 찾지 못한 경우
    }

    static int findGram() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    int toGram = bfs(0, 0, i, j, false);
                    if (toGram != -1) {
                        int fromGramToPrincess = bfs(i, j, n - 1, m - 1, true);
                        if (fromGramToPrincess != -1) {
                            return toGram + fromGramToPrincess;
                        }
                    }
                    break;
                }
            }
        }
        return -1;
    }

    static boolean isValid(int ni, int nj) {
        return ni >= 0 && ni < n && nj >= 0 && nj < m;
    }

    static class Node {
        int i, j, cost;

        Node(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
}
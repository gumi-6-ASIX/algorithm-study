import java.io.*;
import java.util.*;

public class 벽부수고이동하기 {

    static int[][] map;
    static boolean[][][] visited;
    static int N, M;

    static class Node {
        public Node(int i, int j, int dis, int crush) {
            this.i = i;
            this.j = j;
            this.dis = dis;
            this.crush = crush;
        }

        int i;
        int j;
        int dis;
        int crush;
    }

    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 1, 0)); // i, j, depth, crash
        visited[0][0][0] = true; // 좌표랑, 부시고 가는지 확인하고 간다 1이면 부신것

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.i == N - 1 && cur.j == M - 1) {
                return cur.dis;
            }

            for (int i = 0; i < 4; i++) {
                int ni = cur.i + di[i];
                int nj = cur.j + dj[i];

                if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                    if (map[ni][nj] == 0) {
                        if (cur.crush == 1) {
                            if (!visited[ni][nj][1]) {
                                visited[ni][nj][1] = true;
                                queue.offer(new Node(ni, nj, cur.dis + 1, cur.crush));
                            }
                        } else {
                            if (!visited[ni][nj][0]) {
                                visited[ni][nj][0] = true;
                                queue.offer(new Node(ni, nj, cur.dis + 1, cur.crush));
                            }
                        }
                    }
                    if (map[ni][nj] == 1 && cur.crush == 0 && !visited[ni][nj][1]) {
                        visited[ni][nj][1] = true;
                        queue.offer(new Node(ni, nj, cur.dis + 1, 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2]; // 벽을 한 번 부술 수 있음

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int dist = bfs();
        System.out.println(dist);
    }
}

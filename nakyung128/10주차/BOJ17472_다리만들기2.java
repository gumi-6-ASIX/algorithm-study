import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17472_다리만들기2 {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n, m, island;
    static int[][] map;
    static boolean[][] visited; // dfs 돌 때 사용할 visited
    static boolean[] visited2; // mst 할 때 사용할 visited2
    static ArrayList<ArrayList<Node>> graph;
    static PriorityQueue<Node> pq;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 개수 세기
        island = 0;
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = num;
                    countIsland(i, j, num);
                    island++;
                    num++;
                }
            }
        }

        // 빈 그래프 생성
        graph = new ArrayList<>();
        for (int i = 0; i < island + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int k = 1; k <= island; k++) {
            // 현재 섬 위치들 넣기
            ArrayList<Point> nows = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == k) {
                        nows.add(new Point(i, j));
                    }
                }
            }
            // 다른 섬들이랑 거리 구하기
            findEdge(nows);
        }

        // 그래프 완성 후 최소 신장 트리 고고
        visited2 = new boolean[island + 1];
        pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        System.out.println(connect());
    }

    // 섬 개수 구하기
    static void countIsland(int x, int y, int num) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = num;
                    countIsland(nx, ny, num);
                }
            }
        }
    }

    // 섬 사이의 간선 길이 구하기
    static void findEdge(ArrayList<Point> me) {
        for (Point p : me) {
            int x = p.x;
            int y = p.y;
            int myIdx = map[p.x][p.y];

            int r_cnt = 0;
            int idx = 0;

            // 아래로 확인
            while (true) {
                x++;

                if (x >= n) {
                    r_cnt = 0;
                    break;
                }

                if (myIdx == map[x][y]) {
                    r_cnt = 0;
                    break;
                }

                if (map[x][y] != 0) {
                    idx = map[x][y];
                    break;
                }
                r_cnt++;
            }
            if (r_cnt > 1) {
                graph.get(myIdx).add(new Node(idx, r_cnt));
            }

            x = p.x;
            y = p.y;
            // 위로 확인
            r_cnt = 0;
            while (true) {
                x--;
                if (x < 0) {
                    r_cnt = 0;
                    break;
                }
                if (myIdx == map[x][y]) {
                    r_cnt = 0;
                    break;
                }

                if (map[x][y] != 0) {
                    idx = map[x][y];
                    break;
                }
                r_cnt++;
            }
            if (r_cnt > 1) {
                graph.get(myIdx).add(new Node(idx, r_cnt));
            }

            x = p.x;
            y = p.y;
            // 왼쪽 확인
            int c_cnt = 0;
            while (true) {
                y--;
                if (y < 0) {
                    c_cnt = 0;
                    break;
                }
                if (myIdx == map[x][y]) {
                    c_cnt = 0;
                    break;
                }

                if (map[x][y] != 0) {
                    idx = map[x][y];
                    break;
                }
                c_cnt++;
            }
            if (c_cnt > 1) {
                graph.get(myIdx).add(new Node(idx, c_cnt));
            }

            x = p.x;
            y = p.y;
            // 오른쪽 확인
            c_cnt = 0;
            while (true) {
                y++;

                if (y >= m) {
                    c_cnt = 0;
                    break;
                }

                if (myIdx == map[x][y]) {
                    c_cnt = 0;
                    break;
                }

                if (map[x][y] != 0) {
                    idx = map[x][y];
                    break;
                }
                c_cnt++;
            }
            if (c_cnt > 1) {
                graph.get(myIdx).add(new Node(idx, c_cnt));
            }
        }
    }

    // 다리 연결하기
    // prim 알고리즘
    static int connect() {
        int cnt = 0;
        int answer = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited2[now.idx]) {
                visited2[now.idx] = true;
                answer += now.cost;

                if (++cnt == island) {
                    break;
                }

                for (Node n : graph.get(now.idx)) {
                    if (!visited2[n.idx]) {
                        pq.offer(n);
                    }
                }
            }
        }

        // 연결 못 하면 -1
        if (cnt != island) {
            return -1;
        }
        return answer;
    }
}
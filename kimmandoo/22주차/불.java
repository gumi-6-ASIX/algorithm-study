import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static Queue<Node> fireQueue;
    static Queue<Node> personQueue;

    static class Node {
        int i, j, t;

        Node(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            visited = new boolean[n][m];
            fireQueue = new LinkedList<>();
            personQueue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '@') {
                        personQueue.add(new Node(i, j, 0));
                        visited[i][j] = true;
                    }
                    if (map[i][j] == '*') {
                        fireQueue.add(new Node(i, j, 0));
                    }
                }
            }
            int res = escape();
            if (res == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(res);
            }
        }
    }

    public static boolean isEdge(int ci, int cj) {
        return ci == 0 || cj == 0 || ci == n - 1 || cj == m - 1;
    }

    public static int escape() {
        while (!personQueue.isEmpty()) {
            // 불 확산
            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                Node fire = fireQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int ni = fire.i + di[d];
                    int nj = fire.j + dj[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] == '.') {
                        map[ni][nj] = '*';
                        fireQueue.offer(new Node(ni, nj, fire.t + 1));
                    }
                }
            }

            // 상근이 이동
            int personSize = personQueue.size();
            for (int i = 0; i < personSize; i++) {
                Node person = personQueue.poll();

                // 상근이가 가장자리에 있는 경우 탈출
                if (isEdge(person.i, person.j)) {
                    return person.t + 1;
                }

                for (int d = 0; d < 4; d++) {
                    int ni = person.i + di[d];
                    int nj = person.j + dj[d];

                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] == '.' && !visited[ni][nj]) {
                        visited[ni][nj] = true;
                        personQueue.offer(new Node(ni, nj, person.t + 1));
                    }
                }
            }
        }
        return -1;
    }
}
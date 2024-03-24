import java.io.*;
import java.util.*;

public class 아기상어 {

    static int n, cur_x, cur_y, time = 0, size = 2;
    static int[][] map;
    static boolean[][] check;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    static class Node {
        int x;
        int y;
        int move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st = null;

        int sx = 0, sy = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 9) { // 아기 상어 위치
                    sx = i;
                    sy = j;
                }
            }
        }
        bfs(sx, sy);
        System.out.println(time);
    }

    static void bfs(int x, int y) {

        int eating = 0;
        cur_x = x;
        cur_y = y;

        while (true) {
            Queue<Node> q = new LinkedList<>();
            PriorityQueue<Node> feeding = new PriorityQueue<Node>((o1, o2) -> {
                if (o1.move == o2.move) {
                    if (o1.x == o2.x) {
                        // 가장 왼쪽
                        return o1.y - o2.y;
                    } else {
                        // 가장 위쪽
                        return o1.x - o2.x;
                    }
                } else {
                    // 가장 가까운
                    return o1.move - o2.move;
                }
            });
            check = new boolean[n][n];

            check[cur_x][cur_y] = true;
            q.add(new Node(cur_x, cur_y, 0));

            while (!q.isEmpty()) {
                Node cur = q.poll();
                int nMove = cur.move;

                for (int i = 0; i < 4; i++) {
                    int ni = cur.x + di[i];
                    int nj = cur.y + dj[i];
                    if (ni >= 0 && nj >= 0 && ni < n && nj < n && !check[ni][nj]) {
                        check[ni][nj] = true;
                        if (map[ni][nj] <= size) {
                            if (map[ni][nj] != 0 && map[ni][nj] < size) {
                                feeding.add(new Node(ni, nj, nMove + 1));
                            }
                            q.add(new Node(ni, nj, nMove + 1));
                        }
                    }
                }
            }

            if (feeding.size() == 0) {
                // 남은 먹이 없으면 끝
                break;
            } else {
                Node eat = feeding.poll();
                time += eat.move;
                eating++;

                map[cur_x][cur_y] = 0; // 원래 위치를 빈칸으로 만들고
                cur_x = eat.x;
                cur_y = eat.y;
                map[cur_x][cur_y] = 9; // 상어 이동

                if (size == eating) { // 사이즈 만큼 먹었으면 크기 업
                    size++;
                    eating = 0;
                }
            }
        }
    }
}
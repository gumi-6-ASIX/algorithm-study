import java.io.*;
import java.util.*;

public class 다리만들기2 {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static class Dari {
        int weight;
        int start;
        int end;

        Dari(int weight, int start, int end) {
            this.weight = weight;
            this.start = start;
            this.end = end;
        }

    }

    static int[][] num, land;
    static int n, m, cnt = 1, miin = Integer.MAX_VALUE;
    static boolean[][] visited;
    static boolean[] viland, novi;
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, 1, -1, 0 };
    static ArrayList<Node> list = new ArrayList<>();
    static ArrayList<Dari> node = new ArrayList<>();
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<Integer> lanq = new LinkedList<>();
    static LinkedHashSet<int[]> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
                if (num[i][j] == 1) {
                    list.add(new Node(i, j));
                }
            }
        }
        // System.out.println();
        for (int i = 0; i < list.size(); i++) {
            if (!visited[list.get(i).x][list.get(i).y]) {
                visited[list.get(i).x][list.get(i).y] = true;
                queue.offer(new int[] { list.get(i).x, list.get(i).y });
                bfs(cnt);
                cnt++;
            }
        }
        land = new int[cnt][cnt];

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).y + 1 < m && num[list.get(i).x][list.get(i).y + 1] == 0
                    && list.get(i + 1).x == list.get(i).x && list.get(i + 1).y - list.get(i).y > 2) {
                node.add(new Dari(list.get(i + 1).y - list.get(i).y - 1, num[list.get(i).x][list.get(i).y],
                        num[list.get(i + 1).x][list.get(i + 1).y]));

            }
            if (list.get(i).x + 1 < n && num[list.get(i).x + 1][list.get(i).y] == 0) {
                for (int j = 2; j < n; j++) {
                    if (list.get(i).x + j < n && num[list.get(i).x + j][list.get(i).y] != 0) {
                        if (list.get(i).x + j - list.get(i).x <= 2) {
                            break;
                        }
                        node.add(new Dari(list.get(i).x + j - list.get(i).x - 1, num[list.get(i).x][list.get(i).y],
                                num[list.get(i).x + j][list.get(i).y]));
                        break;
                    }
                }
            }
        }
        // for (int i = 0; i < node.size(); i++) {
        // System.out.printf("%d %d %d \n", node.get(i).start, node.get(i).end,
        // node.get(i).weight);
        // }

        novi = new boolean[node.size()];
        dfs(0, 0);
        if (miin == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(miin);
        }

        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < m; j++) {
        // System.out.printf("%d ", num[i][j]);
        // }
        // System.out.println();
        // }

    }

    public static void bfs(int cnt) {
        while (!queue.isEmpty()) {
            int[] where = queue.poll();
            num[where[0]][where[1]] = cnt;
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + where[0];
                int y = dy[i] + where[1];
                if (check(x, y) && num[x][y] == 1 && !visited[x][y]) {
                    queue.offer(new int[] { x, y });
                    visited[x][y] = true;
                }
            }
        }
    }

    public static void dfs(int sum, int dep) {
        if (sum >= miin) {
            return;
        }
        if (dep == cnt - 2) {
            viland = new boolean[cnt];
            if (bbfs()) {
                miin = Math.min(miin, sum);
            }
            return;
        }
        for (int i = 0; i < node.size(); i++) {
            if (!novi[i]) {
                int weight = node.get(i).weight;
                int start = node.get(i).start;
                int end = node.get(i).end;
                land[start][end] += 1;
                land[end][start] += 1;
                novi[i] = true;
                dfs(weight + sum, dep + 1);
                novi[i] = false;
                land[start][end] -= 1;
                land[end][start] -= 1;
            }
        }
    }

    public static boolean bbfs() {
        for (int i = 1; i < cnt; i++) {
            if (land[1][i] == 1) {
                viland[1] = true;
                lanq.offer(i);
            }
        }
        while (!lanq.isEmpty()) {
            int a = lanq.poll();
            viland[a] = true;
            for (int i = 1; i < cnt; i++) {
                if (land[a][i] == 1 && !viland[i]) {
                    lanq.offer(i);
                }
            }
        }
        for (int i = 1; i < viland.length; i++) {
            if (!viland[i])
                return false;
        }
        return true;
    }

    public static boolean check(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

}

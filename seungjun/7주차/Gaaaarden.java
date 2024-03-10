package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gaaaarden {
    static int[][] num;
    static int[][] num2;
    static int[][] ground;
    static boolean[][] visited;
    static boolean[][] visited2;
    static int n, m, gr, red, ss;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int maax = Integer.MIN_VALUE;
    static Queue<int[]> queue = new LinkedList<>();
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gr = Integer.parseInt(st.nextToken());
        red = Integer.parseInt(st.nextToken());
        num = new int[n][m];
        num2 = new int[n][m];
        visited = new boolean[n][m];
        visited2 = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ss = 0;
        dfs(gr, red, 0, 0);

        System.out.println(maax);
    }

    public static void dfs(int gr, int red, int a, int b) {
        if (gr == 0 && red == 0) {
            for (int i = 0; i < list.size(); i++) {
                queue.offer(list.get(i));
            }
            for (int i = 0; i < num.length; i++) {
                num2[i] = num[i].clone();
                visited2[i] = visited[i].clone();
            }
            ground = new int[n][m];
            bfs();
        } else if (gr != 0) {
            for (int i = a; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a == i && j < b) {
                        continue;
                    }
                    if (num[i][j] == 2 && !visited[i][j]) {
                        num[i][j] = 3;
                        visited[i][j] = true;
                        list.add(new int[] { i, j, 1 });
                        ss = 1;
                        dfs(gr - 1, red, i, j);
                        num[i][j] = 2;
                        visited[i][j] = false;
                        list.remove(list.size() - 1);
                    }
                }
            }
        } else {
            if (ss == 1) {
                ss = 0;
                a = 0;
                b = 0;
            }
            for (int i = a; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (num[i][j] == 2 && !visited[i][j]) {
                        num[i][j] = 4;
                        visited[i][j] = true;
                        list.add(new int[] { i, j, 1 });
                        dfs(gr, red - 1, i, j);
                        num[i][j] = 2;
                        visited[i][j] = false;
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
    }

    public static void bfs() {
        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] where = queue.poll();
            if (ground[where[0]][where[1]] == 10000) {
                continue;
            }
            int flag = num2[where[0]][where[1]];
            ground[where[0]][where[1]] = where[2];
            visited2[where[0]][where[1]] = true;
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + where[0];
                int y = dy[i] + where[1];
                if (cheked(x, y) && !visited2[x][y]) {
                    num2[x][y] = flag;
                    ground[x][y] = where[2] + 1;
                    visited2[x][y] = true;
                    queue.offer(new int[] { x, y, where[2] + 1 });
                } else if (cheked(x, y) && visited2[x][y] && num2[x][y] != flag && ground[x][y] == where[2] + 1
                        && ground[x][y] != 10000) {
                    ground[x][y] = 10000;
                    cnt++;
                }
            }
        }
        maax = Math.max(maax, cnt);

    }

    public static boolean cheked(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 0) {
            return true;
        }
        return false;
    }
}

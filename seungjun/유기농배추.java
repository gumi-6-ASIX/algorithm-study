import java.util.*;

public class 유기농배추 {

    public static int[][] num;
    public static boolean[][] visited;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int a, b, cnt, flag = 0, def;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            int k = sc.nextInt();
            num = new int[a][b];
            visited = new boolean[a][b];
            for (int j = 0; j < k; j++) {
                int q = sc.nextInt(), w = sc.nextInt();
                num[q][w] = 1;
            }
            cnt = 0;
            for (int p = 0; p < a; p++) {
                for (int j = 0; j < b; j++) {
                    if (num[p][j] == 1 && !visited[p][j]) {
                        dfs(p, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }


    public static void dfs(int z, int x) {

        if (num[z][x] == 1 && !visited[z][x]) {
            visited[z][x] = true;
            for (int k = 0; k < 4; k++) {
                if ((z + dx[k] >= 0 && z + dx[k] < a) && (x + dy[k] >= 0 && x + dy[k] < b)) {
                    if (num[z + dx[k]][x + dy[k]] == 1 && !visited[z + dx[k]][x + dy[k]]) {
                        dfs(z + dx[k], x + dy[k]);
                    }
                }
            }
        }
    }
}
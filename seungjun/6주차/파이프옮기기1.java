import java.util.*;

public class 파이프옮기기1 {

    static int n;
    static int[][] num;
    static int cnt;
    static int[] state = { 0, 1, 2 };
    static int[][] dx0 = { { 0 }, { 0, 1, 1 } };
    static int[][] dy0 = { { 1 }, { 1, 1, 0 } };

    static int[][] dx1 = { { 1 }, { 0, 1, 1 } };
    static int[][] dy1 = { { 0 }, { 1, 1, 0 } };

    static int[][] dx2 = { { 0 }, { 0, 1, 1 }, { 1 } };
    static int[][] dy2 = { { 1 }, { 1, 1, 0 }, { 0 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        dfs(0, 1, state[0]);
        System.out.println(cnt);
    }

    public static void dfs(int a, int b, int state) {
        if (a == n - 1 && b == n - 1) {
            cnt++;
            return;
        }
        if (state == 0) {
            for (int i = 0; i < dx0.length; i++) {
                int flag = 0;
                for (int j = 0; j < dx0[i].length; j++) {
                    int x = dx0[i][j] + a;
                    int y = dy0[i][j] + b;
                    if (x < 0 || x >= n || y < 0 || y >= n || num[x][y] == 1) {
                        flag = 1;
                    }
                }
                if (flag == 0 && dx0[i].length == 1) {
                    dfs(a, b + 1, 0);
                } else if (flag == 0 && dx0[i].length == 3) {
                    dfs(a + 1, b + 1, 2);
                }
            }
        } else if (state == 1) {
            for (int i = 0; i < dx1.length; i++) {
                int flag = 0;
                for (int j = 0; j < dx1[i].length; j++) {
                    int x = dx1[i][j] + a;
                    int y = dy1[i][j] + b;
                    if (x < 0 || x >= n || y < 0 || y >= n || num[x][y] == 1) {
                        flag = 1;
                    }
                }
                if (flag == 0 && dx1[i].length == 1) {
                    dfs(a + 1, b, 1);
                } else if (flag == 0 && dx1[i].length == 3) {
                    dfs(a + 1, b + 1, 2);
                }
            }
        } else {
            for (int i = 0; i < dx2.length; i++) {
                int flag = 0;
                int j;
                for (j = 0; j < dx2[i].length; j++) {
                    int x = dx2[i][j] + a;
                    int y = dy2[i][j] + b;
                    if (x < 0 || x >= n || y < 0 || y >= n || num[x][y] == 1) {
                        flag = 1;
                    }
                }
                if (flag == 0 && dx2[i].length == 1 && i == 0) {
                    dfs(a, b + 1, 0);
                } else if (flag == 0 && dx2[i].length == 3) {
                    dfs(a + 1, b + 1, 2);
                } else if (flag == 0 && dx2[i].length == 1 && i == 2) {
                    dfs(a + 1, b, 1);
                }
            }
        }
    }
}

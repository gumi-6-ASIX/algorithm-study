import java.util.Scanner;

public class 캐슬디펜스 {

    static int[][] num;
    static int[][] copy;
    static boolean[][] visited;
    static boolean[] pick;

    static int n;
    static int m;
    static int area;
    static int miin;
    static int result = Integer.MIN_VALUE;;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        area = sc.nextInt();
        num = new int[n + 1][m];
        copy = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                num[i][j] = sc.nextInt();
                copy[i][j] = num[i][j];
            }
        }
        pick = new boolean[m];
        select(0);

        System.out.println(result);

    }

    public static boolean move() {
        int flag = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (i == n - 1 && num[i][j] == 1) {
                    num[i][j] = 0;
                    flag = 0;
                } else if (num[i][j] == 1) {
                    num[i][j] = 0;
                    num[i + 1][j] = 1;
                    flag = 0;
                }
            }
        }
        if (flag == 1) {
            return false;
        } else {
            return true;
        }
    }

    public static void attack(int[] where) {
        miin = Integer.MAX_VALUE;
        int flag = 1;
        int a = n - 1, b = m - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (num[i][j] == 1) {
                    int r = (int) Math.abs(where[0] - i) + (int) Math.abs(where[1] - j);
                    if (r <= area && r < miin) {
                        miin = r;
                        a = i;
                        b = j;
                        flag = 0;
                    } else if (r <= area && r == miin) {
                        if (j < b) {
                            a = i;
                            b = j;
                            flag = 0;
                        }
                    }
                }
            }
        }
        if (flag == 0) {
            visited[a][b] = true;
        }

    }

    public static void select(int dep) {
        if (dep == 3) {
            int cnt = 0;
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    num[i][j] = copy[i][j];
                }
            }
            while (flag) {
                for (int i = 0; i < m; i++) {
                    if (num[n][i] == 2) {
                        attack(new int[] { n, i });
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (visited[i][j]) {
                            if (num[i][j] == 1) {
                                cnt++;
                            }
                            num[i][j] = 0;
                        }
                    }
                }
                visited = new boolean[n][m];
                if (move()) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            result = Math.max(result, cnt);
            return;

        }
        for (int i = 0; i < m; i++) {
            if (!pick[i]) {
                pick[i] = true;
                num[n][i] = 2;
                select(dep + 1);
                num[n][i] = 0;
                pick[i] = false;
            }
        }
    }

}

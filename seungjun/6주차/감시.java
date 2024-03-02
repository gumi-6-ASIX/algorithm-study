import java.util.*;

public class 감시 {
    static int n;
    static int m;
    static int p;
    static int[][] num;
    static boolean[][] visited;

    static int miin = Integer.MAX_VALUE;

    static int cnt;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        num = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        dfs(0, 0);

        System.out.println(miin);
    }

    public static void checkedtrue(int a, int b, int[] where) {
        visited[a][b] = true;
        for (int i = 0; i < where.length; i++) {
            if (where[i] == 0) {
                int x = a + dx[0];
                int y = b + dy[0];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] > 0) {
                        x += dx[0];
                        y += dy[0];
                        continue;
                    }
                    num[x][y]--;
                    x += dx[0];
                    y += dy[0];
                }
            } else if (where[i] == 1) {
                int x = a + dx[1];
                int y = b + dy[1];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] > 0) {
                        x += dx[1];
                        y += dy[1];
                        continue;
                    }
                    num[x][y]--;
                    x += dx[1];
                    y += dy[1];
                }
            } else if (where[i] == 2) {
                int x = a + dx[2];
                int y = b + dy[2];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] > 0) {
                        x += dx[2];
                        y += dy[2];
                        continue;
                    }
                    num[x][y]--;
                    x += dx[2];
                    y += dy[2];
                }
            } else {
                int x = a + dx[3];
                int y = b + dy[3];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] > 0) {
                        x += dx[3];
                        y += dy[3];
                        continue;
                    }
                    num[x][y]--;
                    x += dx[3];
                    y += dy[3];
                }
            }
        }

    }

    public static void checkedfalse(int a, int b, int[] where) {
        visited[a][b] = false;
        for (int i = 0; i < where.length; i++) {
            if (where[i] == 0) {
                int x = a + dx[0];
                int y = b + dy[0];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] >= 0) {
                        x += dx[0];
                        y += dy[0];
                        continue;
                    }
                    num[x][y]++;
                    x += dx[0];
                    y += dy[0];
                }
            } else if (where[i] == 1) {
                int x = a + dx[1];
                int y = b + dy[1];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] >= 0) {
                        x += dx[1];
                        y += dy[1];
                        continue;
                    }
                    num[x][y]++;
                    x += dx[1];
                    y += dy[1];
                }
            } else if (where[i] == 2) {
                int x = a + dx[2];
                int y = b + dy[2];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] >= 0) {
                        x += dx[2];
                        y += dy[2];
                        continue;
                    }
                    num[x][y]++;
                    x += dx[2];
                    y += dy[2];
                }
            } else {
                int x = a + dx[3];
                int y = b + dy[3];
                while (x >= 0 && x < n && y >= 0 && y < m && num[x][y] != 6) {
                    if (num[x][y] >= 0) {
                        x += dx[3];
                        y += dy[3];
                        continue;
                    }
                    num[x][y]++;
                    x += dx[3];
                    y += dy[3];
                }
            }
        }

    }

    public static void dfs(int a, int b) {
        for (int i = a; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == a && j < b) {
                    continue;
                }
                if (num[i][j] != 6 && num[i][j] > 0 && !visited[i][j]) {
                    if (num[i][j] == 1) {
                        for (int k = 0; k < 4; k++) {
                            checkedtrue(i, j, new int[] { k });
                            dfs(i, j);
                            checkedfalse(i, j, new int[] { k });
                        }
                    } else if (num[i][j] == 2) {
                        for (int k = 0; k < 2; k++) {
                            checkedtrue(i, j, new int[] { k, k + 2 });
                            dfs(i, j);
                            checkedfalse(i, j, new int[] { k, k + 2 });
                        }
                    } else if (num[i][j] == 3) {
                        for (int k = 0; k < 4; k++) {
                            checkedtrue(i, j, new int[] { k % 4, (k + 1) % 4 });
                            dfs(i, j);
                            checkedfalse(i, j, new int[] { k % 4, (k + 1) % 4 });
                        }
                    } else if (num[i][j] == 4) {
                        for (int k = 0; k < 4; k++) {
                            checkedtrue(i, j, new int[] { k % 4, (k + 1) % 4, (k + 2) % 4 });
                            dfs(i, j);
                            checkedfalse(i, j, new int[] { k % 4, (k + 1) % 4, (k + 2) % 4 });
                        }
                    } else {
                        checkedtrue(i, j, new int[] { 0, 1, 2, 3 });
                        dfs(i, j);
                        checkedfalse(i, j, new int[] { 0, 1, 2, 3 });
                    }
                }
            }
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (num[i][j] == 0) {
                    cnt++;
                }
            }
        }
        miin = Math.min(cnt, miin);

    }
}
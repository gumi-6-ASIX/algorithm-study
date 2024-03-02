import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 인구이동 {

    static Queue<int[]> queue = new LinkedList<>();
    static int n;
    static int l;
    static int r;
    static int[][] num;
    static int cnt;
    static int cnt2;
    static int addcnt;
    static boolean[][] visited;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Stack<int[]> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        cnt = 0;
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        int flag = 0;
        while (flag == 0) {
            flag = 1;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < n && y >= 0 && y < n && !visited[i][j]) {
                            if (Math.abs(num[x][y] - num[i][j]) >= l && Math.abs(num[x][y] - num[i][j]) <= r) {

                                addcnt = num[i][j];
                                queue.offer(new int[] { i, j });
                                stack.push(new int[] { i, j });
                                cnt2 = 1;
                                bfs(i, j);

                                flag = 0;

                            }
                        }
                    }

                }
            }

            cnt++;
        }

        System.out.println(cnt - 1);

    }

    public static void bfs(int a, int b) {
        visited[a][b] = true;
        while (!queue.isEmpty()) {
            int[] where = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = where[0] + dx[k];
                int y = where[1] + dy[k];
                if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                    if (Math.abs(num[x][y] - num[where[0]][where[1]]) >= l
                            && Math.abs(num[x][y] - num[where[0]][where[1]]) <= r) {
                        visited[x][y] = true;
                        stack.push(new int[] { x, y });
                        queue.offer(new int[] { x, y });
                        addcnt += num[x][y];
                        cnt2++;
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            int[] where = stack.pop();
            num[where[0]][where[1]] = addcnt / cnt2;
        }

    }

}

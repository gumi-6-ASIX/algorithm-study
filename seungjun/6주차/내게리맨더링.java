import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] num;
    static int[] hi;
    static boolean[] hivisited;
    static boolean[] hivisited2;
    static boolean[][] visited;
    static int n;
    static Queue<Integer> queue = new LinkedList<>();
    static List<Integer> list;
    static List<Integer> list2;
    static int cnt;
    static int miin = Integer.MAX_VALUE;
    static int sr;
    static int check = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n + 1][n + 1];
        hi = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            hi[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                int b = Integer.parseInt(st.nextToken());
                num[i][b] = 1;
                num[b][i] = 1;
            }
        }
        sr = 0;
        for (int i = 1; i <= n / 2; i++) {
            cnt = i;
            hivisited = new boolean[n + 1];
            hivisited2 = new boolean[n + 1];
            list = new ArrayList<>();
            list2 = new ArrayList<>();
            select(0);
        }
        if (sr == 1) {
            System.out.println(miin);
        } else {
            System.out.println("-1");
        }
    }

    public static void bfs() {
        for (int i = 1; i <= n; i++) {
            if (!hivisited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int a = queue.poll();
                    hivisited2[a] = true;
                    for (int j = 1; j <= n; j++) {
                        if (num[a][j] == 1 && !hivisited[j] && !visited[a][j]) {
                            queue.offer(j);
                            visited[a][j] = true;
                            visited[j][a] = true;
                        }
                    }
                }
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (hivisited[i] != hivisited2[i]) {
                continue;
            } else {
                return;
            }
        }
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i <= n; i++) {
            if (hivisited[i]) {
                sum1 += hi[i];
            } else {
                sum2 += hi[i];
            }
        }
        sr = 1;

        miin = Math.min((int) Math.abs(sum1 - sum2), miin);
    }

    public static void select(int dep) {
        if (cnt == dep) {
            boolean go = true;
            if (cnt != 1) {
                go = check();
            }
            if (go) {
                visited = new boolean[n + 1][n + 1];
                hivisited2 = new boolean[n + 1];
                bfs();
            }
            return;

        } else {
            for (int i = 1; i <= n; i++) {
                if (!hivisited[i]) {
                    hivisited[i] = true;
                    list.add(i);
                    select(dep + 1);
                    list.remove(list.size() - 1);
                    hivisited[i] = false;
                }
            }

        }

    }

    public static boolean check() {
        visited = new boolean[n + 1][n + 1];
        hivisited2 = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (list.contains(i)) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int a = queue.poll();
                    hivisited2[a] = true;
                    for (int j = 1; j <= n; j++) {
                        if (num[a][j] == 1 && hivisited[j] && !visited[a][j]) {
                            queue.offer(j);
                            visited[a][j] = true;
                            visited[j][a] = true;
                        }
                    }
                }
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (hivisited[i] != hivisited2[i]) {
                return false;
            }
        }
        return true;

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 게리맨더링 {
    static int n;
    static boolean[] visited;
    static int[] ingu;
    static int[] area;
    static ArrayList<Integer>[] list;
    static int miin = Integer.MAX_VALUE;
    static int sr = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ingu = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            ingu[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }

        }

        area = new int[n + 1];
        dfs(1);
        if (sr == 1) {
            System.out.println(miin);
        } else {
            System.out.println("-1");
        }

    }

    public static void dfs(int idx) {
        if (idx == n + 1) {
            visited = new boolean[n + 1];
            int link = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    bfs(i, area[i]);
                    link++;
                }
            }

            if (link == 2) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 1; i <= n; i++) {
                    if (area[i] == 1) {
                        sum1 += ingu[i];
                    } else {
                        sum2 += ingu[i];
                    }
                }
                sr = 1;
                miin = Math.min(miin, Math.abs(sum1 - sum2));
            } else {
                return;
            }

        } else {
            area[idx] = 1;
            dfs(idx + 1);
            area[idx] = 2;
            dfs(idx + 1);
        }
    }

    public static void bfs(int idx, int where) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        visited[idx] = true;
        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (list[a].contains(i) && !visited[i] && area[i] == where) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}

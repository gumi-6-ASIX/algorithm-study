import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] count;
    static boolean[] visited;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        count = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<Integer>());
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
        }
        for (int i = 1; i <= n; i++) {
            queue = new LinkedList<>();
            visited = new boolean[n + 1];
            queue.offer(i);
            bfs(i);
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(n - 1 - count[i]);
        }

    }

    public static void bfs(int check) {
        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int i = 0; i < list.get(a).size(); i++) {
                if (!visited[list.get(a).get(i)]) {
                    queue.offer(list.get(a).get(i));
                    visited[list.get(a).get(i)] = true;
                    count[check]++;
                    count[list.get(a).get(i)]++;
                }
            }

        }
    }
}

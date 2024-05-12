import java.util.*;
import java.io.*;

class 음악프로그램 {
    static int n, m;
    static int[] indegree;
    static List<Integer>[] adj;
    static PriorityQueue<Integer> pq;
    static StringBuilder sb;
    static int cnt = 0;

    static void topology() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)
                pq.add(i);
        }
        while (!pq.isEmpty()) {
            int node = pq.poll();
            sb.append(node).append("\n");
            cnt++;
            for (int nxt : adj[node]) {
                indegree[nxt]--;
                if (indegree[nxt] == 0)
                    pq.add(nxt);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        indegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList();
        }
        pq = new PriorityQueue<Integer>((o1, o2) -> {
            return o1 - o2;
        });
        sb = new StringBuilder();
        a: for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int size = Integer.parseInt(line[0]);
            for (int j = 1; j < size; j++) {
                int from = Integer.parseInt(line[j]);
                int to = Integer.parseInt(line[j + 1]);
                adj[from].add(to);
                indegree[to]++;
            }
        }
        topology();
        if (cnt == n) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }
}
import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] indegree;
    static List<Integer>[] adj;
    static PriorityQueue<Integer> pq;
    static StringBuilder sb;

    static void topology() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)
                pq.add(i);
        }
        while (!pq.isEmpty()) {
            int node = pq.poll();
            sb.append(node).append(" ");
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
        n = Integer.parseInt(st.nextToken());// 문제의수 1~N까지
        m = Integer.parseInt(st.nextToken()); // 먼저 푸는 게 좋은 문제에 대한 개수
        adj = new ArrayList[n + 1]; // 부터 N까지의 인접리스트
        indegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList(); // N개의 정점 초기화
        }
        pq = new PriorityQueue<Integer>((o1, o2) -> {
            return o1 - o2;
        });
        sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            // to정점의 진입차수를 늘린다.
            adj[from].add(to);
            indegree[to]++;
        }
        // 진입차수가 0인애들만 pq에 넣고 시작하기
        topology();
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766_문제집 {
    static int[] edge;
    static ArrayList<ArrayList<Integer>> graph;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        edge = new int[n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            edge[b]++; // 해당 정점 진입 차수 늘려주기
        }

        // 진입 차수 0인 것 다 넣어 주기
        pq = new PriorityQueue<>();
        for (int i = 1; i < n + 1; i++) {
            if (edge[i] == 0) {
                pq.offer(i);
            }
        }

        // 위상정렬
        sort();
    }

    static void sort() {
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int now = pq.poll();

            sb.append(now + " "); // 현재 번호 sb에 넣어 주기

            // 인접한 정점들 탐색
            for (int node : graph.get(now)) {
                // 인접차수 - 1 => 진입차수 0 되면 pq에 넣어 주기
                if (--edge[node] == 0) {
                    pq.offer(node);
                }
            }
        }

        // pq 다 돌면 끝
        System.out.println(sb.toString());
    }
}

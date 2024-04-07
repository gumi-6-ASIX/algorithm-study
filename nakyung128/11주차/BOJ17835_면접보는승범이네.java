import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17835_면접보는승범이네 {
    static class Node implements Comparable<Node> {
        int idx;
        long dist;

        Node(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }

    }

    static int n, m, k;
    static ArrayList<ArrayList<Node>> graph;
    static long[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수
        k = Integer.parseInt(st.nextToken()); // 면접장 개수

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 역방향 그래프 생성
        // 면접장 -> 각 도시까지의 거리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(to).add(new Node(from, dist));
        }

        pq = new PriorityQueue<>();
        visited = new boolean[n + 1];
        dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        // 면접장들을 pq에 모두 넣는다
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int room = Integer.parseInt(st.nextToken());
            dist[room] = 0;
            pq.offer(new Node(room, 0));
        }

        // 다익스트라
        find();

        int max_node = 0;
        long max_dist = 0;

        // 거리가 가장 먼 도시와 길이 갱신해 주기
        for (int i = 1; i < n + 1; i++) {
            if (max_dist < dist[i]) {
                max_dist = dist[i];
                max_node = i;
            }
        }

        System.out.println(max_node);
        System.out.println(max_dist);
    }

    // 면접장까지 거리 찾기
    static void find() {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                for (Node n : graph.get(now.idx)) {
                    if (!visited[n.idx]) {
                        if (dist[n.idx] > dist[now.idx] + n.dist) {
                            dist[n.idx] = dist[now.idx] + n.dist;
                            pq.offer(new Node(n.idx, dist[n.idx]));
                        }
                    }
                }
            }
        }
    }
}

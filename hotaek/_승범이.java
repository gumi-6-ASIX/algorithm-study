import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _승범이 {

    static List<List<Node>> graph;
    static long INF = Long.MAX_VALUE;
    static List<Integer> city;

    static class Node implements Comparable<Node> {
        int dist;
        long cost;

        public Node(int dist, long cost) {
            this.dist = dist;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.cost - o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stg = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stg.nextToken());
            int to = Integer.parseInt(stg.nextToken());
            int cost = Integer.parseInt(stg.nextToken());
            graph.get(to).add(new Node(from, cost));
        }

        city = new ArrayList<>();

        PriorityQueue<Node> queue = new PriorityQueue<>();
        long[] dist = new long[n + 1];

        Arrays.fill(dist, INF);

        StringTokenizer sta = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int a = Integer.parseInt(sta.nextToken());
            queue.offer(new Node(a, 0));
            dist[a] = 0;
        }

        dijkstra(n, queue, dist);

        int resultCity = 0;
        long resultDist = 0;
        for (int i = 1; i <= n; i++) {
            if (resultDist < dist[i]) {
                resultDist = dist[i];
                resultCity = i;
            }
        }

        System.out.println(resultCity + "\n" + resultDist);
    }

    public static void dijkstra(int n, PriorityQueue<Node> pq, long[] dist) {

        boolean[] check = new boolean[n + 1];

        while (!pq.isEmpty()) {
            Node q = pq.poll();
            int nowVertex = q.dist;
            long nowWeight = q.cost;

            if (check[nowVertex])
                continue;
            check[nowVertex] = true;

            if (nowWeight > dist[nowVertex]) {
                continue;
            }

            for (Node next : graph.get(nowVertex)) {
                if (dist[next.dist] > dist[nowVertex] + next.cost) {
                    dist[next.dist] = dist[nowVertex] + next.cost;
                }
                pq.offer(new Node(next.dist, dist[next.dist]));
            }
        }

    }
}

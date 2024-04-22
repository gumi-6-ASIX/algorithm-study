package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1238_파티 {

    public static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {

            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        List<List<Node>> rgraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }

        // 그래프 넣기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
            rgraph.get(to).add(new Node(from, weight));
        }

        int[] result = dijkstra(new Node(x, 0), n, graph);
        int[] rresult = dijkstra(new Node(x, 0), n, rgraph);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int value = result[i] + rresult[i];
            if (answer < value) {
                answer = value;
            }
        }
        System.out.println(answer);
    }

    public static int[] dijkstra(Node start, int n, List<List<Node>> value) {
        int[] dist = new int[n + 1];
        boolean[] check = new boolean[n + 1];
        Arrays.fill(dist, 100001);
        dist[start.to] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);

        while (!pq.isEmpty()) {
            Node q = pq.poll();

            if (check[q.to])
                continue;
            check[q.to] = true;
            for (Node node : value.get(q.to)) {
                if (dist[node.to] > dist[q.to] + node.weight) {
                    dist[node.to] = dist[q.to] + node.weight;

                    pq.offer(new Node(node.to, dist[node.to]));
                }
            }
        }

        return dist;
    }
}

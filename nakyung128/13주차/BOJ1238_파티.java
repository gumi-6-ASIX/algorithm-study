import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {
    static class Node implements Comparable<Node> {
        int idx;
        int dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int n, m, x;
    static ArrayList<ArrayList<Node>> graph;
    static PriorityQueue<Node> pq;
    static boolean[] visited;
    static int[] back_dist, go_dist;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 학생 수
        m = Integer.parseInt(st.nextToken()); // 간선 수
        x = Integer.parseInt(st.nextToken()); // 파티 장소

        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        pq = new PriorityQueue<>();
        back_dist = new int[n + 1];
        Arrays.fill(back_dist, Integer.MAX_VALUE);
        back_dist[x] = 0;
        pq.offer(new Node(x, 0));

        find(x, back_dist);

        for (int i = 1; i < n + 1; i++) {
            pq = new PriorityQueue<>();
            visited = new boolean[n + 1];
            go_dist = new int[n + 1];
            Arrays.fill(go_dist, Integer.MAX_VALUE);
            go_dist[i] = 0;
            pq.offer(new Node(i, 0));

            find(i, go_dist);
        }

        System.out.println(answer);
    }

    static void find(int start, int[] dist) {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (start != x && now.idx == x) {
                answer = Math.max(answer, back_dist[start] + dist[x]);
                break;
            }

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                for (Node n : graph.get(now.idx)) {
                    if (dist[n.idx] > dist[now.idx] + n.dist) {
                        dist[n.idx] = dist[now.idx] + n.dist;
                        pq.offer(new Node(n.idx, dist[n.idx]));
                    }
                }
            }
        }
    }
}

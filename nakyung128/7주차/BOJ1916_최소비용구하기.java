import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_최소비용구하기 {
    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }

        @Override
        public String toString() {
            return "[ " + this.idx + ", " + this.cost + " ]";
        }
    }

    static boolean[] visited;
    static int[] costs;
    static ArrayList<Node>[] nodes;
    static PriorityQueue<Node> pq;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 도시의 개수

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 버스의 개수

        // nodes 초기화
        nodes = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Node node = new Node(e, c);
            nodes[s].add(node);
        }

        // 도시 방문 여부 판별할 visited
        visited = new boolean[n + 1];

        // 최소 비용 저장할 배열 필요
        costs = new int[n + 1];

        // 초기화
        for (int i = 0; i < costs.length; i++) {
            costs[i] = Integer.MAX_VALUE;
        }

        // 출발 도시와 도착 도시
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 출발지는 0
        costs[start] = 0;

        pq = new PriorityQueue<>();
        find(start);

        System.out.println(costs[end]);
    }

    static void find(int start) {

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                // 인접한 노드 탐색
                for (Node n : nodes[now.idx]) {
                    // 현재 해당 노드의 비용과 나를 거쳐서 갈 때의 비용 비교 후 갱신해 주기
                    if (costs[n.idx] > costs[now.idx] + n.cost) {
                        costs[n.idx] = costs[now.idx] + n.cost;
                        pq.offer(new Node(n.idx, costs[n.idx]));
                    }
                }
            }
        }
    }
}

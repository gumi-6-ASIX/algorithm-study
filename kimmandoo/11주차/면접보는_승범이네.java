import java.util.*;
import java.io.*;

class 면접보는_승범이네 {
    static int v, e, k;

    static class Node {
        int e;
        long w;

        Node(int e, long w) {
            this.e = e;
            this.w = w;
        }
    }

    static List<Node>[] adj;
    static long[] dist;
    static long INF = Long.MAX_VALUE;
    static PriorityQueue<Node> pq;

    static void dijk() {
        boolean[] check = new boolean[v + 1];
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.e;
            if (check[curNode])
                continue;
            check[curNode] = true;
            for (Node node : adj[curNode]) {
                long tmpW = dist[curNode] + node.w;
                if (dist[node.e] > tmpW) { // 커야지 갱신임
                    dist[node.e] = tmpW;
                    pq.add(new Node(node.e, dist[node.e]));
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        v = Integer.parseInt(st.nextToken());
        adj = new ArrayList[v + 1];// 도시는 1번부터 N번까지
        dist = new long[v + 1];
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<Node>((o1, o2) -> {
            return (int) (o1.w - o2.w);
        });
        for (int i = 0; i <= v; i++) {
            adj[i] = new ArrayList();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[end].add(new Node(start, w));
        }

        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        Arrays.fill(dist, INF);
        for (int i = 0; i < k; i++) {
            // '면접장까지의 거리'란 그 도시에서 도달 가능한 가장 가까운 면접장까지의 최단 거리
            int m = Integer.parseInt(st.nextToken());
            pq.add(new Node(m, 0)); // 시작 면접장 놓고 역방향 돌리기
            dist[m] = 0;
        }

        dijk();

        long max = -1;
        int idx = -1;

        for (int i = 1; i <= v; i++) {
            if (max < dist[i]) {
                max = dist[i];
                idx = i;
            }
        }

        sb.append(idx).append("\n").append(max).append("\n");

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class 파티 {
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
    static List<Node>[] adj2;

    static long[] dist;
    static long[] dist2;

    static long INF = Long.MAX_VALUE;
    static PriorityQueue<Node> pq;

    static void dijk(int k) {
        pq.add(new Node(k, 0));
        dist[k] = 0;
        boolean[] check = new boolean[v + 1];
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.e;
            if (check[curNode])
                continue;
            check[curNode] = true;
            for (Node node : adj[curNode]) {
                long tmpW = dist[curNode] + node.w;
                if (dist[node.e] > tmpW) {
                    dist[node.e] = tmpW;
                    pq.add(new Node(node.e, dist[node.e]));
                }
            }
        }
    }

    static void dijkReverse(int k) {
        pq.add(new Node(k, 0));
        dist2[k] = 0;
        boolean[] check = new boolean[v + 1];
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.e;
            if (check[curNode])
                continue;
            check[curNode] = true;
            for (Node node : adj2[curNode]) {
                long tmpW = dist2[curNode] + node.w;
                if (dist2[node.e] > tmpW) {
                    dist2[node.e] = tmpW;
                    pq.add(new Node(node.e, dist2[node.e]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        v = Integer.parseInt(st.nextToken()); // n, m, x
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adj = new ArrayList[v + 1];// 도시는 1번부터 N번까지
        adj2 = new ArrayList[v + 1];// 도시는 1번부터 N번까지
        dist = new long[v + 1];
        dist2 = new long[v + 1];

        pq = new PriorityQueue<Node>((o1, o2) -> {
            return (int) (o1.w - o2.w);
        });

        for (int i = 0; i <= v; i++) {
            adj[i] = new ArrayList();
            adj2[i] = new ArrayList();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[end].add(new Node(start, w)); // 돌아가는길이 제일 큰놈
            adj2[start].add(new Node(end, w)); // 가는길이 제일 큰놈
        }
        StringBuilder sb = new StringBuilder();
        Arrays.fill(dist, INF);
        Arrays.fill(dist2, INF);
        dijk(k); // 돌아가기
        dijkReverse(k);

        long max = -1;

        for (int i = 1; i <= v; i++) {
            if (max < dist[i] + dist2[i]) {
                max = dist[i] + dist2[i];
            }
        }

        // System.out.println(Arrays.toString(dist));
        // System.out.println(Arrays.toString(dist2));
        System.out.println(max);
    }
}

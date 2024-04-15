import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int y;

        Node(int y) {
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.y - o.y;
        }

    }

    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();
    static int n, m;
    static int[] dist;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<Node>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b));
            dist[b] += 1;
        }
        for (int i = 1; i <= n; i++) {
            if (dist[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now + " ");
            for (int i = 0; i < list.get(now).size(); i++) {
                dist[list.get(now).get(i).y] -= 1;
                if (dist[list.get(now).get(i).y] == 0) {
                    queue.offer(list.get(now).get(i).y);
                }
            }
        }

        System.out.printf("%s", sb);
    }

}

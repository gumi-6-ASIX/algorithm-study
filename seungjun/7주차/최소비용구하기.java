package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기 {
    static int[] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> list;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node obj) {
            return this.cost - obj.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        visited = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        find(start, end);

        System.out.println(dp[end]);
    }

    public static void find(int start, int end) {
        queue.offer(new Node(start, 0));
        dp[start] = 0;
        while (!queue.isEmpty()) {
            Node where = queue.poll();
            if (where.end == end) {
                break;
            }
            if (!visited[where.end]) {
                visited[where.end] = true;
                for (Node node : list.get(where.end)) {
                    if (!visited[node.end] && dp[node.end] > dp[where.end] + node.cost) {
                        dp[node.end] = dp[where.end] + node.cost;
                        queue.add(new Node(node.end, dp[node.end]));
                    }
                }
            }

        }
    }
}

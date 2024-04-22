package Baekjoon.Algo_13주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티 {

    static class Node implements Comparable<Node> {
        int y;
        int weight;

        Node(int y, int weight) {
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

    }

    static int[] dist, sum;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        sum = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<Node>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dist[j] = Integer.MAX_VALUE;
            }
            visited = new boolean[n + 1];
            stra(i);
            if (i == x) {
                for (int j = 1; j < n + 1; j++) {
                    sum[j] += dist[j];
                }
            } else {
                sum[i] += dist[x];
            }
        }
        int result = 0;
        for (int i = 1; i < n + 1; i++) {
            result = Math.max(result, sum[i]);
        }
        System.out.println(result);

    }

    public static void stra(int start) {
        queue.offer(new Node(start, 0));
        dist[start] = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (!visited[now.y]) {
                visited[now.y] = true;
                for (Node next : list.get(now.y)) {
                    if (dist[next.y] > now.weight + next.weight) {
                        dist[next.y] = now.weight + next.weight;
                        queue.offer(new Node(next.y, dist[next.y]));
                    }
                }
            }
        }
    }

}

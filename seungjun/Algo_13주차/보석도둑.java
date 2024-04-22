package Baekjoon.Algo_13주차;

import java.io.*;
import java.util.*;

public class 보석도둑 {

    static class Node implements Comparable<Node> {
        int m;
        int v;

        Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            if (this.v == o.v) {
                return this.m - o.m;
            }
            return o.v - this.v;
        }
    }

    static class Bosuk implements Comparable<Bosuk> {
        int m;
        int v;

        Bosuk(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Bosuk o) {
            return this.m - o.m;
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static PriorityQueue<Bosuk> pq2 = new PriorityQueue<>();
    static ArrayList<Integer> list = new ArrayList<>();
    static int n, k;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq2.offer(new Bosuk(m, v));
        }
        for (int i = 0; i < k; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        for (int i = 0; i < k; i++) {
            while (!pq2.isEmpty() && pq2.peek().m <= list.get(i)) {
                Bosuk data = pq2.poll();
                pq.offer(new Node(data.m, data.v));
            }
            if (!pq.isEmpty()) {
                result += pq.poll().v;
            }
        }
        System.out.println(result);
    }
}

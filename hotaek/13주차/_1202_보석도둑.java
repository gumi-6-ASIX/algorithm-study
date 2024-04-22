package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1202_보석도둑 {

    static class Jewel implements Comparable<Jewel> {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return o.value - this.value;
        }
    }

    static class Bag implements Comparable<Bag> {
        int weight, value;

        public Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Bag o) {
            if (this.value == o.value) {
                return this.weight - o.weight;
            }
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq.offer(new Jewel(m, v));
        }
        PriorityQueue<Bag> bpq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            int weight = Integer.parseInt(br.readLine());
            bpq.offer(new Bag(weight, 0));
        }
        int zeroCount = 0;
        long result = 0;

        while (!pq.isEmpty() && !bpq.isEmpty()) {
            Jewel jvpq = pq.poll();
            Bag bvpq = bpq.poll();

            if (bvpq.weight >= jvpq.weight) {
                if (bvpq.value == 0) {
                    zeroCount++;
                    result += jvpq.value;
                    bpq.offer(new Bag(bvpq.weight, jvpq.value));
                } else if (bvpq.value < jvpq.value) {
                    result -= bvpq.value;
                    result += jvpq.value;
                    bpq.offer(new Bag(bvpq.weight, jvpq.value));
                }
            } else {
                bpq.offer(new Bag(bvpq.weight, bvpq.value));
            }

            if (zeroCount == k) {
                System.out.println(result);
                break;
            }
        }
    }
}

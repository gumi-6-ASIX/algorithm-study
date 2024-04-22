import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1202_보석도둑 {
    static class Jewel implements Comparable<Jewel> {
        int weight;
        int price;

        Jewel(int w, int p) {
            this.weight = w;
            this.price = p;
        }

        @Override
        public int compareTo(Jewel o) {
            if (this.weight == o.weight) {
                return o.price - this.price;
            }
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Jewel> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            list.add(new Jewel(w, p));
        }

        list.sort(null);

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 가방 오름차순

        // pq
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long answer = 0;

        int idx = 0;
        for (int i = 0; i < k; i++) {
            while (idx < n && list.get(idx).weight <= bags[i]) {
                pq.offer(list.get(idx).price);
                idx++;
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1826_연료채우기 {

    static class Node implements Comparable<Node> {
        int dist; // 성경이의 시작 위치에서 주유소까지의 거리
        int amount; // 채울 수 있는 연료의 양

        public Node(int dist, int amount) {
            this.dist = dist;
            this.amount = amount;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "[" + this.dist + ", " + this.amount + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> stations = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stations.add(new Node(a, b));
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken()); // 성경이의 위치에서 마을까지의 거리
        int p = Integer.parseInt(st.nextToken()); // 트럭에 원래 있던 연료의 양

        // 연료 내림차순 pq
        PriorityQueue<Integer> amounts = new PriorityQueue<>(Collections.reverseOrder());

        // 정답
        int answer = 0;

        while (p < l) {
            // 주유소 비기 전까지 && 주유소의 거리가 현재 갈 수 있을 때까지
            while (!stations.isEmpty() && stations.peek().dist <= p) {
                Node station = stations.peek();
                // 갈 수 있는 거리면
                amounts.add(station.amount); // 연료 pq에 추가하기
                stations.poll(); // 해당 주유소 빼주기
            }

            // 아직 p(연료 탱크의 양)가 l(마을까지의 거리)보다 같거나 커지지 않았는데 amounts가 비었다는 것은
            // 마을까지 갈 수 없다는 것
            if (amounts.isEmpty()) {
                answer = -1;
                break;
            }

            p += amounts.poll(); // 내 연료 탱크에 추가하기
            answer++; // 횟수 + 1
        }

        System.out.println(answer);
    }
}

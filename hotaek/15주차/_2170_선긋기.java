package 백준.java.골드;

/*
 * 1. 우선순위 큐에 넣음! 이떄 왼쪽 기준 오름차순! 왼쪽이 같으면 오른쪽 오름차순
 * 2. 하나씩 빼면서 전에 뺐던 오른쪽 값이 지금 빼는 왼쪽 값보다 크면, maxValue 갱신
 * 3. 작으면 result에 지금까지의 minValue, maxValue의 차를 넣어주고 지금 뺀 값들로 minValue, maxValue 갱신
 * 4. 큐 비운뒤 다시 계산
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _2170_선긋기 {
    static class Line implements Comparable<Line> {
        int left, right;

        public Line(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Line o) {
            if (this.left == o.left) {
                return this.right - o.right;
            }
            return this.left - o.left;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Line> pq = new PriorityQueue<>();

        // 우선순위 큐 넣기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            pq.offer(new Line(left, right));
        }
        int maxValue = 0;
        int minValue = 0;
        // 처음 min, max값
        Line firstQ = pq.poll();
        minValue = firstQ.left;
        maxValue = firstQ.right;

        int result = 0;
        while (!pq.isEmpty()) {
            Line q = pq.poll();
            if (q.left <= maxValue && q.right > maxValue) {
                maxValue = q.right;
            } else if (q.left > maxValue) {
                result += Math.abs(maxValue - minValue);
                minValue = q.left;
                maxValue = q.right;
            }
        }

        result += Math.abs(maxValue - minValue);

        System.out.println(result);

    }
}

package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _14719_빗물 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] graph = new int[w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            int value = Integer.parseInt(st.nextToken());
            graph[i] = value;
        }
        int start = 0;
        int result = 0;
        int end = graph[w - 1];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < w; i++) {

            if (i == w - 1) {
                while (!stack.isEmpty()) {
                    int value = stack.pollLast();
                    if (value > end) {
                        end = value;
                    }
                    int min = Math.min(graph[start], end);
                    result += (min - value);
                }
                break;
            }

            if (graph[start] <= graph[i]) {
                while (!stack.isEmpty()) {
                    int value = stack.pollLast();
                    result += (graph[start] - value);

                }
                start = i;
            } else {
                stack.offerLast(graph[i]);
            }

        }
        System.out.println(result);
    }
}

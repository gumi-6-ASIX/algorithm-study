package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2623_음악프로그램 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] table = new int[n + 1];
    boolean[] check = new boolean[n + 1];
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int in = Integer.parseInt(st.nextToken());
      for (int j = 0; j < (a - 1); j++) {
        int out = Integer.parseInt(st.nextToken());
        table[out]++;
        graph.get(in).add(out);
        in = out;
      }
    }
    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (table[i] == 0) {
        queue.offer(i);
        count++;
      }
    }

    bfs(queue, graph, count, table, n);
  }

  public static void bfs(Queue<Integer> queue, List<List<Integer>> graph, int count, int[] table, int n) {
    StringBuilder sb = new StringBuilder();

    while (!queue.isEmpty()) {
      int q = queue.poll();
      sb.append(q).append("\n");

      for (int number : graph.get(q)) {
        table[number]--;

        if (table[number] == 0) {
          queue.offer(number);
          count++;
        }
      }
    }
    if (count != n) {
      System.out.println(0);
      return;
    }
    System.out.println(sb);
  }
}

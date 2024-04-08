import java.io.*;
import java.util.*;

public class Main {
  static class Node implements Comparable<Node> {
    int y;
    Long weight;

    Node(int y, Long weight) {
      this.y = y;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight.compareTo(o.weight);
    }
  }

  static ArrayList<ArrayList<Node>> list = new ArrayList<>();
  static boolean[] visited;
  static Long[] dist;
  static int[] office;
  static int n, m, k;
  static int dosi, dap;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    office = new int[k + 1];
    dist = new Long[n + 1];
    for (int i = 0; i <= n; i++) {
      list.add(new ArrayList<Node>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      Long c = Long.parseLong(st.nextToken());
      list.get(b).add(new Node(a, c));
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < k; i++) {
      office[i] = Integer.parseInt(st.nextToken());
    }
    // for (int i = 0; i < k; i++) {
    Long result = Long.MIN_VALUE;
    for (int j = 1; j <= n; j++) {
      dist[j] = Long.MAX_VALUE;
    }
    stra();
    for (int j = 1; j <= n; j++) {
      if (result < dist[j] && dist[j] != Integer.MAX_VALUE) {
        result = dist[j];
        dosi = j;
      }
    }
    // }
    System.out.printf("%d ", dosi);
    System.out.println();
    System.out.println(result);
  }

  public static void stra() {
    visited = new boolean[n + 1];
    PriorityQueue<Node> queue = new PriorityQueue<>();
    // dist[start] = 0;
    for (int i = 0; i < k; i++) {
      queue.offer(new Node(office[i], 0L));
      dist[office[i]] = 0L;
    }
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

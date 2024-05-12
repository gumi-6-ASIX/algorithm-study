import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static class Node implements Comparable<Node> {

    int x, y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Node o) {
      if (this.x == o.x) {
        return this.y - o.y;
      }
      return this.x - o.x;
    }

  }

  static PriorityQueue<Node> pq = new PriorityQueue<>();
  static int n, result, start = 0, end = 0;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      pq.offer(new Node(a, b));
    }
    Node data = pq.poll();
    start = data.x;
    end = data.y;
    result += end - start;
    while (!pq.isEmpty()) {
      Node sun = pq.poll();
      if (start <= sun.x && end >= sun.y)
        continue;
      if (sun.x <= end) {
        result += sun.y - end;
        end = sun.y;
      } else {
        start = sun.x;
        end = sun.y;
        result += end - start;
      }
    }
    System.out.println(result);
  }

}

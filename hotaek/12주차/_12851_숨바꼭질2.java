import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int count;
    static boolean[] visited;

    static class Node {
        int x, time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        count = 0;
        visited = new boolean[100001];
        bfs(new Node(n, 0));
        System.out.println(count);
    }

    public static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x] = true;
        boolean flag = false;
        int result = 0;
        while (!queue.isEmpty()) {
            Node q = queue.poll();
            visited[q.x] = true;

            if (q.x == m) {
                if (!flag) {
                    result = q.time;
                    System.out.println(q.time);
                }
                flag = true;
                if (result == q.time) {
                    count++;
                }
            }
            if (!flag) {
                if (q.x < m) {
                    if (((q.x + 1) < 100001) && !visited[q.x + 1]) {
                        queue.offer(new Node(q.x + 1, q.time + 1));
                    }
                    if (((2 * q.x) < 100001) && !visited[2 * q.x]) {
                        queue.offer(new Node(2 * q.x, q.time + 1));
                    }
                }
                if ((q.x - 1) > -1 && !visited[q.x - 1]) {
                    queue.offer(new Node(q.x - 1, q.time + 1));
                }
            }
        }
    }
}

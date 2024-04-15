import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851_숨바꼭질2 {
    static class Node {
        int x;
        int sec;

        Node(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }

    static int n, k, answer, min_sec, cnt;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 2 };
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        min_sec = 0;
        cnt = 0;

        q = new LinkedList<>();
        q.offer(new Node(n, 0));

        visited = new boolean[1000001];

        bfs();

        System.out.println(min_sec);
        System.out.println(cnt);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (min_sec != 0 && now.sec > min_sec) {
                break;
            }

            if (now.x == k) {
                if (!visited[now.x]) {
                    min_sec = now.sec;
                    cnt++;
                } else {
                    cnt++;
                }
            }

            // visited 처리 여기에!!!!
            visited[now.x] = true;

            for (int i = 0; i < 3; i++) {
                if (i < 2) {
                    int nx = now.x + dx[i];
                    if (nx >= 0 && nx < 1000001 && !visited[nx]) {
                        q.offer(new Node(nx, now.sec + 1));
                    }
                } else {
                    int nx = now.x * dx[i];
                    if (nx < 1000001 && !visited[nx]) {
                        q.offer(new Node(nx, now.sec + 1));
                    }
                }
            }
        }
    }
}

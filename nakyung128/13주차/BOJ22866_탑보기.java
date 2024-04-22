import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ22866_탑보기 {
    static class Node {
        int idx;
        int height;

        Node(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    static int[] buildings, cnt, dist;
    static Stack<Node> stack;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        buildings = new int[n];
        cnt = new int[n];
        dist = new int[n];

        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        stack = new Stack<>();

        watch();

        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) {
                sb.append(0 + "\n");
            } else {
                sb.append(cnt[i] + " " + (dist[i] + 1) + "\n");
            }
        }
        System.out.println(sb);
    }

    static void watch() {
        // 오른쪽
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().height <= buildings[i]) {
                // 어차피 i보다 작은 애들은 앞으로 다 못보기 때문에 pop 해버리자
                stack.pop();
            }

            // 비어 있지 않으면 cnt 추가 및 작은 번호
            if (!stack.isEmpty()) {
                cnt[i] += stack.size();
                dist[i] = stack.peek().idx;
            }
            stack.add(new Node(i, buildings[i]));
        }

        // 왼쪽
        stack.clear();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().height <= buildings[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                cnt[i] += stack.size();
                // 더 가까운 번호로 갱신해 주기 (거리가 같다면 왼쪽으로)
                if (Math.abs(i - dist[i]) >= Math.abs(i - stack.peek().idx)) {
                    dist[i] = stack.peek().idx;
                }
            }
            stack.add(new Node(i, buildings[i]));
        }
    }
}

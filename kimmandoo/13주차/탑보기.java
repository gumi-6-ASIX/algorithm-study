import java.io.*;
import java.util.*;

public class 탑보기 {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] building = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[n + 1]; // i번째 건물이 볼 수 있는 다른 건물의 수
        int[][] near = new int[n + 1][2]; // 가장 가까운 건물번호, 가장 가까운 건물거리
        for (int i = 0; i <= n; i++) {
            near[i][0] = 100001;
            near[i][1] = 100001;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        // 정방향 탐색
        for (int idx = 1; idx <= n; idx++) {
            int v = building[idx];
            while (!stack.isEmpty() && stack.peekLast()[1] <= v) {
                // 건물 못보는 조건 = 같거나 나보다 작으면 안됨
                stack.pollLast();
            }
            cnt[idx] += stack.size(); // 볼 수 있는 건물 개수 저장

            if (!stack.isEmpty()) {
                int dist = Math.abs(stack.peekLast()[0] - idx); // 거리(가장가까운 건물 번호를 찾기 위해서...)
                if (dist < near[idx][1]) {
                    near[idx][0] = stack.peekLast()[0];
                    near[idx][1] = dist;
                } else if (dist == near[idx][1] && stack.peekLast()[0] < near[idx][0]) {
                    // 거리가 같으면 가까운 건물번호로 갱신
                    near[idx][0] = stack.peekLast()[0];
                }
            }

            stack.offerLast(new int[] { idx, v });
        }

        stack.clear(); // 이제 역순 돌아야됨
        for (int idx = n; idx >= 1; idx--) {
            int v = building[idx];
            while (!stack.isEmpty() && stack.peekLast()[1] <= v) {
                stack.pollLast();
            }
            cnt[idx] += stack.size();

            if (!stack.isEmpty()) {
                int dist = Math.abs(stack.peekLast()[0] - idx);
                if (dist < near[idx][1]) {
                    near[idx][0] = stack.peekLast()[0];
                    near[idx][1] = dist;
                } else if (dist == near[idx][1] && stack.peekLast()[0] < near[idx][0]) {
                    near[idx][0] = stack.peekLast()[0];
                }
            }

            stack.offerLast(new int[] { idx, v });
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (cnt[i] > 0) {
                sb.append(cnt[i]).append(" ").append(near[i][0]).append("\n");
            } else { // 탑 못본 애
                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }
}
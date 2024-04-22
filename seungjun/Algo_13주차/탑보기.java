package Baekjoon.Algo_13주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑보기 {

    static class Node {
        int x;
        int weight;

        Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

    }

    static int[] building, near, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        building = new int[n + 1];
        near = new int[n + 1];
        cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            building[i] = Integer.parseInt(st.nextToken());
            near[i] = -100001;
        }
        Stack<Node> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && stack.peek().weight <= building[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                near[i] = stack.peek().x;
                cnt[i] += stack.size();
            }
            stack.push(new Node(i, building[i]));
        }
        stack = new Stack<>();
        for (int i = n; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().weight <= building[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                if (i - near[i] > stack.peek().x - i) {
                    near[i] = stack.peek().x;
                }
                cnt[i] += stack.size();
            }
            stack.push(new Node(i, building[i]));
        }

        for (int i = 1; i <= n; i++) {
            if (near[i] != -100001) {
                sb.append(cnt[i] + " " + near[i] + "\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }
}

package Baekjoon.Algo_12주차;

import java.io.*;
import java.util.*;

public class 숨바꼭질2 {
    static int[] visited = new int[100001];
    static int[] dx = { 1, -1, 2 };
    static int n, k, flag = 0, cnt = 0, miin;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited[n] = 1;
        miin = 111111;
        queue.offer(n);
        bfs();
        System.out.printf("%d\n%d", visited[k] - 1, cnt);
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (visited[now] > miin) {
                return;
            }
            for (int i = 0; i < 3; i++) {
                int a = -1;
                if (i < 2) {
                    a = dx[i] + now;
                } else {
                    a = dx[i] * now;
                }
                if (check(a) && (visited[a] == 0 || visited[now] + 1 == visited[a])) {
                    visited[a] = visited[now] + 1;
                    queue.offer(a);
                }
                if (a == k) {
                    miin = visited[a] - 1;
                    cnt++;
                }
            }
        }
    }

    public static boolean check(int x) {
        return (x >= 0 && x < 100001);
    }

}

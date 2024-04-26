package Baekjoon.Algo_14주차;

import java.io.*;
import java.util.*;

public class 음악프로그램 {

    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static Queue<Integer> queue2 = new LinkedList<>();
    static int n, m;
    static int[] come;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        come = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a - 1; j++) {
                int c = Integer.parseInt(st.nextToken());
                list.get(b).add(c);
                come[c] += 1;
                b = c;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (come[i] == 0) {
                queue.offer(i);
            }
        }

        wesang();
        if (queue2.size() == n) {
            System.out.printf("%s", sb);
        } else {
            System.out.println(0);
        }
    }

    public static void wesang() {
        while (!queue.isEmpty()) {
            int where = queue.poll();
            sb.append(where + "\n");
            queue2.offer(where);
            for (int i = 0; i < list.get(where).size(); i++) {
                come[list.get(where).get(i)]--;
                if (come[list.get(where).get(i)] == 0) {
                    queue.offer(list.get(where).get(i));
                }
            }
        }
    }
}

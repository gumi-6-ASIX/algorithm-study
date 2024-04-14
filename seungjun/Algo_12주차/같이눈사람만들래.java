package Baekjoon.Algo_12주차;

import java.io.*;
import java.util.*;

public class 같이눈사람만들래 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                make(i, j);
            }
        }
        System.out.println(result);
    }

    public static void make(int i, int j) {
        int start = i + 1;
        int end = j - 1;
        while (end > start) {
            int an = list.get(i) + list.get(j);
            int el = list.get(start) + list.get(end);
            result = Math.min(result, Math.abs(an - el));
            if (an > el) {
                start++;
            } else {
                end--;
            }
        }
    }
}

package Baekjoon.Algo_14주차;

import java.io.*;
import java.util.*;

public class 빗물 {
    static int[] num, water1, water2;
    static int h, w, result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        num = new int[w + 2];
        water1 = new int[w + 2];
        water2 = new int[w + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= w; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            water1[i] = Math.max(water1[i - 1], num[i]);
        }
        for (int i = w; i >= 1; i--) {
            water2[i] = Math.min(Math.max(water2[i + 1], num[i]), water1[i]);
        }
        System.out.println(sum(w));
    }

    public static int sum(int a) {
        if (a == 0)
            return 0;
        return water2[a] - num[a] + sum(a - 1);
    }
}

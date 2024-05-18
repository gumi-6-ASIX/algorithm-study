package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _10775_공항 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 0; i < P; i++) {
            int number = Integer.parseInt(br.readLine());
            if (find(number) == 0) {
                break;
            }
            union(find(number), find(number) - 1);
            result++;
        }
        System.out.println(result);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parent[x] = y;

    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

}

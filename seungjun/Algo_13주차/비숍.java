package 백준.알고13주차;

import java.io.*;
import java.util.*;

public class 비숍 {
    static int[][] num;
    static int n, maax = Integer.MIN_VALUE, maaax = Integer.MIN_VALUE;
    static boolean[] viup1, vidown1, viup2, vidown2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        viup1 = new boolean[n * 2 - 1];
        vidown1 = new boolean[n * 2 - 1];
        viup2 = new boolean[n * 2 - 1];
        vidown2 = new boolean[n * 2 - 1];
        System.out.println(checkWhite(0, 0, 0, 0) + checkBlack(0, 0, 0, 0));

    }

    public static int checkBlack(int x, int y, int cnt, int maax) {
        if (cnt > maax) {
            maax = cnt;
        }
        for (int i = x; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == x && j < y) || num[i][j] == 0 || (i % 2 == j % 2) || viup1[i + j] || vidown1[j - i + n - 1]) {
                    continue;
                }
                viup1[i + j] = true;
                vidown1[j - i + n - 1] = true;
                maax = Math.max(maax, checkBlack(i, j, cnt + 1, maax));
                viup1[i + j] = false;
                vidown1[j - i + n - 1] = false;
            }
        }
        return maax;
    }

    public static int checkWhite(int x, int y, int cnt, int maax) {
        if (cnt >= maax) {
            maax = cnt;
        }
        for (int i = x; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == x && j < y) || num[i][j] == 0 || (i % 2 != j % 2) || viup2[i + j]
                        || vidown2[j - i + n - 1]) {
                    continue;
                }
                viup2[i + j] = true;
                vidown2[j - i + n - 1] = true;
                maax = Math.max(maax, checkWhite(i, j, cnt + 1, maax));
                viup2[i + j] = false;
                vidown2[j - i + n - 1] = false;
            }
        }
        return maax;
    }
}

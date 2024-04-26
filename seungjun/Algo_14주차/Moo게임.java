package Baekjoon.Algo_14주차;

import java.io.*;

public class Moo게임 {
    static int n, maax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        moo(0, 2);
    }

    public static void moo(int mo, int a) {
        if (mo >= n) {
            maax = mo;
            return;
        }
        moo((mo * 2) + (a + 1), a + 1);
        if (n >= mo) {
            maax -= mo;
            n -= mo;
            if (n <= a + 1) {
                if (n == 1) {
                    System.out.println("m");
                    System.exit(0);
                } else {
                    System.out.println("o");
                    System.exit(0);
                }
            } else {
                maax -= a + 1;
                n -= a + 1;

            }
        } else {
            return;
        }
        if (n == 1) {
            System.out.println("m");
            System.exit(0);
        }
        return;
    }
}
package Baekjoon.Algo_12주차;

import java.io.*;
import java.util.*;

public class 기적의매매법 {
    static int n, cnt1 = 0, cnt2 = 0, flag1 = 0, flag2 = 0;
    static int[] won;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int pay1 = n;
        int pay2 = n;
        won = new int[15];
        won[0] = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 14; i++) {
            won[i] = Integer.parseInt(st.nextToken());
            if (won[i] <= pay1) {
                cnt1 += pay1 / won[i];
                pay1 -= (pay1 / won[i]) * won[i];
            }
            if (won[i - 1] > 0) {
                if (won[i] > won[i - 1]) {
                    flag1++;
                    flag2 = 0;
                    if (flag1 % 3 == 0) {
                        pay2 += won[i] * cnt2;
                        cnt2 = 0;
                    }
                } else if (won[i] < won[i - 1]) {
                    flag2++;
                    flag1 = 0;
                    if (flag2 >= 3 && won[i] <= pay2) {
                        cnt2 += pay2 / won[i];
                        pay2 -= (pay2 / won[i]) * won[i];
                    }
                }
            }

        }
        if (pay1 + (won[14] * cnt1) == pay2 + (won[14] * cnt2)) {
            System.out.println("SAMESAME");
        } else {
            System.out.println(pay1 + won[14] * cnt1 > pay2 + won[14] * cnt2 ? "BNP" : "TIMING");
        }

    }
}

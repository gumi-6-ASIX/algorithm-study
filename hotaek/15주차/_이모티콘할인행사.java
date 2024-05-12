package 프로그래머스.java.Lv2;

import java.util.*;

class Solution {
    static int[] value;
    static int n;
    static int[] table;
    static int count;
    static int result;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        value = new int[] { 10, 20, 30, 40 };
        n = emoticons.length;

        int[] check = new int[emoticons.length];
        table = new int[users.length];
        count = 0;
        result = 0;
        dfs(0, check, users, emoticons);

        return new int[] { count, result };
    }

    public static void dfs(int depth, int[] check, int[][] users, int[] emo) {
        if (depth == n) {
            int countValue = 0;
            int resultValue = 0;
            table = new int[users.length];
            for (int i = 0; i < check.length; i++) {
                for (int j = 0; j < users.length; j++) {
                    if (check[i] < users[j][0])
                        continue;

                    table[j] += calculate(check[i], emo[i]);
                }
            }
            for (int i = 0; i < users.length; i++) {
                if (table[i] >= users[i][1]) {
                    countValue++;
                } else {
                    resultValue += table[i];
                }
            }

            if (count < countValue) {
                count = countValue;
                result = resultValue;
            } else if (count == countValue && result < resultValue) {
                result = resultValue;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            check[depth] = value[i];
            dfs(depth + 1, check, users, emo);
        }
    }

    public static int calculate(int percent, int price) {
        return price / 100 * (100 - percent);
    }

}

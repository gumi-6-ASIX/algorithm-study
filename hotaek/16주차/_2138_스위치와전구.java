package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2138_스위치와전구 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split("");
        String[] stg = br.readLine().split("");
        int[] answer = new int[n];
        int[] leftTmp = new int[n];
        int[] rightTmp = new int[n];

        for (int i = 0; i < n; i++) {
            answer[i] = Integer.parseInt(st[i]);
            int value = Integer.parseInt(stg[i]);
            leftTmp[i] = value;
            rightTmp[i] = value;
        }

        if (Arrays.equals(answer, leftTmp)) {
            System.out.println(0);
            System.exit(0);
        }

        int leftResult = 1;
        boolean leftFlag = false;
        // 0번 눌렀을 때
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                leftTmp[i] = change(leftTmp[i]);
                leftTmp[i + 1] = change(leftTmp[i + 1]);
            } else if (leftTmp[i - 1] != answer[i - 1]) {
                leftTmp[i - 1] = change(leftTmp[i - 1]);
                leftTmp[i] = change(leftTmp[i]);
                if (i != n - 1) {
                    leftTmp[i + 1] = change(leftTmp[i + 1]);
                }
                leftResult++;
            }

            if (Arrays.equals(leftTmp, answer)) {
                leftFlag = true;
                break;
            }
        }

        int rightResult = 0;
        boolean rightFlag = false;
        // 0번 안눌렀을 때
        for (int i = 1; i < n; i++) {
            if (rightTmp[i - 1] != answer[i - 1]) {
                rightTmp[i - 1] = change(rightTmp[i - 1]);
                rightTmp[i] = change(rightTmp[i]);
                if (i != n - 1) {
                    rightTmp[i + 1] = change(rightTmp[i + 1]);
                }
                rightResult++;
            }

            if (Arrays.equals(rightTmp, answer)) {
                rightFlag = true;
                break;
            }
        }

        if (!leftFlag && !rightFlag) {
            System.out.println(-1);
        } else {
            if (leftFlag && rightFlag) {
                System.out.println(Math.min(leftResult, rightResult));
            } else if (rightFlag) {
                System.out.println(rightResult);
            } else {
                System.out.println(leftResult);
            }

        }

    }

    public static int change(int value) {
        if (value == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

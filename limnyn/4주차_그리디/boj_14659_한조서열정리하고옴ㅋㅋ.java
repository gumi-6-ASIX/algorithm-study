package com.codingtest.알고리즘_스터디.week4_그리디;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14659
public class boj_14659_한조서열정리하고옴ㅋㅋ {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n = sc.nextInt();
        
        int[] mountains = new int[n];
        for (int i = 0; i < n; i++){
            mountains[i] = sc.nextInt();
        }

        int maxHeight = 0;
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < n; i++){
            if (mountains[i] > maxHeight){
                maxHeight = mountains[i];
                count = 0;
            } else {
                count+=1;
                maxCount = Math.max(maxCount, count);
            }
        }
        System.out.println(maxCount);
    }
}

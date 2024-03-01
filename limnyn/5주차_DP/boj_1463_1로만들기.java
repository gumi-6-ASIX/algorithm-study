package com.codingtest.알고리즘_스터디.week5_DP;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class boj_1463_1로만들기 {
//https://www.acmicpc.net/problem/1463
    public static void main(String[] args) throws IOException{   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= n; i++){
            int minNum = Integer.MAX_VALUE;
            if (i % 3 == 0)
                minNum = minNum > dp[i/3] ? dp[i/3] : minNum;
            if (i % 2 == 0)
                minNum = minNum > dp[i/2] ? dp[i/2] : minNum;
            minNum = (minNum > dp[i-1]) ? dp[i-1] : minNum;
            dp[i] = minNum + 1;
        }
        System.out.println(dp[n]);

    }
    
}

package com.codingtest.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14501
public class boj_14501_퇴사 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        int[] days = new int[n];
        int[] costs = new int[n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }

        
        int result = 0; //출력값
        for(int i = n-1; i >= 0; i--){
            int day =days[i];
            int cost = costs[i];
            int dayEnd = i + day;

            if (dayEnd <= n)
                result = Math.max(result, dp[dayEnd]+cost);
            dp[i] = result;
        }
        System.out.println(result);

    }    
}

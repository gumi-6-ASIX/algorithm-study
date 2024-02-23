package com.codingtest.boj;
//14576KB	136ms
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1149_RGB거리 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = Integer.MAX_VALUE;
        int [][]costs = new int[n][3];
        int [][]dp = new int[n][3];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }
        dp[0] = costs[0];
        for (int i = 1; i < n; i++){
            for (int j = 0; j < 3; j++){
                dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + costs[i][j];

                // 마지막 줄 일 때 최소값을 result로 결정
                if (i == n-1) //3항연산자 
                    result = dp[i][j] < result ? dp[i][j] : result;
            }
        }

        System.out.println(result);
    }   
}

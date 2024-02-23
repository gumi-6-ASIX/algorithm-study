package com.codingtest.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1932_정수_삼각형 {
    public static void main(String[] args) throws IOException{
        /**
         * 입력마다 이전줄과 현재줄을 비교하며 바로 수행
         * 현재좌표x 일 때, 이전줄의 x-1, x를 비교하여 dp를 갱신
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[] dp_before = new int[1];
        
        int result = Integer.MIN_VALUE;
        for (int r = 1; r <= n; r++){
            
            
            //line 1차원 배열에 해당하는 열 입력
            int[] line = new int[r];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < r; i++)
                line[i] = Integer.parseInt(st.nextToken());
            
            if (n == 1){
                result = line[0];
                break;
            }

            if (r == 1){
                dp_before = line;
                continue;
            }
            
            int[] dp_now = new int[r];
            for (int c = 0; c < r; c++){

                if (c == 0)
                    dp_now[0] = line[0] + dp_before[0];
                
                else if (c == r-1)
                    dp_now[c] = line[c] + dp_before[c-1];

                else
                    dp_now[c] = line[c] + Math.max(dp_before[c-1], dp_before[c]);

                //최대값 출력을 위해 마지막 줄에서 max 값 찾기
                if (r == n){
                    result = Math.max(result, dp_now[c]);
                }

            }
            // 현재 줄이 이전 줄로 가서 dp 계산에 쓰인다.
            dp_before = dp_now;

            
        }

        System.out.println(result);
        
    }
    
}

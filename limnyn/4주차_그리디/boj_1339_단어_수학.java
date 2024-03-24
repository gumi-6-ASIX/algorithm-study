package com.codingtest.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 1. 단어별 자리수 비중을 구한다
 * 2. 비중이 큰 값 순서대로 9를 부여한다
 * 3. 계산한다.
 */
public class boj_1339_단어_수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        String[] words = new String[n];
        
        for (int i=0; i<n; i++){
            words[i] = br.readLine();
        }
        //단어별로 뒤에서 부터 접근해서 숫자로 변환

        int[][] charSumList = new int[26][2];
        for (String word : words) {
            for (int i=0; i<word.length(); i++){
                int w = word.charAt(i)-65;
                charSumList[w][0] += Math.pow(10, word.length()-1-i); //역순 정렬
                charSumList[w][1] = word.charAt(i);

            }
        }
        Arrays.sort(charSumList, (a,b) -> {
            return b[0]-a[0];
        });

        Map<Integer, Integer> dict = new HashMap<>();
        
        int num = 9;

        for (int i =0; i < 26; i++){
            if (charSumList[i][0] != 0)
                dict.put(charSumList[i][1], num--);

        }
        // System.out.println(dict.toString());

        int result = 0;
        for (String word : words){
            for (int i = 0; i < word.length(); i++){
                int a = (int) (dict.get((int)word.charAt(i)) * Math.pow(10, word.length()-1-i));
                result += a;

                
            }
        }
        System.out.println(result);
    }
}

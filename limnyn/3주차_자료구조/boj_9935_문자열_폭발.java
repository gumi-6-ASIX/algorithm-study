package com.codingtest.알고리즘_스터디.week3_자료구조;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9935
 * 스택 쓰니까 메모리 초과나서 그냥 이렇게 stringbuilder로 풀어봄
 * 
 */
public class boj_9935_문자열_폭발 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String line = sc.next();
        String boom = sc.next();

        StringBuilder stackBoomCheck = new StringBuilder();;
        String boomCheck;
        for (int i = 0; i < line.length(); i++){

            // 한 글자씩 입력받으면서 
            stackBoomCheck.append(line.charAt(i));
            
            
            // 만약 폭탄보다 길이가 크거나 같으면
            if(stackBoomCheck.length() >= boom.length()){

                // 뒤의 폭탄길이만큼의 문장을 boomCheck에 넣고
                boomCheck = stackBoomCheck.substring(stackBoomCheck.length()-boom.length()).toString();
                
                //뒷 n자리 글자 boomCheck가 폭탄과 같다면 (n : boom.length())
                if(boomCheck.equals(boom)){
                    // stringbuilder의 뒷n자리 글자를 삭제하는 방식
                    stackBoomCheck.delete(stackBoomCheck.length()-boom.length(), stackBoomCheck.length());
                }

                // System.out.println(stackBoomCheck); //로그용
            }
        }

        if (stackBoomCheck.length() > 0)
            System.out.println(stackBoomCheck);
        else
            System.out.println("FRULA");
        
    }
}

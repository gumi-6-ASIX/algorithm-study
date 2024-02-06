package com.codingtest.boj;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1158

/*
 * queue에 배열을 넣고 while문을 돌면서
 * poll하고 뒤로 push 하면서
 * k번째를 출력한다.
 * 
 */
public class boj_1158 {
    static Scanner sc = new Scanner(System.in);
    static int n, k;
    
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        List<Integer> result = new ArrayList<>();    
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i <= n; i++){
            q.add(i);
            // System.out.println(q.poll());
        }
        int popOut;
        while (!q.isEmpty()) {
            for(int i = 0; i < k-1; i++){
                popOut = q.poll();
                q.add(popOut);
            }
            popOut = q.poll();
            result.add(popOut);
        }
        System.out.print("<");
        for (int i = 0; i < n-1; i++){
            System.out.printf("%d, ",result.get(i));
        }
            
        
        System.out.printf("%d>\n", result.get(n-1));

    }   
}

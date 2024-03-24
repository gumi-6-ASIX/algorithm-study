package com.codingtest.알고리즘_스터디.week4_그리디;

import java.util.Scanner;

public class boj_1783_병든_나이트 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (n == 1)
            System.out.println(1);
        
        if (n == 2){
            if(m < 8)
                System.out.println((m+1)/2);
            else    
                System.out.println(4);
        }
        if (n >= 3){
            if (m <= 4)
                System.out.println(m);
            else if (m < 6)
                System.out.println(4);
            else
                System.out.println(m-2);
        }
        
    }
    
}

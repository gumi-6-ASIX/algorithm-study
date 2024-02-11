package com.codingtest.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1012
public class boj_1012 {
    public static void main(String[] args) {
        int testCase = sc.nextInt();    
        for(int i = 0; i < testCase; i++){
            System.out.println(solution());
        }
    }
    static Scanner sc = new Scanner(System.in);
    static int[][] grid;
    static int[][] isVisited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int m, n;

    public static int solution(){
        int result = 0;
        int k;
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();
        
        grid = new int[n][m];
        isVisited = new int[n][m];
        for(int i = 0; i < k; i++){
            int c = sc.nextInt();
            int r = sc.nextInt();
            grid[r][c] = 1;
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                    result+=countingBlob(i, j);
                }
            }
        

        return result;
    }
    
    public static int countingBlob(int startR, int startC){
        if(isVisited[startR][startC] == 1 || grid[startR][startC] == 0){
            return 0;
        }
        Queue<int[]> q = new LinkedList<>();
        int[] first_start = {startR, startC};
        q.add(first_start);
        
        isVisited[startR][startC] = 1;
        //0으로 초기화된 2차원 방문배열
        
        while(!q.isEmpty()){
            int[] spot = q.poll();
            int r = spot[0];
            int c = spot[1];

            for (int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m){
                    if (isVisited[nr][nc] == 0){
                        if (grid[nr][nc] == 1){
                            isVisited[nr][nc] = 1;
                            int[] next_spot = {nr, nc};
                            q.add(next_spot);
                        } 
                    }
                    
                }

            }
        }
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < m; j++){
        //         System.out.println("hello");
        //         System.out.printf("%d", isVisited[i][j]);   
        //     }
        // }
        return 1;

    }



    

}

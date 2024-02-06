package com.codingtest.boj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// https://www.acmicpc.net/problem/17086
// 아기상어
// 파이썬으로 푼 코드를 자바로 다시 푼거
// snake_case 코드를 camelCase로 안바꿔버렸다.
public class boj_17086 {
    public static void main(String[] args)throws IOException{
        System.out.println(boj_17086.solution());
    }
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static int[][] grid;
    public static int solution() {
        int result = 0;
        // 입력단
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for(int r =0 ; r < n; r++){
            for (int c = 0; c < m; c++){
                grid[r][c] = sc.nextInt(); 
            }
        }
        
        //각 좌표별로 fs를 수행
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] != 1)
                    result = Math.max(result, bfs(i,j));
            }
        }
        return result;
    }
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int bfs(int start_r, int start_c) {
        Queue<int[]> q = new LinkedList<>();
        int[] first_start = {start_r, start_c};
        q.add(first_start);
        
        //0으로 초기화된 2차원 방문배열
        int[][] is_visited = new int[n][m];
        is_visited[start_r][start_c] = 1;
        while(!q.isEmpty()){
            int[] spot = q.poll();
            int r = spot[0];
            int c = spot[1];

            for (int i = 0; i < 8; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m){
                    if (is_visited[nr][nc] == 0){
                        if (grid[nr][nc] == 0){
                            is_visited[nr][nc] = is_visited[r][c] + 1;
                            int[] next_spot = {nr, nc};
                            q.add(next_spot);
                        } 
                        else if (grid[nr][nc] == 1) {
                            return is_visited[r][c];
                        }
                    }
                    
                }

            }
        }
        return -1;
    }
}

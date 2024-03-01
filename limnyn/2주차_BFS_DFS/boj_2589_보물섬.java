package com.codingtest.알고리즘_스터디.week2_BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2589

public class boj_2589_보물섬 {
    static Scanner sc = new Scanner(System.in);
    static int[][] isVisited;
    static int[][] grid;
    static Queue<int[]> q = new LinkedList<>();
    static int n, m;
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        
        String line;
        for (int i = 0; i < n; i++){
            line = sc.next();
            for(int j = 0; j < m; j++){
                if (line.charAt(j) == 'W'){
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = 1;
                }
            }
        }

        int solved = 0;
        int maxLength = 0;
        for (int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    maxLength = returnMaxPath(i, j);
                    solved = Math.max(solved, maxLength);
                }
            }
        }
        System.out.println(solved);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int returnMaxPath(int startR, int startC) {
        isVisited = new int[n][m];
        isVisited[startR][startC] = 1;
        q.add(new int[] {startR, startC});
        int result = 0;
        
        int[] spot;
        while(!q.isEmpty()){
            spot= q.poll();
            int r = spot[0];
            int c = spot[1];
            
            for (int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                    if (isVisited[nr][nc] == 0 && grid[nr][nc] == 1){
                        isVisited[nr][nc] = isVisited[r][c] + 1;
                        result = isVisited[nr][nc];
                        q.add(new int[] {nr, nc});
                    }
                }
            }
        }
        return result-1;
    }
}

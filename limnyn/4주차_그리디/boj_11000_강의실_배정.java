package com.codingtest.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
가장 빨리 끝나는 시간 중에 가장 늦게 시작하는것만 출력하면 되는 문제

200,000. nlogn으로 정렬 후 계산

"모든 강의"를 수행할 때 필요한 "강의실 수"

알고리즘
    다음 강의시간에 대해
    현재 강의장 중 가장 먼저 끝나는 강의실을 우선적으로 배당한다

1~3
2~4
3~5

1~3
    강의실 끝나는 시간 : [3]
2~4
    강의실 끝나는 시간 : [3]
        시작시간 2가 끝나는 시간 3보다 빠르다
        강의실 추가 배정, 끝나는 시간 삽입 : [3, 4]
3~5
    강의실 끝나는 시간 : [3, 4]
        가장 빨리 끝나는 시간 3 <= 시작 시간 3
        강의실 이어서 사용, 끝나는 시간 갱신
        -> [4, 5]

강의실 별 끝나는 시간 리스트 [4, 5] 의 길이 가 강의실 배정 수
 */
public class boj_11000_강의실_배정 {
    static int[][] timeTable;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        timeTable = new int[n][2];

        
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            timeTable[i][0] = Integer.parseInt(st.nextToken());
            timeTable[i][1] = Integer.parseInt(st.nextToken());
        }
        
        //정렬
        Arrays.sort(timeTable, (a, b) -> {
            if (a[0] == b[0])
                return a[1]-b[1];
            else
                return a[0]-b[0];
        });
        

        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.offer(timeTable[0][1]);
        int start, end;
        for(int i = 1; i < n; i++){
            start = timeTable[i][0];
            end = timeTable[i][1];

            if (start >= q.peek())
                q.poll();
            q.offer(end);
        }
        System.out.println(q.size());

        
    }
}

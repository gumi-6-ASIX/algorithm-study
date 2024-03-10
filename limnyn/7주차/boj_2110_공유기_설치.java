// https://www.acmicpc.net/problem/2110

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110_공유기_설치 {
    /**
     * 파라메트릭 서치 문제
     * 공유기간 최대 거리에 대해서 
     * 이분탐색으로 구한다.
     *  
     */
    static int n, c;
    static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        houses = new int[n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            houses[i] = Integer.parseInt(st.nextToken()); 
        }
        Arrays.sort(houses);

        System.out.println(parametric_search());

    }


    /**
     * 가장 인접한 두 공유기 사이의 거리를 구하는데
     * 1~최대거리 사이에서 가능한 거리를 찾기 위해
     * 이분 탐색으로 범위를 좁히며 조건을 확인한다.
     * @return
     *  가장 인접한 두 공유기 사이의 최대 거리를 반환
     */
    static public int parametric_search(){
        int result = 0;
        int start = 1;
        int end = houses[n-1] - houses[0];;
        
        while(start <= end) {
            int count = 1;
            int before_house = houses[0];
            int distance = (end + start)/2;
            for(int i = 1; i < n; i ++){
                if (houses[i] - before_house >= distance){
                    count += 1;
                    before_house = houses[i];
                }
            }    
            
            
            if (count >= c){
                result = distance;
                start = distance + 1;
            } else
                end = distance - 1;
        }

        return result;
    }
}

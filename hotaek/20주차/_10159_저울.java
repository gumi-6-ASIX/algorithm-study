package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _10159_저울 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Integer>> small = new ArrayList<>();
        List<List<Integer>> big = new ArrayList<>();
        for(int i =0; i<=n ; i++){
            small.add(new ArrayList<Integer>());
            big.add(new ArrayList<Integer>());
        }

  

        for(int i = 0; i< m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            small.get(a).add(b);
            big.get(b).add(a);
        }
        
        for(int i = 1; i <= n ; i++){
            int smallValue = bfs(n,i, small);
            int bigValue = bfs(n,i, big);
            System.out.println((n-1)-smallValue-bigValue);
        }

    }

    public static int bfs(int n, int start, List<List<Integer>> graph){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        int count = 0;

        while(!queue.isEmpty()){
            int q = queue.poll();

            for(int i : graph.get(q)){
                    if(!visited[i]){
                        queue.offer(i);
                        count++;
                        visited[i] = true;
                    }

            }

        }


        return count;
    }
}

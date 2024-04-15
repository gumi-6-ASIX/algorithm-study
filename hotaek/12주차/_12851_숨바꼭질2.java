package 백준.java.골드;

import java.io.*;
import java.util.*;

public class _12851_숨바꼭질2 {
    static int n;
    static int m;
    static int result;
    static int count;

    static class Node{
        int x, time;
        public Node(int x, int time){
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = Integer.MAX_VALUE;
        count = 0;

    
        System.out.println(result);
    }


    public static void bfs(Node start){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        int beforeTime = 0;

        while(!queue.isEmpty()){
            Node q = queue.poll();

                result = q.time;
                beforeTime = q.time;
            }
            

        }
    }
}

package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _5427_불 {
    
    static int n;
    static int m;
    static String[][] graph;
    static int[][] visited;
    static int answer;
    public static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> fires;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++ ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            graph = new String[n][m];

            fires = new LinkedList<>();

            Node start = null;
            
            for(int i = 0; i < n; i ++){
                String[] sts = br.readLine().split("");
                for(int j = 0 ; j < m; j++){
                    String s = sts[j];
                    graph[i][j] = s;
                    if(s.equals("*")){
                        fires.offer(new Node(i,j));
                    }else if(s.equals("@")){
                        start = new Node(i,j);
                    }
                }
            }
            visited = new int[n][m];
            for(int i =0; i < n; i++){
                for(int j = 0; j < m; j++){
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
            
            answer = Integer.MAX_VALUE;
            bfs(start);
            

            if(answer == Integer.MAX_VALUE){
                System.out.println("IMPOSSIBLE");
            }else{
                System.out.println(answer);
            }
        }
    }

    static void bfs(Node start){
        if(start.x == 0 || start.x == (n-1) || start.y == 0 || start.y == (m-1)){
            answer = 1;
            return;
        }

        Queue<Node> queue = new LinkedList<>();

        queue.offer(start);

        visited[start.x][start.y] = 1;

        while(true){

            int fireSize = fires.size();


            for(int i = 0; i < fireSize; i++){
                Node f = fires.poll();

                for(int j = 0; j < 4; j++){
                    int nx = f.x + dx[j];
                    int ny = f.y + dy[j];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                    if(!graph[nx][ny].equals("#") && !graph[nx][ny].equals("*")){
                        fires.offer(new Node(nx,ny));
                        graph[nx][ny] = "*";
                    }
                }
            }


            int qSize = queue.size();
            for(int j = 0; j < qSize; j++){

                Node q = queue.poll();

                for(int i = 0; i < 4 ; i++){
                    int nx = q.x + dx[i];
                    int ny = q.y + dy[i];
    
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
    
                    if(graph[nx][ny].equals("#") || graph[nx][ny].equals("*")) continue;
    
                    if(visited[nx][ny] > visited[q.x][q.y]+1){
                        queue.offer(new Node(nx,ny));
                        visited[nx][ny] = visited[q.x][q.y]+1;
                    }
    
                    if(nx == 0 || nx == (n-1) || ny == 0 || ny == (m-1)){
                        if(visited[nx][ny] < answer){

                            answer = visited[nx][ny];
                        }
    
                    }
                }
            }
            if(queue.isEmpty() && fires.isEmpty()){
                break;
            }

        }
    }
}

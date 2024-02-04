import java.util.*;

public class 토마토 {

    public static int n,m,count;
    public static boolean[][] visited;
    public static int[][] num;
    public static Queue<int[]> queue = new LinkedList<>();
    public static Queue<int[]> queue2 = new LinkedList<>();
    public static int[] dx = {1,0,-1,0};
    public static int[] dy={0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        num=new int[m][n];
        visited = new boolean[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                num[i][j]=sc.nextInt();
                if(num[i][j]==1){
                    queue.offer(new int[]{i,j});
                }

            }
        }
        count=0;
        bfs();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(num[i][j]==0){
                    System.out.println("-1");
                    return;
                }
            }
        }
        System.out.println(count-1);
    }

    public static void bfs(){
        while(!queue.isEmpty()){
            int[] where=queue.poll();
            for(int i=0;i<4;i++){
                int x=where[0]+dx[i];
                int y=where[1]+dy[i];
                if(x>=0 && x<m && y>=0 && y<n && !visited[x][y]&&num[x][y]==0){
                    num[x][y]=1;
                    visited[x][y]=true;
                    queue2.offer(new int[] {x,y});
                }

            }

        }
        count++;
        while(!queue2.isEmpty()){
            queue.offer(queue2.poll());
        }
        if(!queue.isEmpty()){
            bfs();
        }

    }

}

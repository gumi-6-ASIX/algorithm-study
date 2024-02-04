import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연구소 {

    public static int n,m,segu;
    public  static int maax=Integer.MIN_VALUE;
    public static int[][] num;
    public static int[][] num2;
    public static boolean[][] visited;
    public static boolean[][] visited2;
    public static int[] dx={1,0,-1,0};
    public static int[] dy={0,1,0,-1};

    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        num = new int[n][m];
        num2 = new int[n][m];
        visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                num[i][j]=sc.nextInt();
            }
        }
        dfs(0,0,0);

        System.out.println(maax);



    }
    public static void dfs(int x,int y,int cnt){
        if(cnt==3){
            visited2=new boolean[n][m];

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    num2[i][j]=num[i][j];
                    if(num[i][j]==2){

                        queue.offer(new int[]{i,j});
                    }
                }
            }
            while(!queue.isEmpty()){
                int[] where=queue.poll();
                visited2[where[0]][where[1]]=true;
                for(int i=0;i<4;i++){
                    int q=where[0]+dx[i];
                    int w=where[1]+dy[i];
                    if(q>=0 && q<n && w>=0 && w<m && num[q][w]==0 && !visited2[q][w]){
                        num[q][w]=2;
                        queue.offer(new int[]{q,w});
                    }
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(num[i][j]==0){
                        segu+=1;
                    }
                }
            }
            maax=Math.max(maax,segu);
            segu=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    num[i][j]=num2[i][j];
                }
            }
            return;
        }else{
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(num[i][j]==0&&!visited[i][j]){
                        visited[i][j]=true;
                        num[i][j]=1;

                        dfs(i,j,cnt+1);
                        visited[i][j]=false;
                        num[i][j]=0;
                    }

                }
            }

        }



    }
}
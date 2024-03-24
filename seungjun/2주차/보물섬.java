import java.util.*;

public class 보물섬 {

    public static int n,m;
    public static int maax = Integer.MIN_VALUE;
    public static boolean[][] visited;
    public static char[][] num;
    public  static int[][] arr;
    public static Queue<int[]> queue = new LinkedList<>();
    public static int[] dx = {1,0,-1,0};
    public static int[] dy={0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        num=new char[n][m];
        arr=new int[n][m];
        for(int i=0;i<n;i++){
            String s = sc.next();
            for(int j=0;j<m;j++){
                num[i][j]=s.charAt(j);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) {
                if (num[i][j] == 'L') {
                    visited = new boolean[n][m];
                    queue.offer(new int[]{i, j, 0});
                    bfs();
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(num[i][j]=='L'){
                    maax=Math.max(maax,arr[i][j]);
                }
            }
        }
        System.out.println(maax);
    }

    public static void bfs(){

        while(!queue.isEmpty()){
            int[] where=queue.poll();
            visited[where[0]][where[1]]=true;
            for(int i=0;i<4;i++){
                int x=where[0]+dx[i];
                int y=where[1]+dy[i];
                if(x>=0 && x<n && y>=0 && y<m && !visited[x][y]&&num[x][y]=='L'){
                    if(arr[x][y]==0){
                        arr[x][y]=where[2]+1;
                    }else{
                        arr[x][y]=Math.max(arr[x][y],where[2]+1);
                    }
                    visited[x][y]=true;
                    queue.offer(new int[] {x,y,where[2]+1});
                }

            }

        }
  
    }

}

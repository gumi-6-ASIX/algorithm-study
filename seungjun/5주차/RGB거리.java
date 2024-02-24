import java.util.Scanner;

public class RGB거리 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n= sc.nextInt();
        int miin = Integer.MAX_VALUE;
        int[][] num = new int[n][3];
        int[][] sum= new int[n][3];
        for(int i=0;i<n;i++){
            for(int j=0;j<3;j++){
                num[i][j]=sc.nextInt();
            }
        }
        sum[0][0]=num[0][0];
        sum[0][1]=num[0][1];
        sum[0][2]=num[0][2];
        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
                sum[i][j]=Math.min(num[i][j] + sum[i-1][(j+1) % 3],num[i][j] + sum[i-1][(j+2) % 3]);
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            result = Math.min(sum[n-1][i], result);
        }
        System.out.println(result);


    }
}

import java.util.Scanner;

public class 정수삼각형 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int[][] num = new int[n][n];
        int[][] sum= new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                num[i][j]=sc.nextInt();
            }
            for(int j=i+1;j<n;j++){
                num[i][j]=-1;
            }
        }
        sum[0][0]=num[0][0];
        for(int i=1;i<n;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    sum[i][j]=num[i][j]+sum[i-1][j];
                }else if(j==i){
                    sum[i][j]=num[i][j]+sum[i-1][j-1];
                }else{
                    sum[i][j]=Math.max(num[i][j] + sum[i-1][j-1],num[i][j] + sum[i-1][j]);
                }
            }
        }
        int result = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            result = Math.max(sum[n-1][i], result);
        }
        System.out.println(result);


    }
}

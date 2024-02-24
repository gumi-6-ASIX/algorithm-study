import java.util.Scanner;

public class 퇴사 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        int[] day = new int[t+2];
        int[] pay = new int[t+2];
        int[] dp = new int[t+2];
        int maax = Integer.MIN_VALUE;
        for(int i=1;i<=t;i++){
            day[i] = sc.nextInt();
            pay[i] = sc.nextInt();
        }

        for(int i=1;i<=t;i++){
            if(i+day[i]>t+1){
                continue;
            }else{
                dp[i+day[i]]=Math.max(pay[i]+dp[i], dp[i+day[i]]);
                for(int j=i+day[i];j<=t+1;j++){
                    if(dp[j]<dp[i+day[i]]){
                        dp[j]=dp[i+day[i]];
                    }

                }

            }
        }
        for(int i=0;i<=t+1;i++){
            //System.out.println(i+"번째 얻는 돈 : "+dp[i]);
            maax = Math.max(maax,dp[i]);
        }
        System.out.println(maax);
    }
}

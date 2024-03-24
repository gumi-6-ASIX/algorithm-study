import java.util.*;

public class Hanjo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] num=new int[n];
        int cnt=0,d=0,flag=0;
        int maxx=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            num[i]=sc.nextInt();
            if(maxx<num[i]){
                maxx=num[i];
                if(d<cnt){
                    d=cnt;
                }
                cnt=0;
            }else{
                cnt++;
            }
        }
        if(d<cnt){
            d=cnt;
        }
        System.out.println(d);
    }
}

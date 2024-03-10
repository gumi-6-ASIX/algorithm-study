import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] house;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n= Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        house = new int[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            house[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(house);
        int lo=1;
        int hi=house[n-1]-house[0];
        if(m==2){
            System.out.println(hi);
        }else{
            while(lo<hi){
                int mid = (hi+lo)/2;
                if(locationwifi(mid)<m){
                    hi=mid;
                }else{
                    lo=mid+1;
                }
            }
            System.out.println(lo-1);
            
        }
        
    }

    static public int locationwifi(int mid){
        int cnt=1;
        int start=house[0];
        for(int i=0;i< house.length;i++){
            if(house[i]>=start+mid){
                cnt++;
                start=house[i];
            }
        }
        return cnt;
    }
}

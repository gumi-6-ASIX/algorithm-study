import java.util.*;

public class WordMath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] num = new String[n];
        for(int i=0;i<n;i++){
            num[i]=sc.next();
        }

        Integer[] hap = new Integer[28];
        for(int i=0;i<28;i++){
            hap[i]=0;
        }

        for(int i=0;i<n;i++){
            int sut = (int)Math.pow(10, num[i].length()-1);
            for(int j=0;j<num[i].length();j++){
                hap[num[i].charAt(j)-'A']+=sut;
                sut/=10;
            }
        }
        Arrays.sort(hap, Collections.reverseOrder());

        int gob=9;
        int sum=0;
        for(int i=0;i<28;i++){
            if(hap[i]==0){
                break;
            }
            else{
                sum+=hap[i]*gob;
                gob--;
            }
        }
        System.out.println(sum);

    }
}

import java.io.*;
import java.util.*;

public class Main {

    static int n, cnt = 0, cnt1 = 1, flag1 = 0, flag2 = 0;
    static int[] cur, cur1, last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cur = new int[n];
        cur1 = new int[n];
        last = new int[n];
        String num = br.readLine();
        String num1 = br.readLine();
        for (int i = 0; i < n; i++) {
            cur[i] = num.charAt(i) - '0';
        }
        for (int i = 0; i < n; i++) {
            last[i] = num1.charAt(i) - '0';
        }
        cur1 = Arrays.copyOf(cur, n);
        cur1[0] = (cur1[0] + 1) % 2;
        cur1[1] = (cur1[1] + 1) % 2;
        System.out.println(backt());
    }

    public static int backt() {
        for (int i = 1; i < n; i++) {
            if (last[i - 1] != cur[i - 1]) {
                cur[i] = (cur[i] + 1) % 2;
                cur[i - 1] = (cur[i - 1] + 1) % 2;
                if (i != n - 1) {
                    cur[i + 1] = (cur[i + 1] + 1) % 2;
                }
                cnt++;
            }
            if (last[i - 1] != cur1[i - 1]) {
                cur1[i] = (cur1[i] + 1) % 2;
                cur1[i - 1] = (cur1[i - 1] + 1) % 2;
                if (i != n - 1) {
                    cur1[i + 1] = (cur1[i + 1] + 1) % 2;
                }
                cnt1++;
            }
        }
        if(cur[n-1]!=last[n-1] && cur1[n-1]!=last[n-1]){
            return -1;
        }else if(cur[n-1]==last[n-1] && cur1[n-1]==last[n-1]){
            return Math.min(cnt,cnt1);
        }else if(cur[n-1]==last[n-1]){
            return cnt;
        }else{
            return cnt1;
        }

    }

}

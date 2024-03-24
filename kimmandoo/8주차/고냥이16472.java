import java.util.*;
import java.io.*;

public class 고냥이16472 {

    public static void main(String[] args) throws Exception {
        // System.setIn(new java.io.FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        int[] abc = new int[26];
        int i = 0, j = 0;
        abc[input[i] - 'a']++;
        int max = -1;
        int cnt = 1;
        while (j < input.length - 1) {
            j++;
            abc[input[j] - 'a']++;
            if (abc[input[j] - 'a'] == 1) {
                cnt++;
            }
            while (cnt > n && i < j) {
                abc[input[i] - 'a']--;
                if (abc[input[i] - 'a'] == 0) {
                    cnt--;
                }
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        System.out.println(max);
    }
}
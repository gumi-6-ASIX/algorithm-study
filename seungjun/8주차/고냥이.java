import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] alpa = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int cnt = 0;
        int ans = Integer.MIN_VALUE;
        int start = 0;
        for (int end = 0; end < str.length(); end++) {
            if (alpa[str.charAt(end) - 'a'] == 0) {
                cnt++;
            }
            alpa[str.charAt(end) - 'a']++;
            while (n < cnt) {
                alpa[str.charAt(start) - 'a']--;
                if (alpa[str.charAt(start) - 'a'] == 0) {
                    cnt--;
                }
                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        System.out.println(ans);
    }

}

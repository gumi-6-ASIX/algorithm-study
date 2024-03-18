import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16472_고냥이 {
    static int[] alp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 인식할 수 있는 알파벳 종류의 최대 개수
        int n = Integer.parseInt(br.readLine());

        // 문자열
        String nyang = br.readLine();

        alp = new int[26];

        // 최대 길이 (정답)
        int max_len = 0;

        int start = 0;
        int end = 0;
        int cnt = 0;

        while (end < nyang.length()) {

            // 새로운 문자면
            if (alp[nyang.charAt(end) - 'a'] == 0) {
                cnt++; // count + 1
            }

            if (cnt <= n) {
                max_len = Math.max(max_len, end - start + 1);
            }

            while (cnt > n) {
                alp[nyang.charAt(start) - 'a']--;
                if (alp[nyang.charAt(start) - 'a'] == 0) {
                    cnt--;
                }
                start++;
            }

            alp[nyang.charAt(end) - 'a']++;
            end++;
        }

        System.out.println(max_len);
    }
}
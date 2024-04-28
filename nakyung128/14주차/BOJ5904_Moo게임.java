import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904_Moo게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        findChar(0, n, 0);
    }

    static void findChar(int preLen, int n, int k) {
        // 현재 moo 수열의 길이
        int len = 2 * preLen + k + 3;

        // n이 3 이하면
        if (n <= 3) {
            if (n == 1) {
                System.out.println("m");
                System.exit(0);
            } else {
                System.out.println("o");
                System.exit(0);
            }
        }

        // 현재 수열 길이가 n보다 크면
        if (len >= n) {
            // 만약 S(k-1) + k + 3보다 크면 뒷부분 (S(k-1)과 똑같은 부분)
            if (n > preLen + k + 3) {
                n -= preLen + k + 3;
                findChar(0, n, 0);
            } else if (n > preLen && n < preLen + k + 4) { // 만약 이 범위 내면 새로 추가된 문자열 내부 (m + o..o)
                // 이전 문자열의 길이 빼주고
                n -= preLen;

                if (n == 1) {
                    System.out.println("m");
                    System.exit(0);
                } else {
                    System.out.println("o");
                    System.exit(0);
                }
            }
        } else { // 수열보다 n이 크면 수열 증가시키기
            findChar(len, n, k + 1);
        }
    }
}

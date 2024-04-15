import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20366_같이눈사람만들래 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] snow = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n - 3; i++) {
            for (int j = n - 1; j >= i + 3; j--) {
                int elsa_l = i;
                int elsa_r = j;
                int anna_l = i + 1;
                int anna_r = j - 1;

                while (anna_l < anna_r) {
                    int elsa = snow[elsa_l] + snow[elsa_r];
                    int anna = snow[anna_l] + snow[anna_r];

                    answer = Math.min(answer, Math.abs(elsa - anna));

                    if (elsa > anna) {
                        anna_l += 1;
                    } else {
                        anna_r -= 1;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

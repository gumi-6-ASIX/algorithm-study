import java.util.*;
import java.io.*;

public class RGB거리2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] num = new int[n][3];
        int[][] sum = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (i == k) {
                    sum[0][i] = num[0][i];
                } else {
                    sum[0][i] = 1000 * n;
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    sum[i][j] = Math.min(sum[i - 1][(j + 1) % 3], sum[i - 1][(j + 2) % 3]) + num[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                if (i != k && sum[n - 1][i] > 0) {
                    result = Math.min(sum[n - 1][i], result);
                }
            }

        }
        System.out.println(result);

    }
}

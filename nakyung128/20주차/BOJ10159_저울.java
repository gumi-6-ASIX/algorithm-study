import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10159_저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int INF = 1000;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] stuff = new int[n + 1][n + 1];
        for (int[] stu : stuff) {
            Arrays.fill(stu, INF);
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stuff[a][b] = 1; // a가 b보다 무거움. b가 a보다 가벼움.
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    stuff[i][j] = Math.min(stuff[i][k] + stuff[k][j], stuff[i][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && stuff[i][j] == INF && stuff[j][i] == INF) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
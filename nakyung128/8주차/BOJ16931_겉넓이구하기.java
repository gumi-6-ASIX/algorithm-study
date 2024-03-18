import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16931_겉넓이구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] figure = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                figure[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        // 위, 아래
        answer += n * m * 2;

        // 앞뒤양옆
        int side = 0;

        for (int i = 0; i < n; i++) {

            // 맨 앞에 것은 무조건 더해 주기
            side += figure[i][0];

            for (int j = 1; j < m; j++) {
                if (figure[i][j] - figure[i][j - 1] > 0) {
                    side += (figure[i][j] - figure[i][j - 1]);
                }
            }
        }

        answer += side * 2;

        side = 0;

        for (int i = 0; i < m; i++) {
            side += figure[0][i];
            for (int j = 1; j < n; j++) {
                if (figure[j][i] - figure[j - 1][i] > 0) {
                    side += (figure[j][i] - figure[j - 1][i]);
                }
            }
        }

        answer += side * 2;

        System.out.println(answer);
    }
}

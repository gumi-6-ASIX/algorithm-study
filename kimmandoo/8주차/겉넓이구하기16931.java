import java.util.*;
import java.io.*;

public class 겉넓이구하기16931 {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // System.setIn(new java.io.FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        // 앞에서 뒤에 빼면 됨
        int sum = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp != 0) {
                    sum++;
                }
            }
        }

        sum *= 2; // 윗면, 아랫면

        // <-
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 1; j--) {
                int tmp = map[i][j - 1] - map[i][j];// 왼쪽꺼랑
                if (tmp > 0) {
                    sum += tmp;
                }
            }
            sum += map[i][m - 1];
        }
        // ->
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                int tmp = map[i][j + 1] - map[i][j];// 오른쪽꺼랑
                if (tmp > 0) {
                    sum += tmp;
                }
            }
            sum += map[i][0];
        }
        // V
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 1; j--) {
                int tmp = map[j - 1][i] - map[j][i];
                if (tmp > 0) {
                    sum += tmp;
                }
            }
            sum += map[n - 1][i];
        }

        // ^
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                int tmp = map[j + 1][i] - map[j][i];
                if (tmp > 0) {
                    sum += tmp;
                }
            }
            sum += map[0][i];
        }

        System.out.println(sum);
    }

}
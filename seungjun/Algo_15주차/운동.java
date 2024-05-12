import java.io.*;
import java.util.*;

public class 운동 {
    static int v, e, result = Integer.MAX_VALUE;
    static int[][] city;
    static int INF = 5000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        city = new int[v + 1][v + 1];

        for (int i = 0; i <= v; i++) {
            for (int j = 0; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                city[i][j] = INF;
            }
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            city[a][b] = c;
        }
        System.out.println(floyd() == Integer.MAX_VALUE ? -1 : floyd());

    }

    public static int floyd() {
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            for (int j = i; j <= v; j++) {
                if (i == j || city[i][j] == INF || city[j][i] == INF)
                    continue;

                result = Math.min(result, city[i][j] + city[j][i]);

            }

        }
        return result;

    }
}

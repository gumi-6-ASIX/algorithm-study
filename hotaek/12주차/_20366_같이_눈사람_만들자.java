package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _20366_같이_눈사람_만들자 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] graph = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;
        Arrays.sort(graph);

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = (i + 1); j < n; j++) {
                dp[i][j] = graph[i] + graph[j];
            }
        }

        // for (int i = 0; i < n; i++) {
        // System.out.println(Arrays.toString(dp[i]));
        // }

        for (int i = 0; i < n; i++) {
            for (int j = (i + 1); j < n; j++) {
                for (int a = 0; a < n; a++) {
                    if (a == j)
                        break;
                    if (a == i)
                        continue;

                    for (int b = 0; b < n; b++) {
                        if (b == i || b == j)
                            continue;

                        // System.out.println("z " + dp[i][j] + " x " + dp[a][b]);
                        int value = Math.abs(dp[i][j] - dp[a][b]);
                        if (result > value) {
                            // System.out.println(value);
                            result = value;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(result);

    }
}

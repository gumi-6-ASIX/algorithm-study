package 백준.java.골드;

import java.io.*;
import java.util.*;

public class _1956_운동 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] graph = new int[v + 1][v + 1];
        int INF = 10000001;
        // 그래프 초기화
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][dest] = Math.min(graph[start][dest], cost);
        }
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                for (int a = 1; a <= v; a++) {

                    if (graph[i][j] > graph[i][a] + graph[a][j]) {
                        graph[i][j] = graph[i][a] + graph[a][j];
                    }
                }
            }
        }

        int result = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                if (i == j)
                    continue;
                if ((graph[i][j] > 0 || graph[j][i] > 0) && (result > graph[i][j] + graph[j][i])) {
                    result = graph[i][j] + graph[j][i];
                }
            }
        }
        if (result == INF)
            result = -1;

        System.out.println(result);
    }
}

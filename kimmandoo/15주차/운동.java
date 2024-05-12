import java.util.*;
import java.io.*;

class 운동 {
    static int v, e;

    static class Node {
        int nxt, v;

        Node(int nxt, int v) {
            this.nxt = nxt;
            this.v = v;
        }
    }

    static List<Node>[] list;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        // list = new ArrayList[v + 1]; // 1번노드부터 v번 노드까지 존재
        // for (int i = 0; i <= v; i++) {
        // list[i] = new ArrayList();
        // }
        int INF = 100000000;

        int[][] dist = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            dist[from][to] = value;
        }

        // floyd
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (i != j) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int min = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i != j && dist[i][j] != INF && dist[j][i] != INF) {
                    min = Math.min(min, dist[i][j] + dist[j][i]);
                }
            }
        }
        if (min != INF) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }
}
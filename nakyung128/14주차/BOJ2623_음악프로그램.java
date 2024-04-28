import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬
 */

public class BOJ2623_음악프로그램 {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] link;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        link = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] arr = new int[num];

            for (int idx = 0; idx < arr.length; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < arr.length - 1; j++) {
                int first = arr[j];
                int second = arr[j + 1];

                graph.get(first).add(second);
                link[second]++;
            }
        }

        q = new LinkedList<>();
        for (int i = 1; i < link.length; i++) {
            if (link[i] == 0) {
                q.offer(i);
            }
        }

        sort();
    }

    static void sort() {
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now + "\n");

            for (int node : graph.get(now)) {
                if (--link[node] == 0) {
                    q.offer(node);
                }
            }
        }

        // 진입 차수 남아 있는 것이 있으면 순서 못 정하는 것이므로 0 출력
        for (int l : link) {
            if (l != 0) {
                System.out.println(0);
                System.exit(0);
            }
        }

        System.out.println(sb);
    }
}

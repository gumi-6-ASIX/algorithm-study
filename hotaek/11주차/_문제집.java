import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _문제집 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 진입 차수 넣어줄 배열
        int[] table = new int[n + 1];

        // 2차원 리스트 생성
        List<List<Integer>> li = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            li.add(new ArrayList<>());
        }

        // 2차원 리스트에 데이터 넣기 및 진입차수
        for (int i = 0; i < m; i++) {
            StringTokenizer stg = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(stg.nextToken());
            int out = Integer.parseInt(stg.nextToken());
            li.get(in).add(out);
            table[out] += 1;
        }

        System.out.println(bfs(table, li));
    }

    public static String bfs(int[] table, List<List<Integer>> li) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        // 처음 넣기
        for (int i = 1; i < table.length; i++) {
            if (table[i] == 0) {
                table[i] -= 1;
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int out = pq.poll();

            sb.append(out + " ");
            for (int i : li.get(out)) {
                table[i] -= 1;
            }

            for (int i = 1; i < table.length; i++) {
                if (table[i] == 0) {
                    table[i] -= 1;
                    pq.offer(i);
                }
            }
        }

        return sb.toString();
    }
}

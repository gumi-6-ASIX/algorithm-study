import java.util.*;
import java.io.*;

class 숨바꼭질2 {
    static int n, k;
    static int[] time = new int[100001]; // 100000이 마지막
    static int min = Integer.MAX_VALUE;
    static int count = 0;

    static void bfs() { // 최단거리니까 BFS 생각해볼법함
        Queue<Integer> q = new ArrayDeque();
        q.add(n);
        time[n] = 1; // 찾기 시작하니까 1초 추가(방문했다는 의미도 있음)

        while (!q.isEmpty()) {
            int cur = q.poll(); // 현재 위치

            if (min < time[cur]) // 작아지는 경우는 딱 하나
                return;

            // cur+1
            if (cur + 1 >= 0 && cur + 1 <= 100000) {
                if (cur + 1 == k) { // 이동했을때 동생잡으면
                    min = time[cur];
                    count++;
                }
                if (time[cur + 1] == 0 || time[cur + 1] == time[cur] + 1) {
                    // 미방문이거나, 다음시간이 time[cur]+1
                    // (현재시간에서 1추가한거)랑 같으면 다른경우의 수니까 일단 진행
                    // 최단거리가 가능한 경우의 수를 세야하므로 이걸 중복제거 하면 안됨
                    q.add(cur + 1);
                    time[cur + 1] = time[cur] + 1;
                }
            }
            // cur-1
            if (cur - 1 >= 0 && cur - 1 <= 100000) {
                if (cur - 1 == k) {
                    min = time[cur];
                    count++;
                }
                if (time[cur - 1] == 0 || time[cur - 1] == time[cur] + 1) {
                    q.add(cur - 1);
                    time[cur - 1] = time[cur] + 1;
                }
            }
            // cur*2
            if (cur * 2 >= 0 && cur * 2 <= 100000) {
                if (cur * 2 == k) {
                    min = time[cur];
                    count++;
                }
                if (time[cur * 2] == 0 || time[cur * 2] == time[cur] + 1) {
                    q.add(cur * 2);
                    time[cur * 2] = time[cur] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if (n >= k) { // 수빈이가 동생보다 앞에 있으면 작아지는 경우는 한가지뿐
            System.out.println((n - k) + "\n" + "1");
            return;
        }

        bfs();

        System.out.println(min + "\n" + count);
    }
}
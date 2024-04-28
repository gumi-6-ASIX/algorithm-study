import java.io.*;
import java.util.*;

public class 빗물 {
    static int n, m;

    static class Bitmool {
        int idx, h;

        Bitmool(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Deque<Bitmool> s = new ArrayDeque();
        st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        int[] res = new int[m];
        int[] block = new int[m];
        // 일단 넣고, 큰거 나오면 pop, 작은거나오면 차이만큼 더하기
        for (int i = 0; i < m; i++) {
            block[i] = Integer.parseInt(st.nextToken());
            if (s.isEmpty()) {
                s.add(new Bitmool(i, block[i]));
            } else {
                Bitmool tmp = s.peekFirst();
                if (tmp.h > block[i]) {
                    s.add(new Bitmool(i, block[i]));
                } else {
                    // 다 빼고 하나 넣음
                    while (!s.isEmpty()) {
                        Bitmool stacked = s.pollFirst();
                        res[stacked.idx] = tmp.h - stacked.h;
                    }
                    s.add(new Bitmool(i, block[i]));
                }
            }
        }
        s.clear();
        for (int i = m - 1; i > 0; i--) {
            if (s.isEmpty()) {
                s.add(new Bitmool(i, block[i]));
            } else {
                Bitmool tmp = s.peekFirst();
                if (tmp.h > block[i]) {
                    s.add(new Bitmool(i, block[i]));
                } else {
                    // 다 빼고 하나 넣음
                    while (!s.isEmpty()) {
                        Bitmool stacked = s.pollFirst();
                        if (res[stacked.idx] == 0)
                            res[stacked.idx] = tmp.h - stacked.h;
                    }
                    s.add(new Bitmool(i, block[i]));
                }
            }
        }

        for (int i : res) {
            sum += i;
        }
        System.out.println(sum);
    }
}
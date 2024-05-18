import java.util.*;
import java.io.*;

class Main {
    static int g, p;
    static int[] u;

    static void make() {
        for (int i = 0; i <= g; i++) {
            u[i] = i;
        }
    }

    static int find(int a) {
        if (u[a] == a)
            return a;
        return u[a] = find(u[a]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            u[pa] = pb;
            return true; // union 했음
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        u = new int[g + 1];
        make();
        int res = 0;
        for (int i = 0; i < p; i++) {
            int gate = Integer.parseInt(br.readLine());
            int nxt = find(gate); // 비행기가 가리키고 있는 게 대장
            if (nxt == 0)
                break;
            res++;
            union(nxt, nxt - 1);
        }
        System.out.print(res);
    }
}
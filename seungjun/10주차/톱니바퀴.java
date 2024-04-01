import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 톱니바퀴 {
    static int[][] num = new int[4][8];
    static int[] dx = { 1, -1 };
    static int[] hap = { 1, 2, 4, 8 };
    static ArrayList<int[]> list = new ArrayList<>();
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            String arr = br.readLine();
            for (int j = 0; j < 8; j++) {
                num[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
            }
        }
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[] { a, b });
        }

        while (!list.isEmpty()) {
            visited = new boolean[4];
            int x = list.get(0)[0] - 1;
            int role = list.get(0)[1];
            queue.offer(x);
            visited[x] = true;
            while (!queue.isEmpty()) {
                int a = queue.poll();
                for (int i = 0; i < 2; i++) {
                    int ax = dx[i] + a;
                    if (check(ax) && !visited[ax]) {
                        if (ax < a && num[a][6] != num[ax][2]) {
                            visited[ax] = true;
                            queue.offer(ax);
                        } else if (ax > a && num[a][2] != num[ax][6]) {
                            visited[ax] = true;
                            queue.offer(ax);
                        }
                    }

                }
            }
            int mod = x % 2;
            for (int i = 0; i < 4; i++) {
                if (visited[i] && i % 2 == mod) {
                    start(i, role);
                } else if (visited[i] && i % 2 != mod) {
                    start(i, role * -1);
                }
            }
            list.remove(0);
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (num[i][0] == 1) {
                sum += hap[i];
            }
        }
        System.out.println(sum);

    }

    public static boolean check(int x) {
        if (x >= 0 && x < 4)
            return true;
        return false;
    }

    public static void start(int x, int role) {
        if (role == 1) {
            int last = num[x][7];
            for (int i = 7; i > 0; i--) {
                num[x][i] = num[x][i - 1];
            }
            num[x][0] = last;

        } else {
            int fir = num[x][0];
            for (int i = 0; i < 7; i++) {
                num[x][i] = num[x][i + 1];
            }
            num[x][7] = fir;
        }
    }

}

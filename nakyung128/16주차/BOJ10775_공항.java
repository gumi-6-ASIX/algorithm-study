import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10775_공항 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1]; // 게이트 수만큼

        int answer = 0;

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int plane = Integer.parseInt(br.readLine());

            if (find(plane) == 0) {
                break;
            }
            union(find(plane) - 1, find(plane));
            answer++;
        }

        System.out.println(answer);
    }

    static void union(int x, int y) {
        x = find(x); // 부모
        y = find(y); // 부모

        if (x != y) {
            parent[y] = x;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
}

import java.io.*;

public class Main {
    static int g, n, i, a, b;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        parent = new int[g + 1];
        for (i = 1; i <= g; i++) {
            parent[i] = i;
        }
        for (i = 0; i < n; i++) {
            a = Integer.parseInt(br.readLine());
            b = find(a);
            if (b == 0) {
                break;
            }
            union(b, b - 1);
        }
        System.out.println(i);
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[a] = b;
        }
    }
}

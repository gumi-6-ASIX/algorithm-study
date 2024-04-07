import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {
    static class Product {
        int w;
        int v;

        Product(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 물품의 수
        int k = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        ArrayList<Product> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr.add(new Product(w, v));
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            Product p = arr.get(i - 1);
            for (int j = 1; j < k + 1; j++) {
                if (p.w > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - p.w] + p.v);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
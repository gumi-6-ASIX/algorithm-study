import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 스도쿠2239 {
    static int[][] map;
    static boolean end;

    public static void dfs(int depth) {
        if (depth == 81) {
            end = true;
            return;
        }

        int y = depth / 9;
        int x = depth % 9;

        if (map[y][x] != 0) {
            dfs(depth + 1);
        } else {
            for (int i = 1; i <= 9; ++i) {
                if (!isVaild(y, x, i))
                    continue;
                map[y][x] = i;
                dfs(depth + 1);
                if (end)
                    return;
                map[y][x] = 0;
            }
        }
    }

    public static boolean isVaild(int y, int x, int n) {
        for (int i = 0; i < 9; ++i) {
            if (map[y][i] == n || map[i][x] == n)
                return false;
        }

        int yy = y / 3 * 3;
        int xx = x / 3 * 3;
        for (int i = yy; i < yy + 3; ++i) {
            for (int j = xx; j < xx + 3; ++j) {
                if (map[i][j] == n)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        dfs(0);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}

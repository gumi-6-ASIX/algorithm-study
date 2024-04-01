import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤혐브 {
  static int[][] num = new int[101][101];
  static ArrayList<Integer> list;
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int n = Integer.parseInt(br.readLine());
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());

      dragon(x, y, d, g);
    }
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (num[i][j] == 1 && num[i + 1][j] == 1 && num[i][j + 1] == 1 && num[i + 1][j + 1] == 1) {
          cnt++;
        }
      }
    }
    System.out.println(cnt);
  }

  public static void dragon(int x, int y, int d, int g) {
    list = new ArrayList<>();
    list.add(d);
    for (int i = 1; i <= g; i++) {
      for (int j = list.size() - 1; j >= 0; j--) {
        list.add((list.get(j) + 1) % 4);
      }
    }

    num[y][x] = 1;
    for (int idx : list) {
      x += dx[idx];
      y += dy[idx];

      num[y][x] = 1;
    }
  }
}

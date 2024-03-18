import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 스도쿠
 */
public class Main {

  static int[][] num = new int[9][9];
  static int cnt = 0;
  static int flag;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 9; i++) {
      String arr = br.readLine();
      for (int j = 0; j < 9; j++) {
        num[i][j] = Integer.parseInt(String.valueOf(arr.charAt(j)));
        if (num[i][j] == 0) {
          cnt++;
        }
      }
    }
    dfs(0, 0, 0);

  }

  public static void dfs(int x, int y, int s) {
    if (cnt == s) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          System.out.printf("%d", num[i][j]);
        }
        System.out.println();
      }
      System.exit(0);

    }
    for (int i = x; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (j < y && i == x) {
          continue;
        }
        if (num[i][j] == 0) {

          for (int k = 1; k < 10; k++) {
            flag = 0;
            if (checkd(i, j, k)) {
              flag = 1;
              num[i][j] = k;
              dfs(i, j, s + 1);
              num[i][j] = 0;
            }
          }
          if (flag == 0) {
            return;
          }

        }
      }
    }
  }

  public static boolean checkd(int a, int b, int m) {
    for (int i = 0; i < 9; i++) {
      if (num[i][b] == m) {
        return false;
      }
      if (num[a][i] == m) {
        return false;
      }
    }
    if (a < 3) {
      a = 0;
    } else if (a < 6) {
      a = 3;
    } else {
      a = 6;
    }
    if (b < 3) {
      b = 0;
    } else if (b < 6) {
      b = 3;
    } else {
      b = 6;
    }
    for (int i = a; i < a + 3; i++) {
      for (int j = b; j < b + 3; j++) {
        if (num[i][j] == m) {
          return false;
        }
      }
    }
    return true;

  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int n, m, sum, pos, side1, side2;
  static int[][] num;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    num = new int[n][m];
    side1 = 0;
    side2 = 0;
    sum = n * m * 2;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        num[i][j] = Integer.parseInt(st.nextToken());
        if (j == 0) {
          side1 += num[i][j];
          pos = num[i][j];
        } else {
          if (pos < num[i][j]) {
            side1 += num[i][j] - pos;
          }
          pos = num[i][j];
        }
      }
    }
    sum += side1 * 2;
    System.out.println(other());

  }

  public static int other() {

    for (int j = 0; j < m; j++) {
      for (int i = 0; i < n; i++) {
        if (i == 0) {
          side2 += num[i][j];
          pos = num[i][j];
        } else {
          if (pos < num[i][j]) {
            side2 += num[i][j] - pos;
          }
          pos = num[i][j];
        }
      }
    }
    sum += side2 * 2;
    return sum;
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[][] num;
  static int[][] dpmax;
  static int[][] dpmin;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int n = Integer.parseInt(br.readLine());
    num = new int[n][3];
    dpmax = new int[n][3];
    dpmin = new int[n][3];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        num[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int i = 0; i < 3; i++) {
      dpmax[0][i] = num[0][i];
      dpmin[0][i] = num[0][i];
    }
    for (int i = 1; i < n; i++) {
      dpmax[i][0] = Math.max(dpmax[i - 1][0], dpmax[i - 1][1]) + num[i][0];
      dpmax[i][1] = Math.max(Math.max(dpmax[i - 1][0], dpmax[i - 1][1]), dpmax[i - 1][2]) + num[i][1];
      dpmax[i][2] = Math.max(dpmax[i - 1][1], dpmax[i - 1][2]) + num[i][2];
      dpmin[i][0] = Math.min(dpmin[i - 1][0], dpmin[i - 1][1]) + num[i][0];
      dpmin[i][1] = Math.min(Math.min(dpmin[i - 1][0], dpmin[i - 1][1]), dpmin[i - 1][2]) + num[i][1];
      dpmin[i][2] = Math.min(dpmin[i - 1][1], dpmin[i - 1][2]) + num[i][2];
    }
    int maax = Math.max(Math.max(dpmax[n - 1][0], dpmax[n - 1][1]), dpmax[n - 1][2]);
    int miin = Math.min(Math.min(dpmin[n - 1][0], dpmin[n - 1][1]), dpmin[n - 1][2]);
    System.out.printf("%d %d", maax, miin);
  }

}

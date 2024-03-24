import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, 1, 0, -1 };
  static char[][] num = new char[5][5];
  static boolean[][] visited = new boolean[5][5];
  static boolean[][] visited2 = new boolean[5][5];
  static int cnt = 0;
  static int scnt;
  static int ycnt;
  static Queue<int[]> queue = new LinkedList<>();
  static ArrayList<int[]> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 5; i++) {
      String arr = br.readLine();
      for (int j = 0; j < 5; j++) {
        num[i][j] = arr.charAt(j);
      }
    }
    ycnt = 0;
    scnt = 0;
    select(0, 0, 0);
    System.out.println(cnt);

  }

  public static void select(int x, int y, int idx) {
    if (idx == 7) {
      // System.out.println(" s cnt 의 수는 " + scnt);
      // System.out.println(" y cnt 의 수는 " + ycnt);
      visited2 = new boolean[5][5];
      for (int i = 0; i < 5; i++) {
        visited2[i] = visited[i].clone();
      }
      if (scnt >= 4 && isConnected()) {
        cnt++;
      } else {
        return;
      }

    }
    for (int i = x; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (x == i && j < y) {
          continue;
        }
        if (visited[i][j] == false) {
          if (num[i][j] == 'Y') {
            ycnt++;
          } else if (num[i][j] == 'S') {
            scnt++;
          }
          visited[i][j] = true;
          list.add(new int[] { i, j });
          select(i, j, idx + 1);
          if (num[i][j] == 'Y') {
            ycnt--;
          } else if (num[i][j] == 'S') {
            scnt--;
          }
          visited[i][j] = false;
          list.remove(list.size() - 1);
        }

      }
    }
  }

  public static boolean isConnected() {
    int[] where = list.get(0);
    queue.offer(where);
    while (!queue.isEmpty()) {
      where = queue.poll();
      for (int i = 0; i < 4; i++) {
        int x = dx[i] + where[0];
        int y = dy[i] + where[1];
        if (check(x, y) && visited2[x][y]) {
          queue.add(new int[] { x, y });
          visited2[x][y] = false;
        }
      }
    }
    for (int i = 0; i < list.size(); i++) {
      where = list.get(i);
      if (visited2[where[0]][where[1]]) {
        return false;
      }
    }
    return true;

  }

  public static boolean check(int x, int y) {
    if (x >= 0 && x < 5 && y >= 0 && y < 5) {
      return true;
    }
    return false;
  }
}

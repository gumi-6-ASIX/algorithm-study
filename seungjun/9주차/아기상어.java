import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
  static class Node {
    int weight;
    int cnt;

    Node(int weight, int cnt) {
      this.weight = weight;
      this.cnt = cnt;
    }

  }

  static int n, pox, poy, a, miin, sum = 0;
  static int[][] num;
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, 1, 0, -1 };
  static Node[][] shar;
  static int[][] visited;
  static Queue<int[]> queue = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    n = Integer.parseInt(br.readLine());
    num = new int[n][n];
    shar = new Node[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        num[i][j] = Integer.parseInt(st.nextToken());
        if (num[i][j] == 9) {
          shar[i][j] = new Node(2, 0);
          pox = i;
          poy = j;
        } else if (num[i][j] != 0) {
          shar[i][j] = new Node(num[i][j], 0);
        } else {
          shar[i][j] = new Node(0, 0);
        }
      }
    }
    find();
  }

  public static void find() {
    miin = Integer.MAX_VALUE - 1;
    int tarx = -1;
    int tary = -1;
    for (int i = n - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        if (shar[pox][poy].weight > shar[i][j].weight && num[i][j] != 0 && num[i][j] != 9) {
          if (distance(i, j) != -1 && miin >= distance(i, j)) {
            miin = distance(i, j);
            tarx = i;
            tary = j;
          }
        }
      }
    }
    if (tarx == -1 && tary == -1) {
      System.out.println(sum);
      return;
    } else {
      hunt(tarx, tary);
      // System.out.println("=========");
      // for (int i = 0; i < n; i++) {
      // for (int j = 0; j < n; j++) {
      // System.out.print(num[i][j] + " ");
      // }
      // System.out.println();
      // }
      // System.out.println("dis ====" + sum);
      // System.out.println("=========");
      find();
    }
  }

  public static int distance(int x, int y) {
    visited = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(visited[i], -1);
    }
    queue.offer(new int[] { pox, poy });
    visited[pox][poy] = 0;
    while (!queue.isEmpty()) {
      int[] where = queue.poll();
      for (int i = 0; i < 4; i++) {
        int xx = where[0] + dx[i];
        int yy = where[1] + dy[i];
        if (check(xx, yy) && visited[xx][yy] == -1 && shar[xx][yy].weight <= shar[pox][poy].weight) {
          visited[xx][yy] = visited[where[0]][where[1]] + 1;
          queue.add(new int[] { xx, yy });
        }
      }
    }
    return visited[x][y];
  }

  public static void hunt(int tarx, int tary) {
    num[tarx][tary] = 9;
    shar[tarx][tary] = shar[pox][poy];
    shar[pox][poy] = new Node(0, 0);
    pox = tarx;
    poy = tary;
    shar[pox][poy].cnt += 1;
    if (shar[pox][poy].weight == shar[pox][poy].cnt) {
      shar[pox][poy].weight += 1;
      shar[pox][poy].cnt = 0;
    }
    sum += miin;
  }

  public static boolean check(int x, int y) {
    if (x >= 0 && x < n && y >= 0 && y < n) {
      return true;
    }
    return false;
  }
}

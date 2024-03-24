import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
  int x;
  int y;

  Node(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Fish implements Comparable<Fish> {
  int x;
  int y;
  int size;
  int dis;

  Fish(int x, int y, int size, int dis) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.dis = dis;
  }

  // 정렬
  @Override
  public int compareTo(Fish o) {
    if (this.dis != o.dis) {
      return this.dis - o.dis;
    } else if (this.x != o.x) {
      return this.x - o.x;
    } else {
      return this.y - o.y;
    }
  }
}

public class BOJ16236_아기상어 {
  static int n, time, shark, count;
  static int[][] graph;
  static int[][] distance;
  static Queue<Node> queue, queue2;
  static Node now;
  static PriorityQueue<Fish> meals;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());

    graph = new int[n][n];
    queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        graph[i][j] = Integer.parseInt(st.nextToken());
        if (graph[i][j] == 9) {
          queue.offer(new Node(i, j));
          now = new Node(i, j);
        }
      }
    }

    time = 0; // 걸리는 시간 (정답)
    shark = 2; // 기본 크기 2
    bfs();
  }

  // 거리 계산
  static void cal_dis() {
    int[] dx = { -1, 1, 0, 0 };
    int[] dy = { 0, 0, -1, 1 };

    while (!queue2.isEmpty()) {
      Node me = queue2.poll();

      for (int i = 0; i < 4; i++) {
        int nx = me.x + dx[i];
        int ny = me.y + dy[i];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n && graph[nx][ny] <= shark) {
          if (distance[nx][ny] == 0) {
            distance[nx][ny] = distance[me.x][me.y] + 1;
            queue2.offer(new Node(nx, ny));
          }
        }
      }
    }
  }

  static void bfs() {
    while (!queue.isEmpty()) {
      now = queue.poll(); // 현재 상어의 위치

      count_fish(); // 상어가 먹을 수 있는 물고기 배열에 담기

      if (meals.size() == 0) { // 물고기가 0마리면 끝
        System.out.println(time);
        System.exit(0);
      } else if (meals.size() >= 1) { // 물고기가 한 마리 이상 있으면 해당 지점으로 가기
        Fish goal = meals.poll();

        graph[now.x][now.y] = 0;
        graph[goal.x][goal.y] = 9;

        count++; // 먹은 물고기 + 1

        // 자신의 크기만큼 물고기 먹으면 상어 크기 + 1
        if (count == shark) {
          count = 0;
          shark++;
        }

        // 거리만큼 시간 더하기
        time += goal.dis;
        queue.offer(new Node(goal.x, goal.y));
      }
    }
  }

  // 먹을 수 있는 물고기 배열에 담기
  static void count_fish() {
    meals = new PriorityQueue<>();
    distance = new int[n][n];

    queue2 = new LinkedList<>();
    queue2.offer(now);
    cal_dis();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // 먹이이고 내 크기보다 작으며, 거리가 0이 아닌 경우
        if (graph[i][j] != 0 && graph[i][j] != 9 && graph[i][j] < shark && distance[i][j] != 0) {
          meals.add(new Fish(i, j, graph[i][j], distance[i][j]));
        }
      }
    }
  }
}

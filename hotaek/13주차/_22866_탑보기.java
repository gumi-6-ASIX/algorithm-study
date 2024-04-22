package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _22866_탑보기 {

  static class Stack {
    int value, index;

    public Stack(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] array = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }
    int[] cal = new int[n + 1];
    int[][] index = new int[n + 1][2];
    Deque<Stack> stack = new ArrayDeque<>();
    // 왼쪽에서 오른쪽
    for (int i = 1; i <= n; i++) {
      while (!stack.isEmpty()) {
        Stack peek = stack.getLast();
        if (peek.value <= array[i]) {
          stack.pollLast();
        } else if (peek.value > array[i]) {
          index[i][0] = peek.index;
          index[i][1] = Math.abs(i - peek.index);
          cal[i] += stack.size();
          break;
        }
      }
      stack.offer(new Stack(array[i], i));
    }
    stack = new ArrayDeque<>();
    // 오른쪽 왼쪽
    for (int i = n; i >= 1; i--) {
      while (!stack.isEmpty()) {
        Stack peek = stack.getLast();
        if (peek.value <= array[i]) {
          stack.pollLast();
        } else if (peek.value > array[i]) {
          int dist = Math.abs(i - peek.index);
          if (index[i][1] > dist || index[i][0] == 0) {
            index[i][0] = peek.index;
            index[i][1] = dist;
          } else if (index[i][1] == dist && index[i][0] > peek.index) {
            index[i][0] = peek.index;
          }
          cal[i] += stack.size();
          break;
        }
      }
      stack.offer(new Stack(array[i], i));
    }

    for (int i = 1; i <= n; i++) {
      if (cal[i] > 0) {
        System.out.println(cal[i] + " " + index[i][0]);
      } else {
        System.out.println(0);
      }
    }
  }
}

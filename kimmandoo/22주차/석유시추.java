import java.util.*;

class Solution {
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };
    static int[] size;
    static int n, m;
    static boolean[][] v;

    static void bfs(int ci, int cj, int[][] land) {
        Queue<Node> q = new LinkedList();
        q.add(new Node(ci, cj));
        v[ci][cj] = true;
        Set<Integer> block = new HashSet();
        int area = 0;
        while (!q.isEmpty()) {
            Node c = q.poll();
            area++;
            block.add(c.j); // 지나는 열 추가
            for (int d = 0; d < 4; d++) {
                int ni = c.i + di[d];
                int nj = c.j + dj[d];
                if (ni < n && ni >= 0 && nj < m && nj >= 0 && !v[ni][nj] && land[ni][nj] == 1) {
                    q.offer(new Node(ni, nj));
                    v[ni][nj] = true;
                }
            }
        }
        Iterator<Integer> it = block.iterator();
        while (it.hasNext()) {
            size[it.next()] += area;
        }
    }

    public int solution(int[][] land) {
        int answer = 0;
        // 일단 모든 덩어리를 다 탐색하면서 덩어리 번호 - 지나는 열 쌍으로 기록해둠
        // 그리고 덩어리 번호 - 넓이 쌍도 기록해둬야됨
        // 그 다음 열 훑으면서 합이 가장 큰 거로 뽑아내면 됨.
        n = land.length;
        m = land[0].length;
        size = new int[m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j] && land[i][j] == 1)
                    bfs(i, j, land);
            }
        }

        int max = -1;
        for (int i = 0; i < size.length; i++) {
            max = Math.max(max, size[i]);
        }
        // n, m은 잘 들어오는 거 확인했음
        return max;
    }
}

class Node {
    int i, j;

    Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
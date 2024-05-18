class Solution {
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, -1, 1, 0 };
    static String[] strings = { "d", "l", "r", "u" };

    static Node start, end;
    static StringBuilder answer;
    static String result;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        start = new Node(x - 1, y - 1);
        end = new Node(r - 1, c - 1);

        answer = new StringBuilder();
        result = "";

        /**
         * 31번 시간 초과 해결법
         * 거리가 k인 경로를 탐색해야 하므로
         * k - (시작 -> 끝 최단 거리) 한 것이 짝수인 경우에만
         * 거리가 k인 경로가 생길 수 있음. (다른 데 들렀다가 와야 하는데 그럼 2번 움직이니까...)
         */
        int dist = dist(start.x, start.y, end.x, end.y); // 시작 -> 끝 최단 거리
        if (dist > k || (k - dist) % 2 == 1) {
            return "impossible";
        }

        dfs(start.x, start.y, 0, k, n, m);

        if (result == "") {
            return "impossible";
        } else {
            return result;
        }
    }

    static void dfs(int x, int y, int cnt, int k, int n, int m) {
        if (result != "") {
            return;
        }

        if (cnt == k) {
            if (x == end.x && y == end.y) {
                result = answer.toString();
                return;
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && cnt + dist(nx, ny, end.x, end.y) <= k) {
                answer.append(strings[i]);
                dfs(nx, ny, cnt + 1, k, n, m);
                answer.delete(cnt, cnt + 1);
            }
        }
    }

    static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
import java.io.*;
import java.util.*;

public class 다리만들기 {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Path {
        Point start; // 출발 좌표
        Point end; // 도착 좌표
        int startNum; // 시작하는 섬
        int endNum; // 도착하는 섬

        Path(Point start, Point end, int startNum, int endNum) {
            this.start = start;
            this.end = end;
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public boolean equals(Object object) {
            if (object != null && object instanceof Path) {
                Path otherPath = (Path) object;
                return this.start.x == otherPath.start.x && this.start.y == otherPath.start.y
                        && this.end.x == otherPath.end.x && this.end.y == otherPath.end.y
                        && this.startNum == otherPath.startNum && this.endNum == otherPath.endNum;
            }
            return false;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] rangeX = { -1, 0, 1, 0 };
    static int[] rangeY = { 0, 1, 0, -1 };
    static int islandNum = 0; // 섬의 개수
    static ArrayList<Path> pathList = new ArrayList<>(); // 전체 다리
    static ArrayList<Integer> select = new ArrayList<>(); // 일부 선택된 다리
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        int cnt = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    numbering(i, j, visited, cnt);
                    cnt++;
                    islandNum++;
                }
            }
        }

        // 가능한 모든 경로 찾기.
        findPath();

        // 조합 구하기.
        for (int i = 1; i <= pathList.size(); i++) {
            comb(0, pathList.size(), i);
        }

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        System.out.println(ans);
    }

    public static void numbering(int x, int y, boolean[][] visited, int num) {
        map[x][y] = num;
        visited[x][y] = true;

        int dx, dy;
        for (int i = 0; i < 4; i++) {
            dx = x + rangeX[i];
            dy = y + rangeY[i];

            if (dx < 0 || dy < 0 || dx >= N || dy >= M) {
                continue;
            }

            if (map[dx][dy] == 1 && !visited[dx][dy]) {
                visited[dx][dy] = true;
                numbering(dx, dy, visited, num);
            }
        }
    }

    // 조합
    public static void comb(int start, int pathNum, int r) {
        if (r == 0) {
            @SuppressWarnings("unchecked")
            List<Integer>[] a = new ArrayList[islandNum + 2];
            boolean[] visited = new boolean[islandNum + 2];

            for (int i = 0; i <= islandNum + 1; i++) {
                a[i] = new ArrayList<>();
            }

            // 양방향 인접리스트
            for (int i = 0; i < select.size(); i++) {
                int st = pathList.get(select.get(i)).startNum;
                int end = pathList.get(select.get(i)).endNum;
                a[st].add(end);
                a[end].add(st);
            }

            isAllConectected(a, 2, visited); // 2번부터 번호 붙였다.

            for (int i = 2; i < visited.length; i++) {
                if (!visited[i]) {
                    // 다 방문했으면 모두 연결된 상태라는 의미
                    return;
                }
            }

            int total = 0;
            // 모든 다리의 길이를 구함.
            for (int i = 0; i < select.size(); i++) {
                int startX = pathList.get(select.get(i)).start.x;
                int startY = pathList.get(select.get(i)).start.y;
                int endX = pathList.get(select.get(i)).end.x;
                int endY = pathList.get(select.get(i)).end.y;

                if (startX == endX) {
                    total += (endY - startY - 1);
                } else if (startY == endY) {
                    total += (endX - startX - 1);
                }
            }

            ans = Math.min(ans, total);
            return;
        }

        for (int i = start; i < pathNum; i++) {
            select.add(i);
            comb(i + 1, pathNum, r - 1);
            select.remove(select.size() - 1);
        }
    }

    // 인접리스트의 연결 요소의 개수를 구하는 함수.
    public static void isAllConectected(List<Integer>[] a, int start, boolean[] visited) {
        visited[start] = true;
        for (int i : a[start]) {
            if (!visited[i]) {
                isAllConectected(a, i, visited);
            }
        }
    }

    // 모든 경로를 찾는 함수.
    public static void findPath() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    connect(i, j, map[i][j]);
                }
            }
        }
    }

    // 한 점에서 갈 수 있는 다리를 구하는 함수.
    public static void connect(int x, int y, int num) {
        Point start = new Point(x, y); // 시작점
        // 북쪽
        if (x - 1 >= 0 && map[x - 1][y] == 0) {
            int cnt = 0;
            int dx = x - 1, dy = y;
            // 갈 수 있는만큼 쭉 가봄.
            while (map[dx][dy] == 0) {
                cnt++;
                dx--;
                if (dx < 0) {
                    cnt = 0;
                    break;
                }
            }
            addPath(cnt, start, num, dx, dy);
        }

        // 동쪽
        if (y + 1 < M && map[x][y + 1] == 0) {
            int cnt = 0;
            int dx = x, dy = y + 1;
            while (map[dx][dy] == 0) {
                cnt++;
                dy++;

                if (dy >= M) {
                    cnt = 0;
                    break;
                }
            }
            addPath(cnt, start, num, dx, dy);
        }

        // 남쪽
        if (x + 1 < N && map[x + 1][y] == 0) {
            int cnt = 0;
            int dx = x + 1, dy = y;
            while (map[dx][dy] == 0) {
                cnt++;
                dx++;

                if (dx >= N) {
                    cnt = 0;
                    break;
                }
            }
            addPath(cnt, start, num, dx, dy);
        }

        // 서쪽
        if (y - 1 >= 0 && map[x][y - 1] == 0) {
            int cnt = 0;
            int dx = x, dy = y - 1;
            while (map[dx][dy] == 0) {
                cnt++;
                dy--;

                if (dy < 0) {
                    cnt = 0;
                    break;
                }
            }
            addPath(cnt, start, num, dx, dy);
        }
    }

    public static void addPath(int bridge, Point start, int num, int dx, int dy) {
        if (bridge >= 2) {
            Path path = new Path(start, new Point(dx, dy), num, map[dx][dy]);
            Path conversion = new Path(new Point(dx, dy), start, map[dx][dy], num);

            if (!pathList.contains(conversion)) {
                pathList.add(path);
            }
        }
    }
}
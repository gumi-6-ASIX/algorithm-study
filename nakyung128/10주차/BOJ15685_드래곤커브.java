import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15685_드래곤커브 {
    static boolean[][] map;
    static ArrayList<Integer> dirs = new ArrayList<>();
    static int n, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new boolean[101][101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            dirs = new ArrayList<>();
            directions(d, g);
            draw(x, y);
        }

        // 정사각형 개수 세기
        find();
        System.out.println(answer);
    }

    // 정사각형 개수 찾기
    static void find() {
        answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
    }

    // 드래곤 그리기 (true로 표시)
    static void draw(int x, int y) {
        map[x][y] = true;
        for (int dir : dirs) {
            // 오
            if (dir == 0) {
                map[++x][y] = true;
            } else if (dir == 1) { // 위
                map[x][--y] = true;
            } else if (dir == 2) { // 왼
                map[--x][y] = true;
            } else { // 아래
                map[x][++y] = true;
            }
        }
    }

    // 방향 바뀌는 순서 배열
    static void directions(int d, int g) {
        dirs.add(d); // 초기 방향 입력

        // 세대만큼 반복하기
        for (int i = 0; i < g; i++) {
            // 마지막 방향 -> 처음 방향
            for (int j = dirs.size() - 1; j >= 0; j--) {
                int new_direct = (dirs.get(j) + 1) % 4;
                dirs.add(new_direct);
            }
        }
    }
}

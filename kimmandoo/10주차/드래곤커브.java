import java.util.*;
import java.io.*;

class 드래곤커브 {

    static int[] di = { 0, -1, 0, 1 };
    static int[] dj = { 1, 0, -1, 0 }; // 순서대로 0, 1, 2, 3 방향대로 di x dj 선언해둠

    static int ci, cj, d, g;
    static int[][] map;

    public static void dragonCurve() {
        map[ci][cj] = 1; // 시작위치에 표시
        // 커브가 겹칠 수도 있으니까 visited는 필요없다..
        List<Integer> dragon = new ArrayList<>();
        dragon.add(d); // 시작하는 곳 추가
        for (int i = 0; i < g; i++) {
            int len = dragon.size();
            // 뒤집어서 1씩 더하기 -> 넣은 순서대로 뒤에 넣으니까 뒤집는 것과 동일
            for (int j = len - 1; j >= 0; j--) {
                dragon.add((dragon.get(j) + 1) % 4);
            }
        }
        for (int i = 0; i < dragon.size(); i++) {
            int dir = dragon.get(i);
            ci += di[dir];
            cj += dj[dir];
            map[ci][cj] = 1;
        }
    }

    static int cnt;

    public static void cntBox() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 점 4개가 모두 1이면 정사각형으로 처리
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1)
                    cnt++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        map = new int[101][101]; // 0~100
        cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cj = Integer.parseInt(st.nextToken());
            ci = Integer.parseInt(st.nextToken());
            // cj, ci 는 시작점
            d = Integer.parseInt(st.nextToken()); // 시작 방향 0, 1, 2, 3
            g = Integer.parseInt(st.nextToken()); // 세대 -> g세대까지 돌리라는 의미
            dragonCurve();
        }
        cntBox();

        System.out.println(cnt);
    }
}
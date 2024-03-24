import java.io.*;
import java.util.*;

public class 소문난칠공주 {
    static char[][] map;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };
    static boolean visit[];
    static int[] output;
    static int ans;

    static void dfs(int idx, int cnt, int dasom) {
        if (cnt == 7) {
            if (dasom >= 4) {
                bfs();
            }
            return;
        }

        for (int i = idx; i < 25; i++) {
            if (visit[i])
                continue;

            output[cnt] = i;
            visit[i] = true;
            if (map[i / 5][i % 5] == 'S') // 신기해서 써봤음
                dfs(i + 1, cnt + 1, dasom + 1);
            else
                dfs(i + 1, cnt + 1, dasom);
            visit[i] = false;
            output[cnt] = -1;
        }
    }

    static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        int ci = output[0] / 5; // i
        int cj = output[0] % 5; // j
        boolean vis[][] = new boolean[5][5]; // bfs용 방문배열
        int cnt = 1;
        q.offer(new int[] { ci, cj });
        vis[ci][cj] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (int i = 0; i < 4; i++) {
                int ni = node[0] + di[i];
                int nj = node[1] + dj[i];
                if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && !vis[ni][nj] && visit[ni * 5 + nj]) {
                    cnt++;
                    q.add(new int[] { ni, nj });
                    vis[ni][nj] = true;
                }
            }
        }

        if (cnt == 7) { // 길이가 7인 그래프가 되면(잘 이어져있다면)
            ans++;
            return true;
        } else
            return false;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = null; // new StringTokenizer(br.readLine(), " ");
        map = new char[5][5];
        visit = new boolean[25];
        output = new int[7];

        for (int i = 0; i < 5; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line[j];
            }
        }
        // 입력 끝
        // S는 이다솜, Y는 임도연
        // S가 4개 이상 있어야됨. 그래프는 7칸 짜리, 4방으로 인접해있어야됨
        dfs(0, 0, 0);
        // 그냥 백트래킹으로 풀려니까 ㅜ 랑 + 모양이 안됨... 그냥 푸는게 낫다

        System.out.println(ans);
    }
}
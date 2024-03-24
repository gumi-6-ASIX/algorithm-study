import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941_소문난칠공주 {
    static char[][] girls;
    static ArrayList<int[]> girls_arr;
    static boolean[] visited;
    static boolean[][] girls_visited;
    static ArrayList<int[]> team;
    static int answer, count, sCount;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        girls = new char[5][5];
        girls_visited = new boolean[5][5];
        visited = new boolean[25];
        girls_arr = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                girls[i][j] = line.charAt(j);
                girls_arr.add(new int[] { i, j });
            }
        }

        team = new ArrayList<>();
        answer = 0;
        organize(0, 0);
        System.out.println(answer);
    }

    static void organize(int start, int depth) {

        if (depth == 7) {
            // 모두 연결돼 있는지 확인
            count = 1;
            sCount = 0;

            q = new LinkedList<>();
            q.offer(team.get(0));

            if (check()) {
                answer++;
            }

            return;
        }

        for (int i = start; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                girls_visited[girls_arr.get(i)[0]][girls_arr.get(i)[1]] = true;
                team.add(girls_arr.get(i));
                organize(i + 1, depth + 1);
                visited[i] = false;
                girls_visited[girls_arr.get(i)[0]][girls_arr.get(i)[1]] = false;
                team.remove(team.size() - 1);
            }
        }
    }

    static boolean check() {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        boolean[][] check_visited = new boolean[5][5];

        while (!q.isEmpty()) {
            int[] me = q.poll();

            check_visited[me[0]][me[1]] = true;

            // 다솜파 세기
            if (girls[me[0]][me[1]] == 'S') {
                sCount++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = me[0] + dx[i];
                int ny = me[1] + dy[i];

                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                    if (girls_visited[nx][ny] && !check_visited[nx][ny]) {
                        check_visited[nx][ny] = true;
                        count++;
                        q.offer(new int[] { nx, ny });
                    }
                }
            }
        }

        if (count == 7 && sCount >= 4)
            return true;
        else
            return false;
    }
}
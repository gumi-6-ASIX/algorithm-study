import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16234_인구이동 {
    static int N, L, R;
    static int[][] country; // 국가 인구수 2차원 배열
    static boolean[][] enable; // 국경선 열렸는가 여부
    static int hap; // 연합국의 총 인구수
    static int cnt; // 연합국 개수
    static boolean move; // 인구 이동 발생하는가
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        country = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        enable = new boolean[N][N];
        cnt = 1; // 연합국 개수 초기화
        move = false;
        answer = 0;

        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!enable[i][j]) {
                        hap = country[i][j]; // 연합국 인구수 초기화 (시작 지점으로)

                        dfs(i, j);

                        // 연합국 개수 2개 이상이면
                        if (cnt > 1) {
                            // 국경선 열린 거 다 돌았으면 연합국 평균으로 바꿔주기
                            int avg = hap / cnt; // (연합 인구수) / (연합 이루는 칸의 개수)
                            for (int k = 0; k < N; k++) {
                                for (int l = 0; l < N; l++) {
                                    // 연합국이 있으면 평균값으로 갱신해 주기
                                    if (enable[k][l]) {
                                        country[k][l] = avg;
                                    }
                                }
                            }
                            enable = new boolean[N][N]; // 국경선 닫기
                            cnt = 1; // 연합국 개수 초기화
                            move = true; // 인구이동 발생
                        }
                    }
                }
            }
            // 인구 이동 발생했으면
            if (move) {
                answer++; // 결과 + 1
                move = false; // 초기화
            } else { // 발생 끝났으면 break
                break;
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y) {
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        enable[x][y] = true; // 국경선 열기

        // 상하좌우 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                // 국경선이 열려 있지 않고 둘의 차이가 L 이상 R 이하일 때
                if (!enable[nx][ny]) {
                    int cha = Math.abs(country[x][y] - country[nx][ny]);
                    if (cha >= L && cha <= R) {
                        cnt++; // 연합국 개수 + 1
                        hap += country[nx][ny]; // 연합국 인구수 더하기
                        dfs(nx, ny);
                    }
                }
            }
        }
    }
}

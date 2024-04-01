import java.util.*;
import java.io.*;

class 톱니바퀴 {
    static int[][] topni;

    public static void rotate(int[] arr, int dir) {
        if (dir == 1) {
            int tmp = arr[7];
            for (int i = 7; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = tmp;
        } else {
            int tmp = arr[0];
            for (int i = 0; i < 7; i++) {
                arr[i] = arr[i + 1];
            }
            arr[7] = tmp;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        topni = new int[4][8]; // 12시 방향부터 시계방향 순서대로 줌
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                topni[i][j] = line.charAt(j) - '0';
            }
        }
        int n = Integer.parseInt(br.readLine());

        for (int k = 0; k < n; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            int topniNum = Integer.parseInt(st.nextToken()) - 1;
            int[] dir = new int[4];// 회전방향기억
            dir[topniNum] = Integer.parseInt(st.nextToken());
            // 이제 n 기준으로 돌아야됨
            for (int i = topniNum; i < 3; i++) {// 오른쪽체크
                if (topni[i][2] != topni[i + 1][6])
                    dir[i + 1] = -dir[i]; // 0이면 안움직일거고, 아니면 반대로
            }
            for (int i = topniNum; i > 0; i--) {// 왼쪽체크
                if (topni[i - 1][2] != topni[i][6])
                    dir[i - 1] = -dir[i];
            }
            for (int i = 0; i < 4; i++) {
                if (dir[i] != 0)
                    rotate(topni[i], dir[i]);
            }
        }
        System.out.println(topni[0][0] + topni[1][0] * 2 + topni[2][0] * 4 + topni[3][0] * 8);
    }
}
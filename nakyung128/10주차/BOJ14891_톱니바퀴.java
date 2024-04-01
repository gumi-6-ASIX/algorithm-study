import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {
    static boolean[] diff;
    static int[] one, two, three, four;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        one = new int[8];
        two = new int[8];
        three = new int[8];
        four = new int[8];

        String line = br.readLine();
        for (int i = 0; i < 8; i++) {
            one[i] = line.charAt(i) - '0';
        }

        line = br.readLine();
        for (int i = 0; i < 8; i++) {
            two[i] = line.charAt(i) - '0';
        }

        line = br.readLine();
        for (int i = 0; i < 8; i++) {
            three[i] = line.charAt(i) - '0';
        }

        line = br.readLine();
        for (int i = 0; i < 8; i++) {
            four[i] = line.charAt(i) - '0';
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 회전 횟수

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            diff = new boolean[3];
            swap(num, dir);
        }

        // 네 톱니바퀴 점수의 합 구하기
        int answer = 0;
        if (one[0] == 1) {
            answer += 1;
        }
        if (two[0] == 1) {
            answer += 2;
        }
        if (three[0] == 1) {
            answer += 4;
        }
        if (four[0] == 1) {
            answer += 8;
        }

        System.out.println(answer);
    }

    static void swap(int num, int dir) {
        whereDiff();
        if (num == 1) {
            turn(one, dir);
            if (diff[0]) {
                turn(two, -dir);
                if (diff[1]) {
                    turn(three, dir);
                    if (diff[2]) {
                        turn(four, -dir);
                    }
                }
            }
        } else if (num == 2) {
            turn(two, dir);
            if (diff[0]) {
                turn(one, -dir);
            }
            if (diff[1]) {
                turn(three, -dir);
                if (diff[2]) {
                    turn(four, dir);
                }
            }
        } else if (num == 3) {
            turn(three, dir);
            if (diff[2]) {
                turn(four, -dir);
            }
            if (diff[1]) {
                turn(two, -dir);
                if (diff[0]) {
                    turn(one, dir);
                }
            }
        } else {
            turn(four, dir);
            if (diff[2]) {
                turn(three, -dir);
                if (diff[1]) {
                    turn(two, dir);
                    if (diff[0]) {
                        turn(one, -dir);
                    }
                }
            }
        }
    }

    // 맞닿은 톱니의 극이 다른 곳 찾기
    static void whereDiff() {
        if (one[2] != two[6]) {
            diff[0] = true;
        }

        if (two[2] != three[6]) {
            diff[1] = true;
        }

        if (three[2] != four[6]) {
            diff[2] = true;
        }
    }

    static void turn(int[] arr, int dir) {
        if (dir == 1) {
            int origin = arr[7];
            for (int i = 6; i >= 0; i--) {
                arr[i + 1] = arr[i];
            }
            arr[0] = origin;
        } else {
            int origin = arr[0];
            for (int i = 1; i < 8; i++) {
                arr[i - 1] = arr[i];
            }
            arr[7] = origin;
        }
    }
}
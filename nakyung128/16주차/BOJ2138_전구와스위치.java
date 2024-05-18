import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138_전구와스위치 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] origin = new int[n];
        int[] goal = new int[n];

        int nonPressCnt = 0;
        int pressCnt = 1;

        String origiString = br.readLine();
        for (int i = 0; i < n; i++) {
            origin[i] = origiString.charAt(i) - '0';
        }

        String goalString = br.readLine();
        for (int i = 0; i < n; i++) {
            goal[i] = goalString.charAt(i) - '0';
        }

        // 만약 처음부터 같은 상태면?
        if (isEqual(origin, goal)) {
            System.out.println(0);
            System.exit(0);
        }

        boolean press = false;
        boolean nonPress = false;

        // 첫 번째 전구를 누르지 않는 경우
        int[] temp = origin.clone();
        for (int i = 1; i < n; i++) {
            // 내 앞의 것이 목표 것이랑 다르면 누르기
            if (temp[i - 1] != goal[i - 1]) {
                if (i < n - 1) {
                    threeOnOff(temp, i);
                } else {
                    twoOnOff(temp, i);
                }

                nonPressCnt++;
            }
        }

        // 목표랑 같은지 비교하기
        if (isEqual(temp, goal)) {
            nonPress = true;
        }

        // 첫 번째 전구를 누르는 경우
        twoOnOff(origin, 1);

        for (int i = 1; i < n; i++) {
            // 내 앞의 것이 목표 것이랑 다르면 누르기
            if (origin[i - 1] != goal[i - 1]) {
                if (i < n - 1) {
                    threeOnOff(origin, i);
                } else {
                    twoOnOff(origin, i);
                }
                pressCnt++;
            }
        }

        if (isEqual(origin, goal)) {
            press = true;
        }

        if (press && nonPress) {
            System.out.println(Math.min(pressCnt, nonPressCnt));
        } else if (nonPress) {
            System.out.println(nonPressCnt);
        } else if (press) {
            System.out.println(pressCnt);
        } else if (!press && !nonPress) {
            System.out.println(-1);
        }

    }

    static boolean isEqual(int[] arr, int[] goal) {
        for (int i = 0; i < n; i++) {
            if (arr[i] != goal[i]) {
                return false;
            }
        }
        return true;
    }

    static void twoOnOff(int[] origin, int i) {
        if (origin[i - 1] == 0) {
            origin[i - 1] = 1;
        } else {
            origin[i - 1] = 0;
        }

        if (origin[i] == 0) {
            origin[i] = 1;
        } else {
            origin[i] = 0;
        }
    }

    static void threeOnOff(int[] origin, int i) {
        if (origin[i - 1] == 0) {
            origin[i - 1] = 1;
        } else {
            origin[i - 1] = 0;
        }

        if (origin[i] == 0) {
            origin[i] = 1;
        } else {
            origin[i] = 0;
        }

        if (origin[i + 1] == 0) {
            origin[i + 1] = 1;
        } else {
            origin[i + 1] = 0;
        }
    }
}

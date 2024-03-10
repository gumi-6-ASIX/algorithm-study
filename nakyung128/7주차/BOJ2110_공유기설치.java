import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110_공유기설치 {
    static int n, c;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        house = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            house[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(house); // 오름차순 정렬 (이분 탐색을 위함)

        int min_dist = 1; // 최소 거리가 가질 수 있는 최솟값

        // Upper bound 방식이기 떄문에 구하는 값보다 큰 값이 처음으로 나오는 위치가 반환됨
        // 그러므로 max_dist에도 +1을 해 주어야 함
        int max_dist = house[n - 1] - house[0] + 1; // 최소 거리가 가질 수 있는 최댓값

        while (min_dist < max_dist) {
            int mid = (max_dist + min_dist) / 2;

            // 공유기 설치 개수가 부족하다면 최소 거리를 줄여야 한다
            if (!install(mid)) {
                max_dist = mid;
            } else {
                // 설치 개수가 c보다 크거나 같으면 최소 거리의 최대를 찾는다
                min_dist = mid + 1;
            }
        }

        // 찾고자 하는 값보다 최초로 큰 값을 반환하므로 - 1
        System.out.println(min_dist - 1);
    }

    // 공유기 설치 c개 이상 가능 여부
    static boolean install(int dist) {
        int count = 1; // 첫 번째 집에는 무조건 설치하기
        int idx = house[0];

        for (int i = 1; i < n; i++) {
            if (house[i] - idx >= dist) {
                count++;
                idx = house[i];
            }
        }

        if (count < c)
            return false;
        else
            return true;
    }

}

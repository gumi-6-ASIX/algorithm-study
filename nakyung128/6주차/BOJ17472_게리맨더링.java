import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17472_게리맨더링 {
    static int n;
    static int[] people;
    static ArrayList<ArrayList<Integer>> node;
    static ArrayList<Integer> zone1;
    static ArrayList<Integer> zone2;
    static int answer;
    static ArrayList<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        people = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        node = new ArrayList<>();
        node.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            node.add(arr);
        }

        zone1 = new ArrayList<>();
        zone2 = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for (int r = 1; r <= n / 2; r++) {
            devide(1, r, 0);
        }

        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
        } else
            System.out.println(-1);
    }

    // 구역 나누기
    static void devide(int start, int r, int depth) {
        if (depth == r) {
            // zone2 생성하기
            zone2 = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!zone1.contains(i))
                    zone2.add(i);
            }

            // zone1 연결 여부 확인
            visited = new ArrayList<>();
            boolean ok1 = true;
            allConnect(zone1.get(0), zone1);
            for (int z : zone1) {
                if (!visited.contains(z)) {
                    ok1 = false;
                }
            }

            // zone2 연결 여부 확인
            visited = new ArrayList<>();
            boolean ok2 = true;
            allConnect(zone2.get(0), zone2);
            for (int z : zone2) {
                if (!visited.contains(z)) {
                    ok2 = false;
                }
            }

            // 둘 다 연결돼 있으면 계산하기
            if (ok1 && ok2) {
                answer = Math.min(answer, cal());
            }

            return;
        }

        for (int i = start; i <= n; i++) {
            zone1.add(i);
            devide(i + 1, r, depth + 1);
            zone1.remove(zone1.size() - 1);
        }
    }

    // 선거구 내에 연결 돼 있지 않은 구역 있는지 파악
    static void allConnect(int now, ArrayList<Integer> zone) {
        visited.add(now);

        for (int link : node.get(now)) {
            if (!visited.contains(link) && zone.contains(link)) {
                allConnect(link, zone);
            }
        }
    }

    // 두 선거구의 인구 차이 계산
    static int cal() {
        int zone1_ppl = 0;
        int zone2_ppl = 0;

        for (int z : zone1) {
            zone1_ppl += people[z];
        }

        for (int z : zone2) {
            zone2_ppl += people[z];
        }

        return Math.abs(zone1_ppl - zone2_ppl);
    }
}

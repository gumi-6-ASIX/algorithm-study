import java.io.*;
import java.util.*;

public class BOJ2110 {
	static int[] home;
	static int n;
	static int c;

	static int count(int m) { // 공유기 개수 세기
		int cnt = 1; // 시작점에 설치하고 시작함.
		int prev = home[0];

		for (int i = 1; i < n; i++) {
			int d = home[i] - prev; // 현재 지점부터 i번째 집까지의 거리를 계산하기
			if (d >= m) { // m보다 현재 거리가 크거나 같으면 공유기를 놓고 거리 갱신
				cnt++;
				prev = home[i]; // 이러면 다음 갱신될 거리는 늘어나게 됨 -> c만큼 공유기 설치하면 최장거리 완성
			}
		}
		return cnt;
	}

	static int parametric() {
		int l = 0;
		int r = home[n - 1] - home[0];// 최대 거리 -> 사람 위치 수가 아니라 거리를 기준으로 잡는다.
		
		int ans = 0;

		while (l <= r) {
			int m = (l + r) / 2;
			int cnt = count(m);
			if (cnt >= c) { // 공유기 늘릴 수 있음
				ans = m;
				l = m + 1;
			} else { // 공유기 줄여야 함
				r = m - 1;
			}
		}

		return ans;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		home = new int[n];
		// 그래프 초기화
		for (int i = 0; i < n; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(home);
		//System.out.println(Arrays.toString(home));

		System.out.println(parametric());
	}
}

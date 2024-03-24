import java.io.*;
import java.util.*;

public class BOJ1827 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		// i번째 주유소까지 거리, 거기서 채울수 있는 연료
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[0] - o2[0];
		});
		// 연료기준 max heap;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		st = new StringTokenizer(br.readLine(), " ");
		int L = Integer.parseInt(st.nextToken()); // 목적지까지 거리
		int P = Integer.parseInt(st.nextToken()); // 초기 연료 값
		int pos = 0; // 현재위치
		int cnt = 0;
		
		
		PriorityQueue<int[]> fuel = new PriorityQueue<>((o1, o2) -> {
			return o2[1] - o1[1];
		});
		
		while (P < L) { // 연료량이 L 넘어가면 끝내기
			while (!pq.isEmpty() && pq.peek()[0] <= P) {
				fuel.add(pq.poll()); // 갈수있는 주유소 중에 가장 연료가 많이 남아있는 주유소를 선택할 수 있도록
			}

			if (fuel.isEmpty()) {
				System.out.println(-1);
				return;
			}

			cnt++;
			P += fuel.poll()[1];
			// System.out.println(P);
		}

		System.out.println(cnt);
	}
}
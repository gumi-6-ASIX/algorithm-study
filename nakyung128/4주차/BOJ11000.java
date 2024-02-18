import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int[][] times = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}

		// 시작 시간 오름차순으로 정렬하기
		Arrays.sort(times, (s1, s2) -> {
			int minus = s1[0] - s2[0];
			return minus == 0 ? s1[1] - s2[1] : minus;
		});

		pq.add(times[0][1]);
		
		for (int i = 1; i < times.length; i++) {
			int newStart = times[i][0];
			if (newStart >= pq.peek()) {
				pq.poll();
			}
			pq.add(times[i][1]);
		}
		
		
		System.out.println(pq.size());
	}
}
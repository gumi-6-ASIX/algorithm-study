import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test {

	static int N, M;
	static int[] preProblems;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		preProblems = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);

			preProblems[to]++;
		}

		for (int i = 1; i <= N; i++) {
			if (preProblems[i] == 0) {
				pq.add(i);
			}
		}
		
		findOrder();

	}

	public static void findOrder() {
		StringBuilder sb = new StringBuilder();
		
		while (!pq.isEmpty()) {
			int number = pq.poll();
			sb.append(number + " ");

			for (int nextNum : graph.get(number)) {
				preProblems[nextNum]--;
				
				if (preProblems[nextNum] == 0) {
					pq.add(nextNum);
				}
			}
		}
		
		System.out.println(sb);

	}
}

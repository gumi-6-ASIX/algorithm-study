import java.io.*;
import java.util.*;

public class BOJ1916 {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		final int MAX = 100000000;

		int n = Integer.parseInt(br.readLine()); // V
		int m = Integer.parseInt(br.readLine()); // E
		int[][] graph = new int[n + 1][n + 1];
		int[] cost = new int[n + 1];
		boolean[] check = new boolean[n + 1];

		// 그래프 초기화
		for (int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], MAX);
		}
		Arrays.fill(cost, MAX);

		// 입력 받아 그래프 구성
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[s][e] = Math.min(graph[s][e], v); // 더 작은 가중치로 갱신
		}

		st = new StringTokenizer(br.readLine(), " ");
		int sNode = Integer.parseInt(st.nextToken());
		int eNode = Integer.parseInt(st.nextToken());
//		System.out.println(sNode + "|" + eNode);
		// 다익스트라 알고리즘 수행
		cost[sNode] = 0; // 시작 지점은 cost 0으로 설정

		for (int i = 1; i <= n; i++) {
			int min = MAX;
			int minIdx = -1;

			// 아직 처리되지 않은 정점 중 최소 비용을 가진 정점 선택
			for (int j = 1; j <= n; j++) {
				if (cost[j] < min && !check[j]) {
					min = cost[j];
					minIdx = j;
				}
			}

			if (minIdx == -1)
				break; // 모든 정점이 처리됨

			check[minIdx] = true; // 선택된 정점 확정 처리

			// 선택된 정점을 경유하여 다른 정점까지의 최단 거리 갱신
			for (int j = 1; j <= n; j++) {
				if (cost[j] > cost[minIdx] + graph[minIdx][j]) {
					cost[j] = cost[minIdx] + graph[minIdx][j];
				}
			}
		}

		// 결과 출력
		System.out.println(cost[eNode]);
	}
}
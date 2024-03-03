package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17471 {
	static int N, minDiff = Integer.MAX_VALUE, totalSum;
	static int[] population;
	static boolean[] isVisited;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> tempList = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		isVisited = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalSum += population[i];

			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		findGroup(1, 0, 0);

		if (minDiff == Integer.MAX_VALUE) {
			minDiff = -1;
		}
		System.out.println(minDiff);
	}

	public static void findGroup(int node, int cnt, int sumPop) {
		if (cnt > N / 2) {
			return;
		}

		// 노드들이 연결되어 있는지 확인
		if (cnt > 0) {
			boolean isConnectedA = false;
			isConnectedA = isConnected(tempList.get(0), tempList);

			// 노드들이 연결되어 있는지 확인
			boolean isConnectedB = false;
			ArrayList<Integer> otherList = new ArrayList<Integer>();
			for (int i = 1; i <= N; i++) {
				if (!tempList.contains(i)) {
					otherList.add(i);
				}
			}
			isConnectedB = isConnected(otherList.get(0), otherList);

			// 연결되어있다면 인구수 차이 구하기 및 갱신
			if (isConnectedA && isConnectedB) {
				int tempDiff = Math.abs((totalSum - sumPop) - sumPop);
				minDiff = Math.min(minDiff, tempDiff);
			}
		}

		for (int i = node; i <= N; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				tempList.add(i);
				findGroup(i, cnt + 1, sumPop + population[i]);
				isVisited[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static boolean isConnected(int node, ArrayList<Integer> list) {
		boolean[] tempVisited = new boolean[N + 1];
		int size = list.size();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		tempVisited[node] = true;
		size--;

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			for (int i = 0; i < graph[currentNode].size(); i++) {
				int nextNode = graph[currentNode].get(i);

				if (list.contains(nextNode) && !tempVisited[nextNode]) {
					tempVisited[nextNode] = true;
					queue.add(nextNode);
					size--;
				}
			}
		}

		if (size == 0) {
			return true;
		}

		return false;
	}

}

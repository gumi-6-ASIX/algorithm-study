package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ17135 {

	static int R, C, D, maxKill;
	static boolean[] isVisited;
	static ArrayList<Node> enemies = new ArrayList<Node>();
	static ArrayList<Integer> tempArcher = new ArrayList<Integer>();

	static class Node implements Comparable<Node> {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			return this.y - o.y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		isVisited = new boolean[C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					enemies.add(new Node(i, j));
				}
			}
		}
		enemies.sort(null);
		findArcher(0, 0);
		System.out.println(maxKill);

	}

	public static void findArcher(int cnt, int idx) {
		if (cnt == 3) {
			ArrayList<Node> copyEnemies = (ArrayList<Node>) enemies.clone();
			maxKill = Math.max(maxKill, play());
			enemies = (ArrayList<Node>) copyEnemies.clone();
			return;
		}

		for (int i = idx; i < C; i++) {
			if (!isVisited[i]) {
				tempArcher.add(i);
				isVisited[i] = true;
				findArcher(cnt + 1, i);
				tempArcher.remove(tempArcher.size() - 1);
				isVisited[i] = false;
			}
		}
	}

	public static int play() {
		int cntKill = 0;
		while (enemies.size() != 0) {
			HashSet<Node> target = new HashSet<Node>();

			// 가장 가까운 적 찾기
			for (int archer : tempArcher) {
				int minDistance = Integer.MAX_VALUE;
				Node targetEnemy = null;
				for (Node enemy : enemies) {
					int tempDistance = calcDistance(enemy, R, archer);
					if (tempDistance <= D && tempDistance < minDistance) {
						minDistance = tempDistance;
						targetEnemy = enemy;
					}
				}
				if (targetEnemy != null)
					target.add(targetEnemy);
			}

			// 죽이기
			cntKill += target.size();
			for (Node targetEnemy : target) {
				enemies.remove(targetEnemy);
			}

			// 적 이동
			for (int i = 0; i < enemies.size(); i++) {
				int nextX = enemies.get(i).x + 1;
				int nextY = enemies.get(i).y;
				if (nextX == R) {
					enemies.remove(i);
					i--;
				} else {
					enemies.set(i, new Node(nextX, nextY));
				}
			}

		}

		return cntKill;
	}

	public static int calcDistance(Node node, int x, int y) {
		return Math.abs(node.x - x) + Math.abs(node.y - y);
	}
}

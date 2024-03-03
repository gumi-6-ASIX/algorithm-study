package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15683 {

	static int R, C, minCnt, cctv ,recur;
	static int[][] room;
	static boolean[][] coveredArea;
	static int[] rotate = { 0, 4, 2, 4, 4, 1 };
	static ArrayList<Node> cctvList = new ArrayList<Node>();
	static ArrayList<Node> cctvCaseList = new ArrayList<Node>();

	static class Node {
		int x;
		int y;
		int direction;

		Node(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		coveredArea = new boolean[R][C];
		minCnt = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == 6) { // 벽인 경우
					coveredArea[i][j] = true;
				}
				if (room[i][j] != 0 && room[i][j] != 6) {
					cctv++;
					cctvList.add(new Node(i, j, 0));
				}
			}
		}

		findArea(0);
		System.out.println(recur);
		System.out.println(minCnt);

	}

	public static void findArea(int cntCCTV) {
		recur++;
		if (cntCCTV == cctv) { // 모든 cctv 다 돌았음 -> 사각지대 계산
			minCnt = Math.min(minCnt, calc());
			return;
		}

		Node currentCCTV = cctvList.get(cntCCTV);
		int cctv = room[currentCCTV.x][currentCCTV.y];
		for (int j = 1; j <= rotate[cctv]; j++) {
			currentCCTV.direction = j;
			cctvCaseList.add(currentCCTV);
			coveredArea[currentCCTV.x][currentCCTV.y] = true;
			findArea(cntCCTV + 1);
			cctvCaseList.remove(currentCCTV);
			coveredArea[currentCCTV.x][currentCCTV.y] = false;
		}
	}

	public static void checkCase(int x, int y, int direction) {
		switch (room[x][y]) {
		case 1: {
			cover(x, y, direction);
			break;
		}
		case 2: {
			if (direction == 1) {
				cover(x, y, 1);
				cover(x, y, 3);
			}
			if (direction == 2) {
				cover(x, y, 2);
				cover(x, y, 4);
			}
			break;
		}
		case 3: {
			if (direction == 1) {
				cover(x, y, 1);
				cover(x, y, 2);
			}
			if (direction == 2) {
				cover(x, y, 2);
				cover(x, y, 3);
			}

			if (direction == 3) {
				cover(x, y, 3);
				cover(x, y, 4);
			}
			if (direction == 4) {
				cover(x, y, 1);
				cover(x, y, 4);
			}
			break;
		}
		case 4: {
			if (direction == 1) {
				cover(x, y, 1);
				cover(x, y, 2);
				cover(x, y, 3);
			}
			if (direction == 2) {
				cover(x, y, 2);
				cover(x, y, 3);
				cover(x, y, 4);
			}

			if (direction == 3) {
				cover(x, y, 1);
				cover(x, y, 3);
				cover(x, y, 4);
			}
			if (direction == 4) {
				cover(x, y, 1);
				cover(x, y, 2);
				cover(x, y, 4);
			}
			break;
		}
		case 5: {
			cover(x, y, 1);
			cover(x, y, 2);
			cover(x, y, 3);
			cover(x, y, 4);
			break;
		}
		}

	}

	public static void cover(int x, int y, int direction) {
		switch (direction) {
		case 1: { // 오른쪽
			for (int i = y + 1; i < C; i++) {
				if (room[x][i] == 6) {
					break;
				}
				coveredArea[x][i] = true;
			}
			break;
		}
		case 2: { // 위
			for (int i = x - 1; i >= 0; i--) {
				if (room[i][y] == 6) {
					break;
				}
				coveredArea[i][y] = true;
			}
			break;
		}
		case 3: { // 왼
			for (int i = y - 1; i >= 0; i--) {
				if (room[x][i] == 6) {
					break;
				}
				coveredArea[x][i] = true;
			}
			break;
		}
		case 4: { // 아래
			for (int i = x + 1; i < R; i++) {
				if (room[i][y] == 6) {
					break;
				}
				coveredArea[i][y] = true;
			}
			break;
		}
		}
	}

	// 벽은 사각지대에 포함x
	public static int calc() {
		boolean[][] copyArea = new boolean[R][C];
		int idx = 0;
		for (boolean[] array : coveredArea) {
			copyArea[idx] = array.clone();
			idx++;
		}

		for (Node node : cctvCaseList) {
			checkCase(node.x, node.y, node.direction);
		}

		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (coveredArea[i][j] == false) {
					cnt++;
				}
			}
		}

		// 다시 되돌려놓기
		idx = 0;
		for (boolean[] array : copyArea) {
			coveredArea[idx] = array.clone();
			idx++;
		}
		return cnt;
	}
}
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18809 {

	static int N, M, G, R, result;
	static int[][] map;
	static Queue<Node> queue = new LinkedList<Node>();
	static ArrayList<Node> yellow = new ArrayList<Node>();
	static ArrayList<Node> tempSelected = new ArrayList<Node>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Node {
		int x;
		int y;
		int depth;
		int color;

		public Node(int x, int y, int depth, int color) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					yellow.add(new Node(i, j, 0, 0));
				}
			}
		}

		findGR(0, 0);
		System.out.println(result);
	}

	public static int bfs() {
		// 배열복사
		int[][] cpyMap = new int[N][M];
		int idx = 0;
		for (int[] arr : map) {
			cpyMap[idx] = arr.clone();
			idx++;
		}

		for (Node gr : tempSelected) {
			if (gr.color == 10) {// 초록
				cpyMap[gr.x][gr.y] = 10;
			} else {// 빨강
				gr.color = -10;
				cpyMap[gr.x][gr.y] = -10;
			}
			queue.add(gr);
		}

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			if (cpyMap[curNode.x][curNode.y] == -100)
				continue;
			for (int i = 0; i < 4; i++) {
				int newX = curNode.x + dx[i];
				int newY = curNode.y + dy[i];
				if (isValid(newX, newY) && cpyMap[newX][newY] != 0 && cpyMap[newX][newY] != -100) {
					if (cpyMap[newX][newY] == -10) {
						if (cpyMap[curNode.x][curNode.y] == 10) {
							cpyMap[newX][newY] = -100;
						}
					} else if (cpyMap[newX][newY] == 10) {
						if (cpyMap[curNode.x][curNode.y] == -10) {
							cpyMap[newX][newY] = -100;
						}
					} else {// 빈 곳
						cpyMap[newX][newY] = cpyMap[curNode.x][curNode.y];
						queue.add(new Node(newX, newY, curNode.depth + 1, cpyMap[curNode.x][curNode.y]));
					}
				}
			}
		}

		int cnt = 0;
		for (int[] arr : cpyMap) {
			for (int a : arr) {
				if (a == -100) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void findG(int cnt, int idx) {
		if (cnt == G) {
			// BFS돌리기
			int flower = bfs();
			result = Math.max(result, flower);
			return;
		}

		for (int i = idx; i < tempSelected.size(); i++) {
			tempSelected.get(i).color = 10;
			findG(cnt + 1, idx + 1);
			tempSelected.get(i).color = 0;
		}
	}

	public static void findGR(int cnt, int idx) {
		if (cnt == G + R) {
			// 여기서 G,R 골라줌
			findG(0, 0);
			return;
		}

		for (int i = idx; i < yellow.size(); i++) {
			tempSelected.add(yellow.get(i));
			findGR(cnt + 1, idx + 1);
			tempSelected.remove(tempSelected.size() - 1);
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}

}
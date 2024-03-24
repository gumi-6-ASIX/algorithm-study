import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {
	static int n;
	static int m;
	static int[][] graph;
	static int[][] temp_graph;
	static ArrayList<int[]> sea;
	static Queue<int[]> queue;
	static int dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		temp_graph = new int[n][m];
		queue = new LinkedList<int[]>();
		sea = new ArrayList<int[]>();

		int answer = 0;

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				// L: 육지, W: 바다
				if (str.charAt(j) == 'L') {
					graph[i][j] = 0;
					temp_graph[i][j] = 0;
				} else {
					graph[i][j] = -1;
					temp_graph[i][j] = -1;
					sea.add(new int[] { i, j });
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 0) {
					queue.offer(new int[] { i, j });
					bfs(i, j);
					dist = 0;
					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							dist = Math.max(dist, temp_graph[k][l]);
						}
					}
					answer = Math.max(answer, dist);
				}
			}
		}

		System.out.println(answer-1);
	}

	public static void bfs(int i, int j) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		
		temp_graph = new int[n][m];
		temp_graph[i][j] = 1;

		for (int[] s : sea) {
			temp_graph[s[0]][s[1]] = -1;
		}

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (temp_graph[nx][ny] == 0) {
						temp_graph[nx][ny] = temp_graph[x][y] + 1;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}

	}
}

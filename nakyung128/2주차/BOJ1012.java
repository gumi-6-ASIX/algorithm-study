import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {
	static int[][] graph;
	static boolean[][] visited;
	static int m;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		// 배추밭 입력받기
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 배추밭의 가로길이
			n = Integer.parseInt(st.nextToken()); // 배추밭의 세로길이
			int k = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치의 개수

			graph = new int[m][n];
			visited = new boolean[m][n];

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				graph[x][y] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (dfs(i, j) == true) cnt++;
				}
			}
			System.out.println(cnt);
		}
	}

	public static boolean dfs(int x, int y) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		
		if (x < 0 || x >= m || y < 0 || y >= n) {
			return false;
		} else {
			if (!visited[x][y] && graph[x][y] == 1) {
				visited[x][y] = true;				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					dfs(nx, ny);
				}
				return true;
			}
		}
		return false;
	}
}

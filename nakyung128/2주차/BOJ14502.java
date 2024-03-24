import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
	static int n;
	static int m;
	static int[][] virus;
	static int[][] temp_virus;
	static ArrayList<int[]> where_virus;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		virus = new int[n][m];
		temp_virus = new int[n][m];
		visited = new boolean[n][m];
		queue = new LinkedList<int[]>();
		where_virus = new ArrayList<int[]>();

		// 초기 
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				virus[i][j] = Integer.parseInt(st.nextToken());
				if (virus[i][j] == 2) {
					queue.offer(new int[] { i, j });
					where_virus.add(new int[] {i, j});
				}
			}
		}
		
		back(0);		
		System.out.println(answer);
	}

	// 벽을 세운 뒤 
	public static void bfs() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		
		
		// 큐 초기화 후 바이러스 위치 다시 넣어주기
		queue = new LinkedList<int[]>();
		
		for (int i = 0; i < n; i++) {
			temp_virus[i] = virus[i].clone();
		}
		
		for (int[] where : where_virus) {
			queue.offer(where);
		}
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (temp_virus[nx][ny] == 0) {
						temp_virus[nx][ny] = 2; // 바이러스 퍼짐 
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}

	public static void back(int depth) {
		if (depth == 3) {
			bfs();
			
			int safe = 0;
			
			// 안전 영역의 최대 크기 구하기 
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (temp_virus[i][j] == 0) {
						safe++;
					}
				}
			}
			
			if (answer < safe) answer = safe;			
			return;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (virus[i][j] == 0 && !visited[i][j]) {
					virus[i][j] = 1;
					visited[i][j] = true;
					back(depth + 1);
					visited[i][j] = false;
					virus[i][j] = 0;
				}
			}
		}
	}
}

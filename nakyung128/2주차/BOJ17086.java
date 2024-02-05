import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086 {
	static int n;
	static int m;
	static int[][] shark;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		shark = new int[n][m];
		queue = new LinkedList<int[]>();
		
		// 배열 입력받기 
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				shark[i][j] = Integer.parseInt(st.nextToken());
				
				// 상어면 넣기 
				if (shark[i][j] == 1) {
					queue.offer(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		int result = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result = Math.max(shark[i][j], result);
			}
		}
		
		System.out.println(result-1);
	}
	
	public static void bfs() {
		int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
		
		while (!queue.isEmpty()) {
			
			// 현재 위치 
			int[] where = queue.poll();
			
			// 8방향 탐색 
			for (int i = 0; i < 8; i++) {				
				int nx = where[0] + dx[i];
				int ny = where[1] + dy[i];
				
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (shark[nx][ny] == 0) {
						shark[nx][ny] = shark[where[0]][where[1]] + 1;
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
	static int[][] tomatos;
	static Queue<int[]> queue;
	static int m, n;
	static int days = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		m = Integer.parseInt(st.nextToken()); // 세로 칸 수 
		n = Integer.parseInt(st.nextToken()); // 가로 칸 수 
		
		tomatos = new int[n][m];
		queue = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				tomatos[i][j] = Integer.parseInt(st.nextToken());
				if (tomatos[i][j] == 1) {
					queue.offer(new int[] {i, j});	
				}
			}
		}
		
		bfs();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tomatos[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
				days = Math.max(tomatos[i][j], days);
			}
		}
		
		// 익은 토마토가 1이라 날짜 계산이 1부터 시작하므로 출력할 때는 -1해서 출력 
		System.out.println(days-1);
	}
	
	public static void bfs() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		
		while (!queue.isEmpty()) {
			// 현재 위치 
			int[] t = queue.poll();
			
			// 상하좌우 탐색 
			for (int i = 0; i < 4; i++) {
				int nx = t[0] + dx[i];
				int ny = t[1] + dy[i];
				
				// 상자를 벗어나지 않고 아직 익지 않았다면 
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (tomatos[nx][ny] == 0) {
						tomatos[nx][ny] = tomatos[t[0]][t[1]] + 1; // 현재 일수에 + 1
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}
		
	}

}

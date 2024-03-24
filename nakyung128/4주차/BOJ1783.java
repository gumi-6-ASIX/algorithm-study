import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1783 {
	static int x;
	static int y;
	static int n;
	static int m;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로

		x = n - 1;
		y = 0;
		cnt = 1;
		
		if (n <= 2) {
			// 2, 3번만 사용 가능
			while (true) {
				// 2번
				if (move(1) == false) {
					break;
				}
				// 3번
				if (move(2) == false) {
					break;
				}
			}
			System.out.println(cnt);
		} else {
			// 1, 4만 사용
			if (m <= 6) {
				while (true) {
					// 1번
					if (move(0) == false) {
						break;
					}
					// 4번
					if (move(3) == false) {
						break;
					}
				}
				System.out.println(cnt);
			} else {
				// 1, 2, 3, 4 한 번씩 사용하기			
				// 2번, 3번 먼저 한 번씩 사용
				
				move2(1);
				move2(2);
				
				while (true) {
					// 1번
					if (move2(0) == false) {
						break;
					}
					// 4번
					if (move2(3) == false) {
						break;
					}
				}
				System.out.println(cnt);
			}
			
		}
	}
	
	public static boolean move(int idx) {
		int[] dx = { -2, -1, 1, 2 };
		int[] dy = { 1, 2, 2, 1 };
		
		int nx = x + dx[idx];
		int ny = y + dy[idx];
		
		if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
			x = x + dx[idx];
			y = y + dy[idx];
			cnt += 1;
			
			if (cnt == 4) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public static boolean move2(int idx) {
		int[] dx = { -2, -1, 1, 2 };
		int[] dy = { 1, 2, 2, 1 };
		
		int nx = x + dx[idx];
		int ny = y + dy[idx];
		
		if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
			x = x + dx[idx];
			y = y + dy[idx];
			cnt += 1;
		} else {
			return false;
		}
		
		return true;
	}
}

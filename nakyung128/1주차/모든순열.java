import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모든순열 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] per = new int[n];
		boolean[] used = new boolean[n];
		
		back(per, used, n, 0);
	}
	
	// 백트래킹
	public static void back(int[] per, boolean[] used, int n, int depth) {
		// depth가 n이면 출력 후 return
		if (depth == n) {
			for (int p : per) {
				System.out.print(p + " ");
			}
			System.out.println();
			return;
		}
		
		// 1부터 n까지 반복
		for (int i = 1; i <= n; i++) {
			// 사용하지 않은 숫자면
			if (!used[i-1]) {
				per[depth] = i; // 값 넣어주기
				used[i-1] = true;
				back(per, used, n, depth+1); // 재귀
				used[i-1] = false;
			}
		}
	}
}

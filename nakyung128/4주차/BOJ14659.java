import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] bongs = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			bongs[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		
		for (int i = 0; i < n; i++) {
			int can = 0;
			for (int j = i+1; j < n; j++) {
				int now = bongs[i];
				if (now > bongs[j]) {
					can += 1;
				} else {
					break;
				}
			}
			result = Math.max(result, can);
		}
		
		System.out.println(result);
	}

}

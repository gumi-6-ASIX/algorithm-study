import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자야구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		String[] numbers = new String[n];
		int[] strikes = new int[n];
		int[] balls = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			numbers[i] = st.nextToken();
			strikes[i] = Integer.parseInt(st.nextToken());
			balls[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 123; i < 988; i++) {
			String str_number = Integer.toString(i);
			
			boolean right = false;
			int s = 0;
			int b = 0;
			
			// 숫자 내에 중복되는 수가 있는 경우 제외 
			if (str_number.charAt(0) == str_number.charAt(1) || str_number.charAt(1) == str_number.charAt(2) || str_number.charAt(0) == str_number.charAt(2)) {
				continue;
			} else if (str_number.charAt(0) == '0' || str_number.charAt(1) == '0' || str_number.charAt(2) == '0') continue; // 숫자 내에 0이 있는 경우 제

			
			for (int j = 0; j < n; j++) {
				String now_num = numbers[j]; // 질문한 숫자 
				
				for (int k = 0; k < 3; k++) {
					char num = now_num.charAt(k);
			
					for (int l = 0; l < 3; l++) {
						char ans = str_number.charAt(l);
						
						// strike
						if (num == ans && k == l) {
							s++;
						} else if (num == ans && k != l) { // ball
							b++;
						}
					}
				}
				
				// strike, ball의 개수가 같은 경우 right = true
				if (strikes[j] == s && balls[j] == b) {
					right = true;
				} else {
					right = false;
					break;
				}
				s = 0;
				b = 0;
			}
			
			// right == true이면 영수가 말한 모든 숫자와 strike, ball의 개수가 같은 것이므로
			// answer += 1 해 준다
			if (right) {
				answer++;
			}
		}
		
		System.out.println(answer);

	}
}

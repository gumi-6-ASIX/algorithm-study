import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 암호만들기 {
	static ArrayList<String> arr;
	static ArrayList<String> answer;
	static int[] used;
	static int L;
	static int C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new ArrayList<String>();
		answer = new ArrayList<String>();
		used = new int[C];
		
		for (int i = 0; i < C; i++) {
			String c = st.nextToken();
			arr.add(c);
		}
		
		arr.sort(null); // 오름차순 정렬 
		makeKey(arr, 0, 0, 0, 0);
	}
	
	public static void makeKey(ArrayList<String> arr, int vowel_cnt, int con_cnt, int depth, int me) {		
		// 길이가 L이 되면 출력 
		if (depth == L) {
			if (vowel_cnt >= 1 && con_cnt >= 2) {
				String str = "";
				for (int i = 0; i < answer.size(); i++) {
					str += answer.get(i);
				}
				System.out.println(str);
				return;
			} else {
				return;
			}
		}
		
		for (int i = me; i < arr.size(); i++) {
			if (used[i] == 0) {
				String c = arr.get(i);
				answer.add(c);
				used[i] = 1;
				if (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u")) {
					makeKey(arr, vowel_cnt+1, con_cnt, depth+1, i);
				} else {
					makeKey(arr, vowel_cnt, con_cnt+1, depth+1, i);
				}
				used[i] = 0;
				answer.remove(answer.size()-1);
			} 
		}
	}
}

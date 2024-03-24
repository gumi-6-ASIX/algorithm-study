import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1339 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<char[]> words = new ArrayList<char[]>();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		ArrayList<Integer> nums = new ArrayList<Integer>(); 
		
		int n = Integer.parseInt(st.nextToken());
		
		// 입력
		for (int i = 0; i < n; i++) {
			String wd = br.readLine();
			int len = wd.length();
			char[] ch = new char[len];
			for (int j = 0; j < len; j++) {
				ch[j] = wd.charAt(j);
			}
			words.add(ch);
		}
		
		// 자릿수 계산
		for (char[] ch : words) {
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				if (!map.containsKey(c)) {
					int number = (int) Math.pow(10, (ch.length - i - 1));
					map.put(c, number);
				} else {
					int number = (int) Math.pow(10, (ch.length - i - 1));
					map.replace(c, map.get(c) + number);
				}
			}
		}
		
		// 맵 value 기준으로 내림차순
		for (int v : map.values()) {
			nums.add(v);
		}
		Collections.sort(nums, Collections.reverseOrder());
		
		int result = 0;
		int start = 9;
		
		for (int i = 0; i < nums.size(); i++) {
			result += nums.get(i) * start;
			start--;
		}
		
		System.out.println(result);
	}
}

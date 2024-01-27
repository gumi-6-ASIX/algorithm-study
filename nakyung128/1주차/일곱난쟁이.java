import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 일곱난쟁이 {
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<Integer> heights = new ArrayList<>();
		int total = 0;
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			heights.add(height);
			total += height;
		}
		
		int first = 0;
		int second = 0;
		
		for (int i = 0; i < heights.size()-1; i++) {
			for (int j = i+1; j < heights.size(); j++) {
				if (total - (heights.get(i) + heights.get(j)) == 100) {
					first = heights.get(i);
					second = heights.get(j);
					break;
				}
			}
		}
		
		Collections.sort(heights);
		for (int height : heights) {
			if (height != first && height != second) {
				System.out.println(height);
			}
		}
	}
}

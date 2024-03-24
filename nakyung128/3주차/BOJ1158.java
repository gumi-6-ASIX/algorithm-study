import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> answer = new ArrayList<Integer>();
		String result = "<";

		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k - 1; j++) {
				queue.offer(queue.poll());
			}
			answer.add(queue.poll());
		}

		result += answer.toString().substring(1, answer.toString().length() - 1);
		result += ">";

		System.out.println(result);
	}
}

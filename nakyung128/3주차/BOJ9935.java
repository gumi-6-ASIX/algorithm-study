import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();

		String str = br.readLine();
		String bomb = br.readLine();
		int bombLen = bomb.length();

		for (char s : str.toCharArray()) {
			stack.push(s);

			if (stack.size() >= bombLen) {
				boolean isBomb = true;

				for (int i = 0; i < bombLen; i++) {
					if (stack.get(stack.size() - bombLen + i) != bomb.charAt(i)) {
						isBomb = false;
						break;
					}
				}

				if (isBomb) {
					for (int j = 0; j < bombLen; j++) {
						stack.pop();
					}
				}
			}
		}

		for (char s : stack) {
			sb.append(s);
		}

		if (sb.length() == 0) {
			bw.append("FRULA");
			bw.close();
		} else {
			bw.append(sb);
			bw.close();
		}
	}

}

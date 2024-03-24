import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16120_PPAP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        // 받은 문자열
        String str = br.readLine();

        String ppap = "PPAP";

        // 맨 앞이 A이면 무조건 NP
        if (str.charAt(0) == 'A') {
            System.out.println("NP");
        } else {
            for (char s : str.toCharArray()) {
                stack.push(s);

                // 스택의 크기가 4 (PPAP) 이상이면
                if (stack.size() >= 4) {
                    boolean isPPAP = true;

                    // PPAP인지 확인
                    for (int i = 0; i < 4; i++) {
                        if (stack.get(stack.size() - 4 + i) != ppap.charAt(i)) {
                            isPPAP = false;
                        }
                    }

                    // 맞으면 pop 3번
                    if (isPPAP) {
                        for (int i = 0; i < 3; i++) {
                            stack.pop();
                        }
                    }
                }
            }

            for (char s : stack) {
                sb.append(s);
            }

            // 마지막에 PPAP면 pop 3번 될 테니까 P만 남게 되면 PPAP 문자열임
            if (sb.toString().equals("P"))
                System.out.println("PPAP");
            else
                System.out.println("NP");
        }
    }
}

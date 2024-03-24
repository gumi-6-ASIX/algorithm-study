import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Stack<Character> stack = new Stack<>();
    String arr = br.readLine();
    String ppap = "PPAP";
    for (int i = 0; i < arr.length(); i++) {
      stack.push(arr.charAt(i));
      if (stack.peek() == ppap.charAt(3) && stack.size() > 3) {
        int flag = 0;
        for (int j = 2; j < 5; j++) {
          if (stack.get(stack.size() - j) == ppap.charAt(4 - j)) {
            continue;
          } else {
            flag = 1;
            break;
          }
        }
        if (flag == 0) {
          for (int j = 0; j < 3; j++) {
            stack.pop();
          }
        }
      }
    }
    if (stack.size() == 1 && stack.peek() == 'P') {
      System.out.println("PPAP");
    } else {
      System.out.println("NP");
    }
  }
}

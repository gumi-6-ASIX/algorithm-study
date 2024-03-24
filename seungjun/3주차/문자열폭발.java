import java.io.*;
import java.util.Stack;

public class 문자열폭발 {
    public static void main(String[] args) throws IOException {
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String arr = br.readLine();
        String puk = br.readLine();
        for(int i=0;i<arr.length();i++){
            stack.push(arr.charAt(i));
            if(stack.peek()==puk.charAt(puk.length()-1) && stack.size()>=puk.length()){
                int cnt=0;
                for(int j=1;j<=puk.length();j++){
                    if(stack.get(stack.size()-j)==puk.charAt(puk.length()-j)){
                        cnt++;
                    }
                }
                if(cnt==puk.length()){
                    for(int j=1;j<=puk.length();j++){
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : stack) {
            sb.append(ch);
        }

        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");

    }
}

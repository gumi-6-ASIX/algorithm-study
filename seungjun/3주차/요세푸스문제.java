import java.io.*;
import java.util.*;

public class 요세푸스문제 {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

//        int n = sc.nextInt();
//        int k= sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();


        for(int i=1;i<=n;i++){
            queue.offer(i);
        }
        bw.write("<");
//        System.out.print("<");
        while(queue.size()!=1){
            for(int i=0;i<k-1;i++){
                int a=queue.poll();
                queue.offer(a);
            }
            bw.write(queue.poll() + ", ");
//            System.out.print(queue.poll()+", ");


        }
        bw.write(queue.poll() + ">");
        bw.close();
//        System.out.print(queue.poll()+">");
    }


}
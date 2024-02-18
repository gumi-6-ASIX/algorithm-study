import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Lab {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int n = Integer.parseInt(st.nextToken());
        int [][] num = new int[n][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            num[i][0] = Integer.parseInt(st.nextToken());
            num[i][1]= Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num,(s1,s2) ->{
            int result = s1[0]-s2[0];
            return (result==0) ? s1[1]-s2[1] : result;


        });
        queue.offer(num[0][1]);
        for(int i=1;i<n;i++){
            if(num[i][0]>=queue.peek()){
                queue.poll();
            }
            queue.offer(num[i][1]);

        }


        System.out.println(queue.size());
    }
}

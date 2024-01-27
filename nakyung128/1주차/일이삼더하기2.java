import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 일이삼더하기2 {
    static ArrayList<ArrayList<Integer>> com;
    static ArrayList<Integer> c;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        com = new ArrayList<ArrayList<Integer>>();
        c = new ArrayList<Integer>();
        
        back(n, 0);    
        
        if (com.size() < k) {
            System.out.println(-1);
        } else {
            int size = com.get(k-1).size();
            c = com.get(k-1);
            
            for (int i = 0; i < size-1; i++) {
                System.out.print(c.get(i) + "+");
            }
            System.out.print(c.get(size-1));
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public static void back(int n, int hap) {
        
        if (hap == n) {        
            com.add((ArrayList<Integer>)c.clone());
            return;
        }
        if (hap > n) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            c.add(i);
            back(n, hap+i);
            c.remove(c.size()-1);
        }
    }
}
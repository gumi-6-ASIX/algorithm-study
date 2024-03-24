import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  static int n, d, k, c;
  static int[] num;
  static ArrayList<Integer> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int maax = Integer.MIN_VALUE;
    n = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    for (int i = 0; i < n; i++) {
      list.add(Integer.parseInt(br.readLine()));
    }
    for (int i = 0; i < n; i++) {
      HashSet<Integer> set = new HashSet<>();
      for (int j = i; j < i + k; j++) {
        if (j < n) {
          set.add(list.get(j));
        } else {
          set.add(list.get(j - n));
        }
      }
      set.add(c);
      maax = Math.max(maax, set.size());
    }
    System.out.println(maax);
  }
}

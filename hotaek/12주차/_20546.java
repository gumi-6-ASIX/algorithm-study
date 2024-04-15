package 백준.java.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class _20546 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int bnp = n;
        int bnpValue = 0;
        int tim = n;
        int timValue = 0;
        int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int disCount = 0;
        int plusCount = 0;
        for (int i = 0; i < 14; i++) {
            int a = array[i];
            // 준현
            if (bnp >= a) {
                bnpValue += bnp / a;
                bnp -= a * (bnp / a);
            }

            if (i >= 1) {
                int before = array[i - 1];
                if (a > before) {
                    plusCount++;
                    disCount = 0;
                } else if (a < before) {
                    disCount++;
                    plusCount = 0;
                } else {
                    plusCount = 0;
                    disCount = 0;
                }

            }
            if (plusCount >= 3) {
                if (timValue > 0) {
                    tim += timValue * a;
                    timValue = 0;
                }
            }
            if (disCount >= 3) {
                if (tim >= a) {
                    timValue += tim / a;
                    tim -= a * (tim / a);
                }
            }

            if (i == 13) {
                int bnpResult = bnp + bnpValue * a;
                int timResult = tim + timValue * a;
                if (bnpResult > timResult) {
                    System.out.println("BNP");
                } else if (bnpResult == timResult) {
                    System.out.println("SAMESAME");
                } else {
                    System.out.println("TIMING");
                }
            }
        }

    }
}

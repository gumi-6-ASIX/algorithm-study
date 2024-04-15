import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20546_기적의매매법 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cash = Integer.parseInt(st.nextToken());
        int[] jusik = new int[14];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 14; i++) {
            jusik[i] = Integer.parseInt(st.nextToken());
        }

        int jun_cash = cash; // 준현이 돈
        int seong_cash = cash; // 성민이 돈
        int jun = 0; // 준현이의 주식 수
        int seong = 0; // 성민이의 주식 수

        // 준현이 주식 매매
        for (int juga : jusik) {
            if (juga <= jun_cash) {
                jun += jun_cash / juga;
                jun_cash -= jun * juga;
            }

            // 0원 되면 어차피 못 사니까 끝
            if (jun_cash == 0)
                break;
        }

        // 성민이 주식 매매
        for (int i = 0; i < 11; i++) {
            // 3일 연속 하락하면 매수
            if (jusik[i + 2] < jusik[i + 1] && jusik[i + 1] < jusik[i]) {
                if (seong_cash >= jusik[i + 3]) {
                    seong += seong_cash / jusik[i + 3];
                    seong_cash -= seong_cash / jusik[i + 3] * jusik[i + 3];
                }
            }

            // 3일 연속 상승하면 매도
            if (jusik[i + 2] > jusik[i + 1] && jusik[i + 1] > jusik[i]) {
                seong_cash += seong * jusik[i + 3];
                seong = 0;
            }
        }

        if (jun_cash + jun * jusik[13] > seong_cash + seong * jusik[13]) {
            System.out.println("BNP");
        } else if (jun_cash + jun * jusik[13] < seong_cash + seong * jusik[13]) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}

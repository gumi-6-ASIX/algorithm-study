import java.util.*;
import java.io.*;

public class 기적의_매매법 {
    static int[] days;
    static int m;

    public static int samsam() {
        int money = m;
        int stocks = 0;
        // 모든 거래는 전량매도, 전량매수
        // 3일연속 가격 상승 -> 전량 매도
        // 3일연속 가격 하락 -> 전량 매수 ->가격이 동일하다면 하락한게 아님
        // 10 20 23 -> 3일연속 가격상승 -> 전량매도 해야된다.
        // 10, 20, 23, 30이라면 30이 되는날 전량 매도한다.
        int buy = 1;
        int sell = 1;
        for (int i = 1; i < 14; i++) {
            int dailyPrice = days[i];
            if (buy == 3) {
                sell = 1;
                buy = 1;
                while (money >= dailyPrice) {
                    money -= dailyPrice;
                    stocks++;
                }
            }
            if (sell == 3) {
                sell = 1;
                buy = 1;
                while (stocks > 0) {
                    money += dailyPrice;
                    stocks--;
                }
            }
            if (days[i] < days[i - 1]) {
                // 매수카운트 3일 하락시 전량 매수
                buy++;
            } else {
                buy = 1;
            }
            if (days[i] > days[i - 1]) {
                // 매도카운트 3일 상승 시 전량 매도
                sell++;
            } else {
                sell = 1;
            }
        }

        return total(money, stocks);
    }

    public static int bnp() {
        int money = m;
        // 한번 사면 팔지않는다.
        int stocks = 0;
        for (int i = 0; i < 14; i++) {
            int dailyPrice = days[i];
            // money가 허락하는 최대한 그날그날 사버림
            while (money >= dailyPrice) {
                money -= dailyPrice;
                stocks++;
            }
            if (money == 0)
                break;
        }

        return total(money, stocks);
    }

    public static int total(int money, int stocks) {
        return days[13] * stocks + money;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        StringTokenizer st = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(br.readLine());// 준현이, 성민이가 각각 쓸 돈
        days = new int[14];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 14; i++) {
            days[i] = Integer.parseInt(st.nextToken());
        } // 14일간 1월1일 ~14일까지의 주식가격
        int junhyun = bnp();
        int sungmin = samsam();
        if (junhyun == sungmin)
            System.out.println("SAMESAME");
        else if (junhyun > sungmin)
            System.out.println("BNP");
        else if (junhyun < sungmin)
            System.out.println("TIMING");
    }
}

import java.util.Scanner;

public class 만들기1로 {
    static int n;
    static int miin;
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n + 1];
        miin = Integer.MAX_VALUE;
        num[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            if (i % 2 == 0) {
                num[i] = Math.min(num[i / 2] + 1, num[i - 1] + 1);
                if(i%3==0){
                    num[i] = Math.min(num[i], num[i/3] + 1);
                }

            }
            if (i % 3 == 0) {
                num[i] = Math.min(num[i / 3] + 1, num[i - 1] + 1);
                if(i%2==0){
                    num[i] = Math.min(num[i], num[i/2] + 1);
                }

            }
            if (i % 2 != 0 && i % 3 != 0) {
                num[i] = num[i - 1]+1 ;
            }

        }
        System.out.println(num[n]);
        sc.close();
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] blocks = new int[w];

        int high = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
            high = Math.max(high, blocks[i]);
        }

        int rain = 0;

        /**
         * 제일 높은 블록까지 높이 한 칸씩 본다고 생각하면 됨.
         * 높이 1 ~ high까지 반복
         * 현재 높이보다 작은 블록인데 양쪽에 현재 높이보다 크거나 같은 블록이 있으면
         * 그 블록 위에 쌓일 수 있음 (cnt + 1)
         */

        for (int i = 1; i <= high; i++) {
            int cnt = 0;
            for (int j = 1; j < w - 1; j++) {
                // 현재 높이보다 작은 블록이면 (양끝 제외)
                if (blocks[j] < i) {
                    boolean leftAble = false;
                    boolean rightAble = false;

                    // 왼쪽 현재 높이보다 같거나 높은 거 있으면 쌓임
                    for (int left = j - 1; left >= 0; left--) {
                        if (blocks[left] >= i) {
                            leftAble = true;
                            break;
                        }
                    }
                    // 오른쪽도
                    for (int right = j + 1; right < w; right++) {
                        if (blocks[right] >= i) {
                            rightAble = true;
                            break;
                        }
                    }

                    if (leftAble && rightAble) {
                        cnt++;
                    }
                }
            }
            rain += cnt;
        }
        System.out.println(rain);
    }
}

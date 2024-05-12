import java.util.*;

class Solution {
    static int[] sale = { 10, 20, 30, 40 }; // 할인율
    static ArrayList<Integer> perm; // 순열 리스트
    static int[] answer; // 정답 배열
    static int emo_size; // 이모티콘 개수

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        emo_size = emoticons.length;
        perm = new ArrayList<>();

        select(0, users, emoticons);

        return answer;
    }

    static void select(int depth, int[][] users, int[] emoticons) {
        if (depth == emo_size) {
            // 서비스 가입자, 판매액 계산
            cal(users, emoticons);
            return;
        }

        for (int i = 0; i < 4; i++) {
            perm.add(sale[i]);
            select(depth + 1, users, emoticons);
            perm.remove(perm.size() - 1);
        }
    }

    static void cal(int[][] users, int[] emoticons) {
        int join = 0; // 가입한 사람 수
        int money = 0; // 판매액

        for (int[] user : users) {
            int user_perc = user[0]; // 사용자 기준 할인율
            int user_money = user[1]; // 사용자 기준 가격
            int buy = 0; // 구매한 가격의 합
            boolean service = false;

            for (int i = 0; i < emo_size; i++) {
                int percent = perm.get(i); // 할인율
                int price = emoticons[i] / 100 * (100 - percent); // 세일한 가격

                // 사용자 기준 비율 이상 할인하면 구매
                if (percent >= user_perc) {
                    buy += price;

                    if (buy >= user_money) {
                        service = true; // 서비스 가입
                        break;
                    }
                }
            }

            // 만약 서비스에 가입했다면
            if (service) {
                join++;
            } else {
                money += buy;
            }
        }

        // 서비스 가입자 수가 더 많으면 갱신
        if (answer[0] < join) {
            answer[0] = join;
            answer[1] = money;
        } else if (answer[0] == join && answer[1] < money) { // 가입자 수 같은데 판매액 더 높으면 갱신
            answer[1] = money;
        }
    }
}
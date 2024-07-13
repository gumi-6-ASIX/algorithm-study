package 프로그래머스.java.Lv2;

import java.util.*;

public class _택배배달과수거하기 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int nowIndex = n - 1;
        long answer = 0;

        while (nowIndex != -1) {
            int[] truck = new int[3];
            truck[0] = cap;

            int plusIndex = 0;
            boolean flag = false;

            for (int i = nowIndex; i >= 0; i--) {
                // 현재, 배달 및 수거 개수
                int deliver = deliveries[i];
                int pickup = pickups[i];

                // 둘 다 0인 경우 넘어감
                if (deliver == 0 && pickup == 0) {
                    nowIndex = i - 1;
                    continue;
                }

                if (!flag) {
                    plusIndex = i;
                    flag = true;
                }

                // 택배 내려놓기
                if (deliver <= truck[0]) {
                    deliveries[i] = 0;
                    truck[0] -= deliver;
                    truck[1] += deliver;
                } else {
                    deliveries[i] -= truck[0];
                    truck[1] += truck[0];
                    truck[0] = 0;
                }
                // System.out.println("야");
                // System.out.println(Arrays.toString(deliveries));
                // System.out.println(Arrays.toString(pickups));
                // System.out.println(Arrays.toString(truck));
                // 택배 수거
                if (pickup <= truck[1]) {
                    pickups[i] = 0;
                    truck[1] -= pickup;
                    truck[2] += pickup;
                } else {
                    int truckPlus = truck[0] + truck[1];

                    if (pickup <= truckPlus) {
                        truck[0] = truckPlus - pickup;
                        truck[1] = 0;
                        truck[2] += pickup;
                        pickups[i] = 0;
                    } else {
                        truck[0] = 0;
                        truck[1] = 0;
                        truck[2] = cap;
                        pickups[i] = pickup - truckPlus;
                    }
                }
                // System.out.println("호");
                // System.out.println(Arrays.toString(deliveries));
                // System.out.println(Arrays.toString(pickups));
                // System.out.println(Arrays.toString(truck));
                // System.out.println();
                if (deliveries[i] == 0 && pickups[i] == 0) {
                    nowIndex = i - 1;
                }

                if (truck[0] == 0 && truck[2] == cap) {
                    break;
                }
            }
            // System.out.println("더하기" + (plusIndex+1));
            if (plusIndex != 0) {
                answer += (plusIndex + 1) * 2;
                System.out.println("더하기" + (plusIndex + 1));
            }

        }

        return answer;
    }

}

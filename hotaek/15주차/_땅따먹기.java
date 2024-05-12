package 프로그래머스.java.Lv2;

import java.util.*;

//colIndex -> 현재 행
//rowIndex -> 현재 열
// while 문을 통해 colIndex를 늘려줌!! 이때 rowIndex % 4 == 0 일 경우 colIndex를 늘려주는 식으로
// dp로 계속해서 현재 rowIndex를 제외한 나머지 중 최대값을 지속적으로 더해주면 끝

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        // length가 1개일 때
        if (land.length == 1) {
            for (int i = 0; i < 4; i++) {
                if (answer < land[0][i]) {
                    answer = land[0][i];
                }
            }
            return answer;
        }

        int colIndex = 1;
        int rowIndex = 0;
        int size = land.length;
        while (colIndex < size) {
            int max = 0;
            for (int i = 0; i < 4; i++) {
                if (i == rowIndex)
                    continue;

                if (max < land[colIndex - 1][i]) {
                    max = land[colIndex - 1][i];

                }
            }
            land[colIndex][rowIndex] += max;
            rowIndex++;
            if (rowIndex % 4 == 0) {
                rowIndex = 0;
                colIndex++;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (answer < land[size - 1][i]) {
                answer = land[size - 1][i];
            }
        }

        return answer;
    }
}

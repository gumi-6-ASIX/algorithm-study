package 백준.java.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class _7682_틱택토 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] game = br.readLine().split("");
            int count = game.length;
            if (count == 3) {
                break;
            }
            int x_count = 0;
            int y_count = 0;

            int[] tmp = new int[9];

            for (int i = 0; i < 9; i++) {
                if (game[i].equals("X")) {
                    x_count++;
                    tmp[i] = -1;
                } else if (game[i].equals("O")) {
                    y_count++;
                    tmp[i] = 1;
                } else {
                    tmp[i] = 0;
                }
            }

            int[] row = { tmp[0] + tmp[1] + tmp[2], tmp[3] + tmp[4] + tmp[5], tmp[6] + tmp[7] + tmp[8] };
            int[] col = { tmp[0] + tmp[3] + tmp[6], tmp[1] + tmp[4] + tmp[7], tmp[2] + tmp[5] + tmp[8] };
            int[] slice = { tmp[0] + tmp[4] + tmp[8], tmp[2] + tmp[4] + tmp[6] };

            boolean xCheck = xCheck(row, col, slice);
            boolean yCheck = yCheck(row, col, slice);

            // X, O 다 꽉찼을 경우
            if (x_count == 5 && y_count == 4) {
                if (yCheck) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
                continue;
            }

            // X,O 둘다 가능
            if (xCheck && yCheck) {
                System.out.println("invalid");
                continue;
            } else if (xCheck) {
                if (x_count == y_count) {
                    System.out.println("invalid");
                    continue;
                } else if (x_count == y_count + 1) {
                    System.out.println("valid");
                    continue;
                }
            } else if (yCheck) {
                if (x_count == y_count) {
                    System.out.println("valid");
                    continue;
                } else {
                    System.out.println("invalid");
                    continue;
                }
            }

            System.out.println("invalid");
        }
    }

    public static boolean xCheck(int[] row, int[] col, int[] slice) {
        if ((row[0] == -3 || row[1] == -3 || row[2] == -3)) {
            return true;
        } else if ((col[0] == -3 || col[1] == -3 || col[2] == -3)) {
            return true;
        } else if ((slice[0] == -3 || slice[1] == -3)) {
            return true;
        }

        return false;
    }

    public static boolean yCheck(int[] row, int[] col, int[] slice) {
        if ((row[0] == 3 || row[1] == 3 || row[2] == 3)) {
            return true;
        } else if ((col[0] == 3 || col[1] == 3 || col[2] == 3)) {
            return true;
        } else if ((slice[0] == 3 || slice[1] == 3)) {
            return true;
        }
        return false;
    }

}

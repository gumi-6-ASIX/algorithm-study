import java.io.*;
import java.util.*;

public class 틱택토 {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line.equals("end"))
                break;
            System.out.println(game(line));
        }
    }

    public static String game(String line) {
        int cntO = 0;
        int cntX = 0;
        char[][] board = new char[3][3];
        for (int i = 0; i < 9; i++) {
            char t = line.charAt(i);
            board[i / 3][i % 3] = t;
            if (t == 'O')
                cntO++;
            if (t == 'X')
                cntX++;
        }
        // X는 O보다 최대 1개 더 많아야 함
        if (cntX != cntO && cntX != cntO + 1)
            return "invalid";

        boolean winX = checkWin(board, 'X');
        boolean winO = checkWin(board, 'O');

        // 두 명이 동시에 이길 수 없음
        if (winX && winO)
            return "invalid";

        // O가 이기면 X의 개수와 O의 개수는 같아야 함
        if (winO && cntX != cntO)
            return "invalid";

        // X가 이기면 X의 개수는 O의 개수보다 1개 많아야 함
        if (winX && cntX != cntO + 1)
            return "invalid";

        // 게임이 끝나지 않았는데 보드가 꽉 차지 않은 경우
        if (!winX && !winO && cntX + cntO != 9)
            return "invalid";

        return "valid";
    }

    public static boolean checkWin(char[][] board, char p) {

        // 가로 체크
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
                return true;
        }

        // 세로 체크
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p)
                return true;
        }

        // 대각선 체크
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
            return true;
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p)
            return true;

        return false;
    }
}
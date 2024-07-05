import java.io.*;

public class BOJ7682_틱택토 {
    static char[][] board;
    static int oCnt, xCnt;
    static int xLine, oLine;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 반복해서 입력 받기
        while (true) {
            oCnt = 0;
            xCnt = 0;
            xLine = 0;
            oLine = 0;
            board = new char[3][3];

            String line = br.readLine();

            if (line.equals("end")) {
                break;
            }

            boolean haveDot = false;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int idx = 3 * i + j;
                    board[i][j] = line.charAt(idx);

                    if (board[i][j] == '.') {
                        haveDot = true;
                    } else if (board[i][j] == 'O') {
                        oCnt++;
                    } else {
                        xCnt++;
                    }
                }
            }

            isValid();

            // 1. 꽉 찬 판일 때 X가 O보다 한 개 많은 경우 가능
            // 2. 꽉찬 판 아닐 때
            // 2-1. O가 줄 만들었으면 -> X개수 == O개수
            // 2-2. X가 줄 만들었으면 -> X가 O보다 1개 많아야 함
            if (!haveDot) {
                if (xLine > 0 && oLine == 0 && xCnt - oCnt == 1) {
                    System.out.println("valid");
                } else if (xLine == 0 && oLine == 0 && xCnt - oCnt == 1) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            } else {
                if (xLine == 1 && oLine == 0 && xCnt - oCnt == 1) {
                    System.out.println("valid");
                } else if (oLine == 1 && xLine == 0 && xCnt == oCnt) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            }
        }
    }

    static void isValid() {
        // 가로세로 찾기
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'O') {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    oLine++;
                }
            }

            if (board[0][i] == 'O') {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    oLine++;
                }
            }

            if (board[i][0] == 'X') {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    xLine++;
                }
            }

            if (board[0][i] == 'X') {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    xLine++;
                }
            }
        }

        // 대각선 찾기
        if (board[0][0] == 'O') {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                oLine++;
            }
        }

        if (board[0][2] == 'O') {
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                oLine++;
            }
        }

        if (board[0][0] == 'X') {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                xLine++;
            }
        }

        if (board[0][2] == 'X') {
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                xLine++;
            }
        }
    }
}
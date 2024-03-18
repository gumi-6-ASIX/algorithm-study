import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2239_스도쿠 {
    static int[][] sudoku;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = line.charAt(j) - '0';
            }
        }

        fill(0, 0);
    }

    static void fill(int x, int y) {
        // 한 행이 다 채워짐
        if (y == 9) {
            fill(x + 1, 0); // 다음 행 시작
            return;
        }

        // 행, 열이 다 채워짐
        if (x == 9) {
            StringBuilder sb = new StringBuilder();
            for (int[] row : sudoku) {
                for (int col : row) {
                    sb.append(col);
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
        }

        // 비어 있는 곳이면
        if (sudoku[x][y] == 0) {
            // 1 ~ 9 다 넣어보기
            for (int i = 1; i < 10; i++) {
                // 가능한가? (행, 열, 9칸 확인)
                if (isValid(x, y, i)) {
                    sudoku[x][y] = i; // 가능하면 칸에 넣기
                    fill(x, y + 1); // 재귀 (오른쪽으로 한 칸 이동)
                }
            }
            // 1 ~ 10까지 해 봤는데 안 되면 그 전으로 돌아가야 함
            sudoku[x][y] = 0; // 되돌림
            return;
        } else {
            fill(x, y + 1); // 0이 아니면 옆으로
        }
    }

    static boolean isValid(int x, int y, int check) {
        // 행, 열 확인
        for (int k = 0; k < 9; k++) {
            if (sudoku[x][k] == check) {
                return false;
            }

            if (sudoku[k][y] == check) {
                return false;
            }
        }

        // 9칸 확인
        // row, col => 해당 칸 시작 지점
        int row = x / 3 * 3;
        int col = y / 3 * 3;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (sudoku[i][j] == check) {
                    return false;
                }
            }
        }

        return true;
    }
}
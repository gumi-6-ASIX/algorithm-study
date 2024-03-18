package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16931_겉넓이구하기 {

	static int N, M, result;
	static int[][] tower;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tower = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tower[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j == 0) {
					result += tower[i][j];
//					System.out.println("tower[i][j] : " + tower[i][j]);
				} else {
//					System.out.println("abs : " + Math.abs(tower[i][j - 1] - tower[i][j]));
					result += Math.abs(tower[i][j - 1] - tower[i][j]);
				}

				if (j == M - 1) {
					result += tower[i][j];
//					System.out.println("tower[i][j] : " + tower[i][j]);
				}
			}
		}
//		System.out.println("result : " + result);

//		System.out.println("==========================================");
		int tempResult = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (j == 0) {
					tempResult += tower[j][i];
//					System.out.println("tower[j][i] : " + tower[j][i]);
				} else {
//					System.out.println("abs : " + Math.abs(tower[j - 1][i] - tower[j][i]));
					tempResult += Math.abs(tower[j - 1][i] - tower[j][i]);
				}

				if (j == N - 1) {
					tempResult += tower[j][i];
//					System.out.println("tower[j][i] : " + tower[j][i]);
				}
			}
		}
//		System.out.println("tempResult : " + tempResult);

		System.out.println(result + tempResult + 2 * N * M);
	}

}
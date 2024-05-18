package mst_unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10775 {

	static int P, G, result;
	static int[] maxGateList, tempMaxIdx;
	static boolean[] isClosed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		maxGateList = new int[P + 1];
		tempMaxIdx = new int[G + 1];
		isClosed = new boolean[G + 1];
		for (int i = 1; i <= P; i++) {
			maxGateList[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i <= G; i++) {
			tempMaxIdx[i] = i;
		}

		for (int i = 1; i <= P; i++) {
			int curMaxGate = maxGateList[i];
			boolean isPossible = false;

			if (curMaxGate > G) {
				break;
			}

			for (int j = tempMaxIdx[curMaxGate]; j >= 1; j--) {
				if (isClosed[j] == false) {
					isPossible = true;
					isClosed[j] = true;
					result++;

					tempMaxIdx[curMaxGate] = tempMaxIdx[j - 1];
					break;
				}
			}

			if (!isPossible) {
				break;
			}
		}

		System.out.println(result);
	}

}

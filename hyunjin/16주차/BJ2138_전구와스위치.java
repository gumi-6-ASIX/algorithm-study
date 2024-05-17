package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2138 {

	static int N, minCount, countOn, countOff;
	static boolean[] current, result, tempOn, tempOff;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		current = new boolean[N];
		result = new boolean[N];
		tempOn = new boolean[N];
		tempOff = new boolean[N];
		minCount = Integer.MAX_VALUE;

		String c = br.readLine();
		String r = br.readLine();
		for (int i = 0; i < N; i++) {
			if (c.charAt(i) == '0') {
				current[i] = true;
			} else {
				current[i] = false;
			}

			if (r.charAt(i) == '0') {
				result[i] = true;
			} else {
				result[i] = false;
			}
		}

		// 첫번째 스위치를 켜거나 끈 상태
		countOn++;
		for (int i = 0; i < N; i++) {
			if (i <= 1) {
				tempOn[i] = !current[i];
				tempOff[i] = current[i];
			} else {
				tempOn[i] = current[i];
				tempOff[i] = current[i];
			}
		}

		// 다음 스위치 하나씩 탐색
		// 첫번째 스위치를 켜거나 끈 상태
		for (int i = 1; i < N; i++) {
			// i-1 전구가 원하는 상태와 다르면 i 전구 켜기
			if (tempOn[i - 1] != result[i - 1]) {
				switchLight(tempOn, i);
				countOn++;
			}
			if (tempOff[i - 1] != result[i - 1]) {
				switchLight(tempOff, i);
				countOff++;
			}

			// result랑 같은지 비교
			if (Arrays.equals(tempOn, result)) {
				minCount = Math.min(minCount, countOn);
			}
			if (Arrays.equals(tempOff, result)) {
				minCount = Math.min(minCount, countOff);
			}
			
			if(minCount != Integer.MAX_VALUE) {
				break;
			}
		}
		
		if(minCount == Integer.MAX_VALUE) {
			minCount = -1;
		}
		System.out.println(minCount);

	}

	public static void switchLight(boolean[] arr, int idx) {
		arr[idx - 1] = !arr[idx - 1];
		arr[idx] = !arr[idx];
		if (idx < N - 1) {
			arr[idx + 1] = !arr[idx + 1];
		}
	}

}

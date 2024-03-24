package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1826 {

	static int N, L, P;
	static int[] dp, stations;
	static Station[] tempStations;
	static PriorityQueue<Station> pq = new PriorityQueue<Station>();

	static class Station implements Comparable<Station> {
		int distance;
		int gas;

		public Station(int distance, int gas) {
			this.distance = distance;
			this.gas = gas;
		}

		@Override
		public int compareTo(Station o) {

			return o.gas - this.gas;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tempStations = new Station[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int distance = Integer.parseInt(st.nextToken());
			int gas = Integer.parseInt(st.nextToken());
			tempStations[i] = new Station(distance, gas);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dp = new int[L + 1];
		stations = new int[L + 1];

		for (Station s : tempStations) {
			stations[s.distance] = s.gas;
		}

		int cnt = 0;
		for (int i = 1; i <= L; i++) {
			if (P == 0) {
				Station station = pq.poll();// 널 처리 필요
				if (station != null) {
					P += station.gas;
					cnt++;
//					System.out.println("station: " + station.distance);
				} else {
					cnt = -1;
				}

			}
			P--;
			dp[i] = cnt;
			if (stations[i] > 0) {
				pq.add(new Station(i, stations[i]));
			}
		}

//		for(int i:dp) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
		System.out.println(dp[L]);
	}

}

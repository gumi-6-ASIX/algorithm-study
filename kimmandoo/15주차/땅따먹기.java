class Solution {
    int solution(int[][] land) {
        int answer = -1;
        int[][] dp = new int[land.length][4];
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 0; i < 4; i++) {
            for (int r = 1; r < land.length; r++) {
                dp[r][0] = Math.max(dp[r - 1][1], Math.max(dp[r - 1][2], dp[r - 1][3])) + land[r][0];
                dp[r][1] = Math.max(dp[r - 1][0], Math.max(dp[r - 1][2], dp[r - 1][3])) + land[r][1];
                dp[r][2] = Math.max(dp[r - 1][0], Math.max(dp[r - 1][1], dp[r - 1][3])) + land[r][2];
                dp[r][3] = Math.max(dp[r - 1][1], Math.max(dp[r - 1][0], dp[r - 1][2])) + land[r][3];
            }
            for (int c = 0; c < 4; c++) {
                if (i != c) {
                    answer = Math.max(answer, dp[land.length - 1][c]);
                }
            }
        }

        return answer;
    }
}
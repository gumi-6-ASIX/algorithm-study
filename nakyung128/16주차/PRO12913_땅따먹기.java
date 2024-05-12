class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int row = land.length;
        int[][] dp = new int[row][4];

        dp[0] = land[0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), dp[i - 1][3]) + land[i][0];
            dp[i][1] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][2]), dp[i - 1][3]) + land[i][1];
            dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][3]) + land[i][2];
            dp[i][3] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + land[i][3];
        }

        answer = Math.max(Math.max(dp[row - 1][0], dp[row - 1][1]), Math.max(dp[row - 1][2], dp[row - 1][3]));

        return answer;
    }
}
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int[][] sum = new int[n][4];
        sum[0][0]=land[0][0];
        sum[0][1]=land[0][1];
        sum[0][2]=land[0][2];
        sum[0][3]=land[0][3];
        for(int i=1;i<n;i++){
            for(int j=0;j<4;j++){
                sum[i][j] = Math.max(Math.max(sum[i-1][(j+1)%4],sum[i-1][(j+2)%4]),sum[i-1][(j+3)%4])+land[i][j];
            }
        }
        answer = Math.max(Math.max(Math.max(sum[n-1][0],sum[n-1][1]),sum[n-1][2]),sum[n-1][3]);
        
        return answer;
    }
}

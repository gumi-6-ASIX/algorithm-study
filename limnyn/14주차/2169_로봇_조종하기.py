# https://www.acmicpc.net/problem/2169
'''
1 <= N, M <= 1,000

[목표]
    탐사 가치의 합이 최대가 되도록 1,1에서 N,M으로 이동하기

    반복문으로 접근해보기
    왼쪽 - 오른쪽 한번씩 탐색을하면서
    각 탐색 별로 위 | 왼 , 위 | 오른쪽 값을 비교해서 dp에 갱신한다?
    
    dp에 대해서 r,c일 때의 최대 값을 구하는 방법을
        dp1 = 좌 | 상
        dp2 = 우 | 상
        에서 접근할 때의 최대 값으로 지정하고
        dp[r] = max(dp1, dp2)로 찾아서 특정 좌표 r,c에 접근하는 방법 중 최대값을 찾는 방식

    def dp():
        for r in range(1,n): # 각 행에 대해서
            # 좌|상 탐색
            dp1 = [-2e9]*m
            for c in range(m):
                dp1[c] = max(dp1[c], grid[r][c] + dp[r-1][c])
                if c >= 1:
                    dp1[c] = max(dp1[c], grid[r][c] + dp1[c-1])

            dp2 = [-2e9]*m
            # 우|상 탐색
            for c in range(m-1, -1, -1):
                dp2[c] = max(dp2[c], grid[r][c] + dp[r-1][c])
                if c+1 < m:
                    dp2[c] = max(dp2[c], grid[r][c] + dp2[c+1])
            

        return dp[-1][-1]
            

'''

import sys
input = sys.stdin.readline

dr = [0, 1, 0]
dc = [-1, 0, 1]

    
def dp_control_case():
    dp[0][0] = grid[0][0]
    for i in range(1, m):
        dp[0][i] = dp[0][i-1] + grid[0][i]
    for r in range(1,n): # 각 행에 대해서
        # 좌|상 탐색
        dp1 = [-2e9]*m
        for c in range(m):
            dp1[c] = max(dp1[c], grid[r][c] + dp[r-1][c])
            if c >= 1:
                dp1[c] = max(dp1[c], grid[r][c] + dp1[c-1])

        dp2 = [-2e9]*m
        # 우|상 탐색
        for c in range(m-1, -1, -1):
            dp2[c] = max(dp2[c], grid[r][c] + dp[r-1][c])
            if c+1 < m:
                dp2[c] = max(dp2[c], grid[r][c] + dp2[c+1])
        
        for c in range(m):
            dp[r][c] = max(dp1[c], dp2[c])
    return dp[-1][-1]
            


if __name__== "__main__":
    dr = [-1, 0, 1]
    dc = [0, 1, 0]
    n, m = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(n)]
    dp = [[-2e9] * m for _ in range(n)]
    print(dp_control_case())



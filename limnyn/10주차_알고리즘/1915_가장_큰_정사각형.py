# https://www.acmicpc.net/problem/1915

'''
가장 큰 정사각형
DP 문제

    2x2 
        1 1
        1 1

    3x3
        1 1 1
        1 1 1
        1 1 1

    일 때, dp처럼 계산하려면
    (0,-1), (-1, 0), (-1,-1) 중 최소값이 박스가 된다.

    2x2 dp
        1 1
        1 2

    3x3 dp
        1 1 1
        1 2 2
        1 2 3


    따라서 
        인접 이전 좌표들 P를  (0,-1), (-1, 0), (-1,-1) 라고 할 때
        
        if P에 0이 존재하면 
            dp[r][c] = 1

        elif p가 모두 같은 값이면
            dp[r][c] = p[0] + 1

        else:
            dp[r][c] = min(p) + 1

        그 외에는 (0,-1), (-1, 0), (-1,-1)중 최솟값을 넣어주고, 
        만약 세 좌표의 값이 다 같으면 +1 해주는 방식으로 해결해보자

'''

import sys
input = sys.stdin.readline

# 입력
n, m = map(int, input().split())
grid = [list(map(int, list(input().strip()))) for _ in range(n)]

# dp 수행
dp = [[0]*(m+1) for _ in range(n+1)]
result = 0
for r in range(1, n+1):
    for c in range(1, m+1):
        if grid[r-1][c-1] == 0:
            dp[r][c] = 0
            continue

        dp[r][c] = min([dp[r-1][c-1], dp[r-1][c], dp[r][c-1]]) + 1
        result = max(result,dp[r][c])

print(result*result)

        


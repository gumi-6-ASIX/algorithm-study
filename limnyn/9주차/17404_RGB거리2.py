# https://www.acmicpc.net/problem/1149



'''

RGB거리 1에서 마지막 좌표에 대한 시작점을 1e9(max)로 설정하고 진행


'''

import copy, sys
input = sys.stdin.readline
costs = []
result = 1e9
n = int(input())
for _ in range(n):
    costs.append(list(map(int, input().split())))

for start in range(3):
    cost_for_dp = copy.deepcopy(costs)
    cost_for_dp[0][start] = 1e9
    dp = [[0,0,0] for _ in range(n)]
    dp[0] = cost_for_dp[0]
    for i in range(1, n):
        for j in range(3):
            dp[i][j] = min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])+cost_for_dp[i][j]
    
    result = min(result, dp[-1][start])

print(result)
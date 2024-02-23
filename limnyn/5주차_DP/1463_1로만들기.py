# https://www.acmicpc.net/problem/1463

"""
dp[n] = min(dp[i//3], dp[i//2], dp[i-1])
"""
n = int(input())

dp = [0]*1000001

dp[1] = 0
dp[2] = 1
dp[3] = 1
for i in range(4, n+1):
    candi = [dp[i-1]]
    if i % 3 == 0:
        candi.append(dp[i//3])
    if i % 2 == 0:
        candi.append(dp[i//2])
    dp[i] = min(candi) + 1


print(dp[n])
n = int(input())

'''
현재 컬러 + 직전에 현재컬러가 아닌 경우의 값들 중 최솟값 
costs 
[26 40 83]
[49 60 57]
[13 89 99]
dp[0] = {26, 40, 83}
dp[1] = [{49 + min(40,83)}, {60 + min(40,83)}, {57 + min(26, 40)}]
dp[2] = [{13 + min(60,57)}, {89 + min(57,49)}, {99 + min(49, 60)}]
->
for i in range(1, n):
    for j in range(3):
        dp[i][j] = min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])+costs[i][j]
return min(dp[n-1])
'''
dp = [[0,0,0] for _ in range(n)]
costs = []
for _ in range(n):
    costs.append(list(map(int, input().split())))
dp[0] = costs[0]
for i in range(1, n):
    for j in range(3):
        dp[i][j] = min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])+costs[i][j]

print(min(dp[n-1]))


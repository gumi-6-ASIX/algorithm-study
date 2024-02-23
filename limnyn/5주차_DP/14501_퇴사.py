# https://www.acmicpc.net/problem/14501

n = int(input())

dp = [0] * (n+1)

days = []
costs = []
for _ in range(n):
    d, c = map(int, input().split())
    days.append(d)
    costs.append(c)

result = 0
for i in range(n-1,-1,-1):
    day, cost = days[i], costs[i]
    day_end = i + day
    if day_end <= n :
        result = max(cost+dp[day_end], result)
    dp[i] = result
# print(dp)
print(result)



# https://www.acmicpc.net/problem/12865

'''
평범한 배낭

dp 2차원(r,c) 배열에 대해
가로축(row) (value): 0부터 r번 물건중에서 선택할 수 있을 때
세로축(col) (wage) : 0부터 max_wage까지에 대해 해당 col무게 까지만 들 수 있을 때 최대 value합

따라서 dp 식은
    dp[v][w] = 
        max(
            dp[v-1][w - wage[v]] + value[v], # 현재 row 번 물건을 선택하고, wage[row번] 물건의 무게만큼 적게 선택 했을 때 최대값
            dp[v-1][w] # 현재 무게 중에 이전 물건 선택까지의 최대값
            )
    즉 현재 물건을 선택할 때, 선택하지 않을 때 경우 중 최대 값으로 갱신해준다.

'''
import sys
input = sys.stdin.readline

n, max_wage= map(int, input().split())
wage = []
value = []
for _ in range(n):
    w, v = map(int, input().split())
    wage.append(w)
    value.append(v)


dp = [[0] * (max_wage + 1) for _ in range(n + 1)]

for r in range(1, n + 1):
    for c in range(1, max_wage+1):
        if c - wage[r-1] >= 0:
            dp[r][c] = max(dp[r-1][c-wage[r-1]] + value[r-1], dp[r-1][c])
        else:
            dp[r][c] = dp[r-1][c]
            

print(dp[-1][-1])
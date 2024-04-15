# https://www.acmicpc.net/problem/1520
'''
'항상 자기 자신보다 작은 크기로 이동'

50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10 그리드에 대해서

4방향 dfs 탐색을 하면서 방문했던 적 없으면 탐색한 것을 표시하고
만약 방문한 적이 없으면 (-1) 방문처리
방문한 적이 있으면 해당 pd + 1 





'''
import sys
input = sys.stdin.readline  

dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]
n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]
dp = [[-1] * m for _ in range(n)]

def dfs(r, c):
    if r == n-1 and c == m-1:
        return 1
    if dp[r][c] != -1:
        return dp[r][c]
    
    dp[r][c] = 0
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        
        if 0 <= nr < n and 0 <= nc < m: 
            if grid[r][c] > grid[nr][nc]:
                dp[r][c] += dfs(nr,nc)
                
    return dp[r][c]


dfs(0,0)
for d in dp:
    print(d)
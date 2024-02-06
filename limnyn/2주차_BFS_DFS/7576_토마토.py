# https://www.acmicpc.net/problem/7576

"""
시간 단위로 진행된다

모든 익은 토마토좌표들을 처음부터
deque에 넣고 bfs를 돌리고
그중 가장 높은 depth를 return 하면 답이 될 거 같다
"""

from collections import deque

m, n = map(int, input().split())

grid = [list(map(int, input().split())) for _ in range(n)]

ripen_tomatoes = []
for i in range(n):
    for j in range(m):
        if grid[i][j] == 1:
            ripen_tomatoes.append([i,j])



dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]
def bfs():
    dq = deque(ripen_tomatoes)
    max_days = 0

    while dq:
        r, c = dq.popleft()
        days = grid[r][c]
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            
            if 0 <= nr < n and 0 <= nc < m:
                if grid[nr][nc] == 0:
                    grid[nr][nc] = days+1
                    max_days = max(max_days, days+1)
                    dq.append([nr, nc])

    if max_days == 0:
        return 0

    return max_days-1

result = bfs()
for i in range(n):
    for j in range(m):
        if grid[i][j] == 0:
            print(-1)
            exit()

print(result)

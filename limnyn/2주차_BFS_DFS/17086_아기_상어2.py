# https://www.acmicpc.net/problem/17086


"""
모든 0인 칸에 대해
MAX(가장 가까운 상어의 거리)를 출력하라는 뜻이였음


"""

from collections import deque

babyshark_spot = []
dr = [-1,-1,0,1,1,1,0,-1]
dc = [0,1,1,1,0,-1,-1,-1]

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]


def bfs(start_r,start_c):
    dq = deque([])
    dq.append([start_r,start_c])

    visited = [[0]*m for _ in range(n)]
    visited[start_r][start_c] = 1
    while dq:
        r, c = dq.popleft()
        
        for i in range(8):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < n and 0 <= nc < m:
                if visited[nr][nc] == 0:
                    if grid[nr][nc] == 1:
                        return visited[r][c]
                    elif grid[nr][nc] == 0:
                        visited[nr][nc] = visited[r][c] + 1 
                        dq.append([nr,nc])


result = []

for i in range(n):
    for j in range(m):
        if grid[i][j] != 1:
            result.append(bfs(i,j))

print(max(result))



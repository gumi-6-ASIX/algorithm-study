# https://www.acmicpc.net/problem/2589

"""
1. bfs 로 깊이최대값을 찾을 수 있을 거 같다

[시간 초과]

2. bfs를 돌 필요가 없는 중복을 제거
    - 중간은 최장거리가 아니다.
    - 중간을 제외하고 테두리에서만 길이를 잰다
    -> 낀 부분은 스킵한다
    -> 자신의 양옆, 좌우에 땅이 있을 때는 생략한다
"""

from collections import deque

dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]
n, m = map(int, input().split())

grid = [input() for _ in range(n)]

def bfs(start_r, start_c):
    
    visited = [[-1] * m for _ in range(n)]
    visited[start_r][start_c] = 0
    max_len = 0
    dq = deque([])
    dq.append([start_r, start_c])
    while dq:     
        r, c = dq.popleft()

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < m:

                #첫 방문한 'L'에 대해
                if visited[nr][nc] == -1 and grid[nr][nc] == 'L':
                    visited[nr][nc] = visited[r][c] + 1
                    
                    max_len = max(visited[nr][nc], max_len)

                    dq.append([nr,nc])
    
    return max_len

time_max = 0
for i in range(n):
    for j in range(m):

        if grid[i][j] == 'L':
            #테두리만 bfs 루프를 돌게 추가하기
            if 1 <= i < n-1:
                if grid[i-1][j] == 'L' and grid[i+1][j] == 'L':
                    continue
            if 1 <= j < m-1:
                if grid[i][j-1] == 'L' and grid[i][j+1] == 'L':
                    continue
            time_max = max(bfs(i,j), time_max)

print(time_max)

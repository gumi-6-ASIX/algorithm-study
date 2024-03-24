# https://www.acmicpc.net/problem/1012

"""
전역 visited를 공유하여 각 좌표별로 bfs를 돌면 구할 수 있다.
"""
from collections import deque

testcase = int(input())
answer = []
for _ in range(testcase):

    m,n,k = map(int, input().split())

    grid = [[0]*m for _ in range(n)]

    for _ in range(k):
        c, r = map(int, input().split())
        grid[r][c] = 1


    visited = [[False]*m for _ in range(n)]
    dr = [-1, 0, 1, 0]
    dc = [0, -1, 0, 1]


    def bfs(start_r,start_c):
        if grid[start_r][start_c] == 0 or visited[start_r][start_c] == True:
            return 0

        dq = deque([])
        dq.append([start_r, start_c])
        
        while dq:
            r, c = dq.popleft()
            
            for i in range(4):
                nr = r + dr[i]
                nc = c + dc[i]
            
                if 0 <= nr < n and 0 <= nc < m:

                    if grid[nr][nc] == 1 and visited[nr][nc] == False:
                        visited[nr][nc] = True
                        dq.append([nr,nc])
        return 1
                        
    result = 0
    for i in range(n):
        for j in range(m):
            r=bfs(i,j)
            if r==1:
                print(i, j, r)
            result+=r

    answer.append(result)

for ans in answer:
    print(ans)



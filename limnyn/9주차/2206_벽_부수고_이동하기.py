# https://www.acmicpc.net/problem/2206

'''
벽을 부술 때, 안 부술 때 처리를 두 가지 해야한다.
시간 초과를 막기위해 bfs로 탐색을 하려고 한다
visited 처리를 어떻게 해야 할까?

grid에 대한, 2차원 visited로 해결하자
visited에 대해 부쉈을 때, 안부쉈을 때를 0, 1인 3차원으로 처리하고

bfs순회 도중 
    visited[0]일 때 방문안한 경우 visited[1]을 방문처리해주면서
    bfs 순회 내부에서 차원 상관 없이 n-1, m-1 좌표에 도달하면
    가장 먼저 도달 == 최단 거리 이기 때문에
    break한다.





'''
import sys
from collections import deque
input = sys.stdin.readline
BREAK_WALL = 1
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]


#입력
n, m = map(int, input().split())
grid = []
for i in range(n):
    line = list(map(int, list(input().strip())))
    grid.append(line)

# 벽을 안부쉈을 때, 부쉈을 때 0, 1로 처리
visited = [[[0]* m for _ in range(n)] for _ in range(2)]
dq = deque([])
dq.append((0, 0, 0)) # 벽을 안 부수고 0,0에서 시작
visited[0][0][0] = 1
result = 0

while dq:
    
    r, c, isbreaked = dq.popleft() # r, c, 벽 부순 여부 검사
    # 제일 먼저 마지막 칸 도달하면 탈출
    if r == n-1 and c == m-1 and visited[isbreaked][r][c] != 0:
        result = visited[isbreaked][r][c]
        break
    
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        
        if 0 <= nr < n and 0 <= nc < m:

            # 벽을 부수지 않았을 때 벽을 만났다면
            if isbreaked != BREAK_WALL and grid[nr][nc] == 1: 

                # 해당 순회에서 다른 좌표에서 이미 벽을 부순게 아니라면
                if visited[0][nr][nc] == 0:
                    # 벽을 부수고 진행
                    visited[0][nr][nc] = visited[0][r][c] + 1
                    visited[BREAK_WALL][r][c] = visited[0][r][c]
                    visited[BREAK_WALL][nr][nc] = visited[0][r][c] + 1
                    dq.append((nr, nc, BREAK_WALL))
            
            # 벽이 아니고 처음 방문하는 곳이라면 진행   
            if grid[nr][nc] == 0 and visited[0][nr][nc] == 0:

                # 벽을 부수거나 안부수거나 가장 처음 도착했을 때 거리 기준 방문 처리
                if visited[isbreaked][nr][nc] == 0:
                    visited[isbreaked][nr][nc] = visited[isbreaked][r][c] + 1
                    dq.append((nr, nc, isbreaked))

if result == 0:
    print(-1)
else:
    print(result)


'''
3 3
000
000
000

5 5
00000
11110
00000
01111
00001

6 5
00000
11110
00000
01111
01111
00010

9 9
010001000
010101010
010101010
010101010
010101010
010101010
010101010
010101011
000100010

'''


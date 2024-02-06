# https://www.acmicpc.net/problem/14502

from itertools import combinations
from collections import deque
n, m = map(int, input().split())
grid = [list(map(int,input().split())) for _ in range(n)]

virus_spot_list = []
for i in range(n):
    for j in range(m):
        if grid[i][j] == 2:
            virus_spot_list.append([i,j])




comb = [(r, c) for r in range(n) for c in range(m)]
wall_position_list = combinations(comb, 3)

dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]


#벽의 위치 별 안전거리 반환 함수
def safe_area(wall_positions):

    is_visited = [[0]* m for _ in range(n)]
    
    #벽을 세울 수 없는 경우라면 0 반환
    for r, c in wall_positions:
        if grid[r][c] != 0:
            return 0
            #벽은 -1
        is_visited[r][c] = -1
    
    for r, c in virus_spot_list:
        is_visited[r][c] = 2
    
    dq = deque(virus_spot_list)
    
    while dq:
        r, c = dq.popleft()
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
        
            if 0 <= nr < n and 0 <= nc < m:
                if grid[nr][nc] == 0:
                    if is_visited[nr][nc] == 0:
                        is_visited[nr][nc] = 2
                        dq.append([nr,nc])
                if grid[nr][nc] == 1:
                    is_visited[nr][nc] = -1

    # is_visited는 지금 바이러스만 표시되어 있고 벽은 표시되어 있지 않다.
    
    safe_area_size = 0
    for r in range(n):
        for c in range(m):
            if is_visited[r][c] == 0:
                if grid[r][c] == 0:
                    safe_area_size+=1
    
    return safe_area_size
    
    
    
    
# for v in safe_area([[1,0],[0,1],[3,5]]):
#     print(v)

max_safe_area = 0
for wall_positions in wall_position_list:
    max_safe_area = max(max_safe_area, safe_area(wall_positions))
    
print(max_safe_area)
# safe_area(wall_positions_list)


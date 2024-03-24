# https://www.acmicpc.net/problem/16234

"""
문제에 대한 접근

While 연합이 존재하지 않을 때 까지:
    1. Counting cells in blob 문제를 응용해 
        조건을 만족하는 연합을 찾아서 
    2. 인구를 이동한 후
    3. result += 1
    반복
"""
from collections import deque
dr = [-1, 0, 1 ,0]
dc = [0, 1, 0, -1]
n, left, right = map(int, input().split())

def find_union(start_r, start_c):
    global visited, grid
    if visited[start_r][start_c] == True:
        return False
    
    visited[start_r][start_c] = True
    union_cnt = 1
    union_sum = grid[start_r][start_c]
    
    unions = [(start_r, start_c)]
    dq = deque([])
    dq.append([start_r, start_c])

    while dq:
        r, c = dq.popleft()

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < n and 0 <= nc < n:
                if visited[nr][nc] == False:
                    if left <= abs(grid[nr][nc]-grid[r][c]) <= right:
                        visited[nr][nc] = True
                        union_cnt += 1
                        union_sum += grid[nr][nc]
                        unions.append((nr,nc))
                        dq.append([nr,nc])
    if union_cnt == 1:
        return False
    for uni in unions:
        ur, uc = uni
        grid[ur][uc] = int(union_sum/union_cnt) 
    return True
    

grid = []
for _ in range(n):
    grid.append(list(map(int, input().split())))

days = 0
while 1:
    visited = [[False]*n for _ in range(n)]
    isUnioned = False
    for r in range(n):
        for c in range(n):
            result = find_union(r,c)
            if result:
                isUnioned = True
    if isUnioned:
        days+=1
    else:
        break
print(days)

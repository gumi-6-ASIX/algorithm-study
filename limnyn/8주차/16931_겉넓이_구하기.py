# https://www.acmicpc.net/problem/16931

"""
1. 테두리 블록은 높이만큼 더한다
2. 내부 블록은 이전 블록과 높이 차 만큼 더한다
"""
import sys
input = sys.stdin.readline
print = sys.stdout.write
n, m = map(int, input().split())
grid = list([list(map(int, input().split())) for _ in range(n)])




cnt = 2*(m*n)
for r in range(n):
    for c in range(m):
        if r == 0: 
            cnt += grid[r][c]
        else:
            cnt += abs(grid[r][c]-grid[r-1][c])
        if c == 0:
            cnt += grid[r][c]
        else:
            cnt += abs(grid[r][c]-grid[r][c-1])
        if r == n-1:
            cnt += grid[r][c]
        if c == m-1:
            cnt += grid[r][c]
print(str(cnt)+'\n')
        


# """
# bfs탐색을 돌면서 주변에 존재하지 않을 때 ,
# 존재하지 않는 갯수만큼 겉넓이가 존재한다/
# """
# import sys
# input = sys.stdin.readline
# n, m = map(int, input().split())
# grid = [[0 for _ in range(m)] for _ in range(n)]
# visited = [[[] for _ in range(m)] for _ in range(n)]
# for i in range(n):
#     line = list(map(int, input().split()))
#     for j in range(m):
#         grid[i][j] = line[j]
#         visited[i][j].extend([False]*line[j])


# from collections import deque


# dq = deque([])
# dq.append([0,0,0])
# dr = [-1, 0, 1, 0]
# dc = [0, 1, 0, -1]
# dh = [-1, 1]
# count = 0

# visited[0][0][0] = True
# while dq:
#     r, c, h = dq.popleft()
#     for i in range(6):
#         if i < 4:
#             nr = r + dr[i]
#             nc = c + dc[i]
#             nh = h
#         else:
#             nr = r
#             nc = c
#             nh = h + dh[i%4]

#         if 0 <= nr < n and 0 <= nc < m and 0 <= nh < grid[nr][nc]:
#             if visited[nr][nc][nh] == False:
#                 visited[nr][nc][nh] = True
#                 dq.append([nr, nc, nh])
#         else:
            
#             count += 1

# print(count)



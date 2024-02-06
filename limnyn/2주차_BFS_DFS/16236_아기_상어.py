# https://www.acmicpc.net/problem/16236

"""
아기상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나 갈 수 없다
if 더 이상 먹을 수 있는 물고기가 공간에 없다면 끝
elif 먹을 수 있는 물고기가 1마리라면 그 물고기를 먹으러 간다
elif 먹을 수 있는 물고기가 여러개면 가장 가까운 물고기를 먹으러 간다.
    - 거리는 아기상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 
        지나야 하는 칸의 개수의 최솟값이다.
    - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기,
        그러한 물고기가 여러 마리라면, 가장 왼쪽에 있는 물고기

0 : 빈칸
1~6: 칸에 있는 물고기의 크기
9: 아기상어의 위치
"""

"""
현재 자신 위치에서 bfs를 통해 자신보다 작은 크기들에 대해 최단 거리를 파악해 이동
만약 없다면 break하고 time출력
만약 현재위치에서 거리가 같다면 row값 작은 곳으로
row값도 같다면 col값 작은쪽으로
"""

from collections import deque;
dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]
    
# 큐를 돌면서 자신보다 사이즈가 작은 것의 길이를 찾는다.
# 자기랑 크기 같으면 지나갈 순 있다.
# 다른 bfs랑 다르게 최단거리를 찾아도 그 깊이까지의 다른 답도 찾자
# 그 중에 r, c값을 비교하는 방식을 택하자
count = 2 # 성장할 때까지 먹어야 할 것


# 출발점 r,c에서 가장 가까운 먹이의 좌표와 먹는데 걸린 시간
def bfs(my_size, r, c, time): 
    visited = [[0]*n for _ in range(n)]
    visited[r][c] = 1
    dq = deque([])
    dq.append([r,c,time])
    
    result = []
    isfin = False

    # while dq:
    #     r, c, t = dq.popleft()
    #     for i in range(4):
    #         nr = r + dr[i]
    #         nc = c + dc[i]
    #         if 0 <= nr < n and 0 <= nc < n:
    #             if visited[nr][nc] != 0:
    #                 continue
    #             visited[nr][nc] = 1
    #             if grid[nr][nc] != 0 and grid[nr][nc] < my_size:
                    
    #                 result.append([nr,nc, t+1])
    #                 isfin = True
    #             elif grid[nr][nc] == 0 or  grid[nr][nc] == my_size:
                    
    #                 dq.append([nr,nc,t+1])
    #     if isfin:
    #         result.sort(key=lambda x: (x[0], x[1]))
    #         break
    # if not isfin:
    #     return -1, -1, time
    # else:    
    #     return result[0][0], result[0][1], result[0][2]

    while dq:
        r, c, t = dq.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < n:
                if visited[nr][nc] != 0:
                    continue
                visited[nr][nc] = 1
                if grid[nr][nc] != 0 and grid[nr][nc] < my_size:
                    
                    result.append([nr,nc, t+1])
                    isfin = True
                elif grid[nr][nc] == 0 or  grid[nr][nc] == my_size:
                    
                    dq.append([nr,nc,t+1])
    if isfin:
        result.sort(key=lambda x: (x[2], x[0], x[1]))
        return result[0][0], result[0][1], result[0][2]

    if not isfin:
        return -1, -1, time
    
    
    

n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]
# print(grid)





for i in range(n):
    for j in range(n):
        if grid[i][j] == 9:
            start_r, start_c = i, j
    

#시작점부터 돌린다. 만약 r, c, 가 -1, -1이 나오면 탈출하고 출력하자
time = 0
size = 2
count = 2
while 1:
    grid[start_r][start_c] = 0
    start_r, start_c, time = bfs(size, start_r,start_c,time)
    
    if start_r == -1:
        print(time)
        break
    count -= 1
    if count == 0:
        size += 1
        count = size





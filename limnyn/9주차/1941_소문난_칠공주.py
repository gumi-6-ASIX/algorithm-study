# https://www.acmicpc.net/problem/1941

'''
5*5 입력
완탐으로 접근해보기

주어진 조건 : 좌표평면 25칸
                공주 = 7명 
    25C7 = 480700     

    25 칸 중 7칸을 뽑아서
        1. 해당 칸이 이어져 있고
        2. 해당 칸에 S가 4개 이상 있는지 확인

        1 and 2 만족 시 count += 1
    

    1. 해당 칸이 이어져 있는지 확인하기
        -> 4방향을 bfs하면서 다음 칸의 좌표가 조합에 존재하는지 확인
            존재하면 visited 처리하고 반복해서 전부 방문하는지 확인
    
    2. bfs 탐색하며 S수 count

'''
from collections import deque
from itertools import combinations

import sys
input = sys.stdin.readline



dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

# 뽑은 조합이 이어져 있는지, 그리고 7공주가 되는지 True False 반환
def bfs(comb):
    # 출발점, 조합의 첫 좌표부터 출발
    start_r = comb[0][0]
    start_c = comb[0][1]
    visited = [[False]*5 for _ in range(5)]
    
    visit_count = 1 # 조합이 방문한 갯수 세기, 7개만 방문해야함
    
    s_count = 0 # s 갯수 세기
    if grid[start_r][start_c] == 'S':
        s_count += 1
    
    # bfs 탐색 진행
    dq = deque([])
    dq.append((start_r, start_c))
    visited[start_r][start_c] = True

    while dq and visit_count < 7:
        r, c = dq.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < 5 and 0 <= nc < 5:
                if visited[nr][nc] == False and (nr, nc) in comb:
                    if grid[nr][nc] == 'S':
                        s_count += 1
                    visited[nr][nc] = True
                    dq.append((nr,nc))
                    visit_count += 1

    if visit_count == 7 and s_count >= 4:
        return True
    return False



# 입력
grid = [input().strip() for _ in range(5)]


# 25좌표 생성
spots = []
for i in range(5):
    for j in range(5):
        spots.append((i,j))

# 25개의 좌표에 대해 7개를 뽑는 조합 생성
combs = combinations(spots, 7)
count = 0

# 25개중 7개좌표를 뽑아서 이어져 있고, 7공주 조건을 만족하는지 체크
for comb in combs:
    if bfs(comb):
        count += 1

print(count)

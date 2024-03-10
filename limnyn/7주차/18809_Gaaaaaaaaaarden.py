# https://www.acmicpc.net/problem/18809

'''
입력에 대해 
1 <= R, G <= 5
R+G <= 배양액을 뿌릴 수 있는 땅의 수 <= 10

10 c R * (10-R) c G
ex) R = 4, G = 2
    -> 10 c 4 * 6 C 2
    -> 3150개 조합
    
    max_count = -1e9
    for 조합 하나 in 3150개 조합:
        while(deque_red):
            day_for_green()
            day_for_red()
        
        max_count = max(꽃 개수 count,max_count)
    print(max_count)
'''

'''
0 : 호수
1 : 배양액을 뿌릴 수 없는 땅
2 : 배양액을 뿌릴 수 있는 땅
'''

import copy, sys
input = sys.stdin.readline
from collections import deque

n, m, green, red = map(int,input().split())
grid_origin = [list(map(int,input().split())) for _ in range(n)]

culture_able_spot = []
for r in range(n):
    for c in range(m):
        if grid_origin[r][c] == 2:
            culture_able_spot.append((r,c))



        
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]


def day_for_green():
    global grid, visited_g, day
    while green_dq:
        r, c = green_dq.popleft()
        if grid[r][c] == FLOWER:
            continue
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < m:
                if grid[nr][nc] == 1 or grid[nr][nc] == 2:
                    if visited_g[nr][nc] < 0:
                        visited_g[nr][nc] = day
                        grid[nr][nc] = GREEN
                        next_green_dq.append([nr,nc])

                
def day_for_red():
    global grid, visited_r, flower_count, day
    while red_dq:
        r, c = red_dq.popleft()
        if grid[r][c] == FLOWER:
            continue

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < n and 0 <= nc < m:
                if visited_r[nr][nc] < 0:
                    if grid[nr][nc] == 1 or grid[nr][nc] == 2:
                        visited_r[nr][nc] = day
                        grid[nr][nc] = RED
                        next_red_dq.append([nr,nc])
                    elif grid[nr][nc] == GREEN:
                        if visited_g[nr][nc] == day:
                            grid[nr][nc] = FLOWER
                            flower_count += 1
                            visited_r[nr][nc] = day











# 각 조합 구하기 
# 배앙액 칸 갯수를 A 라 할 때
# 모든 경우의 수 ->  A c Red * (A-Red) c Green
from itertools import combinations
for_comb = [x for x in range(len(culture_able_spot))]
# print(culture_able_spot)
combination_g = combinations(for_comb, green)
count = 0
#상수 처리
GREEN = -1
RED = -2
FLOWER = -3
#
result = -1e9
for comb in combination_g:
    
    green_comb = comb
    combination_r = combinations([x for x in for_comb if x not in green_comb],red)
    
    for red_comb in combination_r:

        flower_count = 0
        grid = copy.deepcopy(grid_origin)
        visited_g = [[-1]*m for _ in range(n)]
        visited_r = [[-1]*m for _ in range(n)]
        
        green_dq = deque([])
        next_green_dq = deque([])
        red_dq = deque([])
        next_red_dq = deque([])

        # 첫 상태, day0 초기화
        day = 0
        for spot_num in green_comb:
            green_r, green_c = culture_able_spot[spot_num]
            grid[green_r][green_c] = GREEN
            visited_g[green_r][green_c] = day
            green_dq.append([green_r, green_c])

        for spot_num in red_comb:
            red_r, red_c = culture_able_spot[spot_num]
            grid[red_r][red_c] = RED
            visited_r[red_r][red_c] = day
            red_dq.append([red_r, red_c])
        
        #여기서 while문으로 반복을 할 예정
        day = 1
        while True:
            day_for_green()
            if len(next_green_dq) == 0:
                break
            day_for_red()
            green_dq = next_green_dq
            red_dq = next_red_dq
            next_green_dq = deque([])
            next_red_dq = deque([])
            day+=1
        
        #특정 테케 디버깅을 위해 
        # if flower_count >= 3:
            # print(f"g조합 : {green_comb} r조합 : {red_comb} , flower_count : {flower_count}")
        result = max(result, flower_count)
        
        
print(result)
        # for r in r_comb:
        #     r_r, r_c = culture_able_spot[r]
        #     grid[r_r][r_c] = RED

"""
디버깅해볼 테케
5 7 3 2
1 0 1 2 1 2 1
1 1 1 0 1 0 2
2 1 0 0 1 1 1
1 0 2 1 2 1 0
0 2 1 1 0 1 2
"""
# https://www.acmicpc.net/problem/17135


"""
3<=n<=15
15개중 3자리 뽑는 경우 15C3 -> 455
최대 455번 game(positions_of_archers)을 수행시켜 최대값 출력
-> 완탐 수행

def game
    -> 각 archer에 대해 bfs를 통해 
        조건을 만족하기 위해서 (가장가까운 거리, 동일한 거리는 왼쪽것 부터)
        [0,-1], [-1,0] ,[0,1], 우 순서대로 탐색 하다가 제일 처음 만나는 적을 
        target으로 지정

        성벽에 배치된 궁수에 대해 stage(또는 round) 마다 
        궁수들의 좌표 중 r 값을 n부터 0까지 한 줄 씩 올리면서 궁수들의 거리 계산 및 bfs를 수행한다
        이후 표적들에 대해 list에 넣어서 stage가 끝날 때 마다 표적들을 0으로 만들어 주고 count를 한다

        stage가 끝나면 처리한 전체 적을 반환한다.
        

"""
from collections import deque

import copy

def game(positions_of_archers):
    dr = [0, -1, 0]
    dc = [-1, 0, 1]
    
    # grid에 적을 처치하면 0으로 표시 하기 위해 copy한다
    grid_for_comb = copy.deepcopy(grid)

    # 적 처치 수를 반환
    enemy_kill_count = 0
    for stage in range(n, -1, -1): 
        # 0~n-1 까지인 grid에 대해 궁수들의 r좌표를 n~0으로 처리하면 첫 줄은 n-1보다 크기 때문에 상향으로 시작한다. 
        # 이후부터는 좌.상.우 순서로 수행

        
        enemy_spot = []
        for archer in positions_of_archers:
            visited = [[False]*m for _ in range(n)]
            #archer는 거리가 가장가까운 값을 리스트에 넣고
            dq = deque([])
            dq.append([stage, archer, 0])
            
            while dq:
                r, c, dis = dq.popleft()

                for i in range(3):
                    nr = r + dr[i]
                    nc = c + dc[i]

                    if 0 <= nr < n and 0 <= nc < m:
                        if visited[nr][nc] == False:
                            visited[nr][nc] == True
                            if grid_for_comb[nr][nc] == 1:
                                if dis + 1 <= d:

                                    enemy_spot.append((nr,nc, dis + 1))
                                    dq = []
                                    break
                            else:
                                if dis + 1 <= d:
                                    dq.append([nr,nc, dis+1])



        for e_r, e_c, dis in enemy_spot:
            # 표적이 중복될 수 있으므로 한번씩만 카운트 하기 위해 1 -> 0으로 바꿀 때만 값을 계산 
            if grid_for_comb[e_r][e_c] == 1:
                enemy_kill_count += 1
                grid_for_comb[e_r][e_c] = 0

        # 궁수가 성벽부터 탐색한다
        # 이미 성벽을 지나간 적들을 0으로 처리
        # -> 성벽에 닿은 적들은 처리하지 않기 위해서
        grid_for_comb[stage-1]=[0]*m 
            
    return enemy_kill_count
    
grid = []
n, m, d = map(int, input().split())
for _ in range(n):
    grid.append(list(map(int, input().split())))

from itertools import combinations
#최대값 비교를 위해
max_result = -1e9

comb = combinations([x for x in range(m)],3) # m개 중에 3개를 뽑는 조합의 수를 구한다

for cmb in comb:
    # 각 조합에 대해 game에 넣어 조합별로 처리한 적의 수를 구한다
    res = game(cmb)
    
    # 가장 많이 처리한 조합의 결과를 저장
    max_result = max(res, max_result)
#해당 결과 출력
print(max_result)
    
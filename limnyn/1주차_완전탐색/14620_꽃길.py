# https://www.acmicpc.net/problem/14620
"""
완전탐색이라면 
2차원에서 조합으로 3개 뽑으면 되는거아닌가?
그중에서 cost합의 최소값을 출력
"""
n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

min_cost = 1e9
dr = [-1, 0, 1, 0, 0] # 4방향 + 자기자신 더해야하니까 그냥 for문으로 처리하기 위해
dc = [0, 1, 0, -1, 0]

#좌표쌍 3개를 받고(spots), 꽃잎이 겹치면 -1 , 안겹치면 땅값들의 합 반환
def seed_cost(spots):
    visitied = [[0]*n for _ in range(n)]
    cost = 0
    for spot in spots:
        r, c = spot
        if visitied[r][c] == 1:
            return -1
        
        for i in range(5):
            nr = r + dr[i]
            nc = c + dc[i]
            if visitied[nr][nc] == 0:
                visitied[nr][nc] = 1
            elif visitied[nr][nc] == 1:
                return -1

        for i in range(5):
            nr = r + dr[i]
            nc = c + dc[i]
            cost += grid[nr][nc]

    return cost

from itertools import combinations

# spots =  [1:n]까지 2차원 그리드 i,j 좌표쌍들을 1차원으로 배열에 넣는다
spots = [[i, j] for j in range (1, n-1) for i in range(1,n-1)]

for comb in combinations(spots, 3): #좌표쌍 중 3개를 뽑는 조합 리스트

    result = seed_cost(comb) #뽑은 3개의 좌표를 함수에 넣고 최소값 또는 -1 반환
    
    if result != -1: #-1 , 즉 꽃끼리 겹치지 않았을 때
        min_cost = min(min_cost, result)
# 결과 출력
print(min_cost)



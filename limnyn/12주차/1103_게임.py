# https://www.acmicpc.net/problem/1103

'''
1 <= N,M <= 50

문제
    보드가 주어졌을 떄 "최대 몇 번" 움직일 수 있는지
    만약 "무한"으로 움직이면, -1 출력

접근
    1. "무한"을 판단하는 기준
    2. "최대"로 움직이는 횟수 로직

[1. 무한을 판단하는 기준]
    알고리즘에서 무한하게 움직인다는 개념이 나타나는 경우를 생각해보면
    "그래프 이동 간 무한 싸이클" 같은 생각을 떠올릴 수 있다.
    이 문제에서도 무한하게 움직이는 경우는 "싸이클"이 생겨 무한하게 움직일 수 있는 경우일 것이다.
    
    "싸이클이 생기는 경우", 즉 -1을 출력해야하는 경우를 어떻게 판별해야 할까
    
    싸이클 -> [방문 했던 곳을 다시 방문하는 경우]

[2. 최대로 움직이는 경우]
    최대로 움직이는 경우를 찾으려면 어떻게 해야할까?
    
    -> dfs
        dfs를 수행하면서 hole에 빠질때 까지 깊이 탐색을 하며 탐색깊이의 max를 반환하기

    여기에 시간복잡도 및 중복 연산을 줄이기 위해서는?
    -> dfs + dp
        -1로 초기화된 dp그리드에 대해서
        (r,c)의 각 4방향(nr,nc)으로 탐색하면서,  
        dp[nr][nc] 가 -1이 아니라면 dp[r][c] = max(dp[nr][nc], dp[r][c]) 를 수행할 수 있다.
    
        수도코드

            def dfs(r, c):
                global cycle
                if cycle:
                    return 0

                if dp[r][c] != -1:
                    return dp[r][c]
                
                if visited[r][c]:
                    cycle = True
                    print(-1)
                    exit(0)
                
                if not visited[r][c]:
                    visited[r][c] = True
                    num = grid[r][c]
                    result = 1
                    
                    for i in range(4):
                        nr = (r+1) + dr[i]*num -1
                        nc = (c+1) + dc[i]*num -1
                        
                        # 만약 다음 칸이 범위를 벗어나거나 'H'에 빠진다면(-1) 이번칸까지 이동이 마지막
                        if not (0 <= nr < n and 0 <= nc < m) or grid[nr][nc] == -1:
                            continue
                        result = max(result, dfs(nr, nc) + 1)
                    dp[r][c] = result
                return dp[r][c]
                





'''
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

dr = [-1,1,0,0]
dc = [0,0,-1,1]

n, m = map(int, input().split())

grid = []
for _ in range(n):
    line = list(input().strip())
    for i in range(m):
        if line[i] == 'H':
            line[i] = '-1'
    grid.append(list(map(int, line)))

dp = [[-1]*m for _ in range(n)]
visited = [[False]*m for _ in range(n)]

cycle = False

def dfs(r, c):
    global cycle
    if cycle:
        return 0

    if dp[r][c] != -1:
        return dp[r][c]
    
    if visited[r][c]:
        cycle = True
        print(-1)
        exit(0)
    
    if not visited[r][c]:
        visited[r][c] = True
        num = grid[r][c]
        result = 1
        
        for i in range(4):
            nr = (r+1) + dr[i]*num -1
            nc = (c+1) + dc[i]*num -1
            
            # 만약 다음 칸이 범위를 벗어나거나 'H'에 빠진다면(-1) 이번칸까지 이동이 마지막
            if not (0 <= nr < n and 0 <= nc < m) or grid[nr][nc] == -1:
                continue
            result = max(result, dfs(nr, nc) + 1)
        dp[r][c] = result
    return dp[r][c]

dfs(0,0)


print(dp[0][0])


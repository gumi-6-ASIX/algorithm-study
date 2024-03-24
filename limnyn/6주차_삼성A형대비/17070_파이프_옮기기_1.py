# https://www.acmicpc.net/problem/17070

"""
문제 접근

dfs 백트래킹으로 
    if 벽에 닿거나 진행할 수 없으면 
        return으로 돌아오고
    else: -> 벽에 안닿고 진행할 수 있으면
        if 다음칸이 목적지면
             count+=1
             return
        else:
            각 방향별로 진행할 수 있는 곳으로 진행한다

python은 pypy3으로 통과
"""
dir = [(0,1),(1,0), (1,1)]
count = 0

def pipe(r,c,d):


    global count
    if r == n-1 and c == n-1:
        count += 1
        return
    
    if d == 0:
        if c == n-1:
            return
        if 0 <= r < n and 0 <= c + 1 < n and grid[r][c+1] == 0:
            pipe(r, c+1, 0)
        if 0 <= r + 1 < n and 0 <= c + 1 < n and grid[r][c+1] == 0 and grid[r+1][c] == 0 and grid[r+1][c+1] == 0:
                pipe(r+1,c+1, 2)
        
        
    elif d == 1:
        if r == n-1:
            return
        if 0 <= r + 1 < n and 0 <= c < n and grid[r+1][c] == 0:
            pipe(r+1, c, 1)
        if 0 <= r + 1 < n and 0 <= c + 1 < n and grid[r][c+1] == 0 and grid[r+1][c] == 0 and grid[r+1][c+1] == 0:
            pipe(r+1,c+1, 2)
    
    elif d == 2:
        if 0 <= r < n and 0 <= c + 1 < n and grid[r][c+1] == 0:
            pipe(r, c+1, 0)
        if 0 <= r + 1 < n and 0 <= c < n and grid[r+1][c] == 0:            
            pipe(r+1, c, 1)
        if 0 <= r + 1 < n and 0 <= c + 1 < n and grid[r][c+1] == 0 and grid[r+1][c] == 0 and grid[r+1][c+1] == 0:
            pipe(r+1,c+1, 2)
    
       
    
        
        



n = int(input())
grid = []
for _ in range(n):
    grid.append(list(map(int, input().split())))


pipe(0,1,0)

print(count)


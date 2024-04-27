# https://www.acmicpc.net/problem/14719
'''
빗물 채우기
빗물이 고이는 규칙

    각 행에 대해 빗물을 가둘 수 있는 왼쪽 부분을 open, 
        빗물을 가둘 수 있는 오른쪽 부분을 close로 두어
        open close를 찾을 때 마다 해당 간격을 더해주면 각 층 별로 사이에 낀 칸 수를 계산할 수 있다
        따라서 층 별로 끼인 칸을 찾아서 더하는 방식으로 해결했다.

    is_open = false
    open_start = -1
    open_end = -1
    c = 0
    while c < n-1:
        if is_open == false and grid[r][c] == 1 and grid[r][c+1] == 0:
            is_open = true
            open_start = c
        elif is_open and grid[r][c+1] == 1:
            is_open = false
            open_end = c
            result += (open_end-open_start)
        c += 1

'''
import sys
input = sys.stdin.readline

# 입력
h, w = map(int, input().split())
heights = list(map(int, input().split()))
grid = [[0]*w for _ in range(h)]
max_h = -1
# grid 초기화
for i, block in enumerate(heights):
    max_h = max(block, max_h) # 불필요한 탐색을 위해 최대 높이를 구하고 이후 최대 높이 까지만 탐색
    for j in range(block):
        grid[j][i] = 1

# 각 높이별 탐색하며 갇힌 구간에 대한 길이 계산
result = 0
for r in range(max_h):
    is_open = False
    open_start = -1
    open_end = -1
    c = 0
    while c < w-1:
        if is_open == False and grid[r][c] == 1 and grid[r][c+1] == 0:
            is_open = True
            open_start = c
        elif is_open and grid[r][c+1] == 1:
            is_open = False
            open_end = c
            result += (open_end-open_start)
        c += 1

print(result)
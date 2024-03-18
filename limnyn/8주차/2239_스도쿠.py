# https://www.acmicpc.net/problem/2239

# 문제 출력 조건
# -> 사전식으로 출력하라

# 따라서 (r,c)에 대해 1~9 순서대로 넣어보며 
# 조건이 맞는지 백트래킹

# 조건
#     1. 현재 row에 대해 1~9까지 하나만 존재하는지 검사
#     2. 현재 col에 대해 1~9까지 하나만 존재하는지 검사
#     3. 현재 sector (3*3 집합)에 대해 1~9가 하나씩 존재하는지 검사

#     백트래킹 시 num이 가능한지 확인하고 가능하면 다음 재귀 진행
#     실패시 현재 좌표를 되돌려놓고 다음 숫자를 삽입해 검사 반복
    
                            
    

    

import sys
input = sys.stdin.readline
grid = [[0] * 9 for _ in range(9)]
empty_cells = []

for i in range(9):
    line = input().strip()
    for j in range(9):
        grid[i][j] = int(line[j])
        if grid[i][j] == 0:
            empty_cells.append((i, j))


def is_valid(num, r, c):
    # 행 검사
    for i in range(9):
        if grid[r][i] == num:
            return False
    
    # 열 검사
    for i in range(9):
        if grid[i][c] == num:
            return False
    
    # 3x3 섹터 검색
    sector_r = (r // 3) * 3
    sector_c = (c // 3) * 3
    for i in range(3):
        for j in range(3):
            if grid[sector_r + i][sector_c + j] == num:
                return False
    
    return True


def sudoku(idx):
    if idx == len(empty_cells):
        for row in grid:
            print(*row, sep="")
        sys.exit(0)
    
    r, c = empty_cells[idx]
    for num in range(1, 10):
        if is_valid(num, r, c):
            grid[r][c] = num
            sudoku(idx + 1)
            grid[r][c] = 0  #백트래킹


sudoku(0)

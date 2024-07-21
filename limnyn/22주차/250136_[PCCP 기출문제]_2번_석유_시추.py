# https://school.programmers.co.kr/learn/courses/30/lessons/250136
from collections import deque

visited = []
dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]
row = -1
col = -1

def bfs(r, c, land):
    area = 1
    visited[r][c] = True
    dq = deque([])
    dq.append((r, c))
    col_set = [c]
    while dq:
        r, c = dq.popleft()
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < row and 0 <= nc < col:
                if visited[nr][nc] == False and land[nr][nc] == 1:
                    area += 1
                    col_set.append(nc)
                    visited[nr][nc] = True
                    dq.append((nr, nc))
    return area, list(set(col_set))
    
def solution(land):
    '''
    1. bfs 탐색을 통해 구획별 사이즈를 측정한다.
    2. 탐색을 하고나서 해당 구획이 포함된 열 번호를 
        area 넓이 : (area를 포함하는 열) 형식으로 반환
    3. area를 포함하는 각 열에 해당 area 값을 더해준다
    4. 가장 많은 area를 포함하는 열 반환
    '''
    global visited, row, col
    row, col = len(land), len(land[0])
    visited = [[False] * col for _ in range(row)]
    col_sum_list = [0] * col
    for r in range(row):
        for c in range(col):
            if land[r][c] == 1 and visited[r][c] == 0:
                area, col_list = bfs(r, c, land)
                for col_num in col_list:
                    col_sum_list[col_num] += area                
    
    return max(col_sum_list)
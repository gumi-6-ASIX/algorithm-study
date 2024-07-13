# https://www.acmicpc.net/problem/17836
from collections import deque
import sys
def input(): return sys.stdin.readline().rstrip()

'''
bfs로 한번 탐색한다
result = min(출구까지의 최단거리, 검까지 최단거리 + 검에서 출구까지 거리)
result가 t 이하면 result 출력
'''
if __name__ == "__main__":
    n, m, t = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(n)]
    visited = [[-1] * m for _ in range(n)]

    dq = deque([[0 ,0, 1]])
    visited[0][0] = 0
    
    dr = [-1, 0, 1, 0]
    dc = [0, -1, 0, 1]
    sword_r, sword_c = -1, -1
    while dq:
        r, c, time = dq.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < n and 0 <= nc < m and visited[nr][nc] == -1:
                if grid[nr][nc] != 1:
                    visited[nr][nc] = time
                    dq.append((nr, nc, time + 1))
                    if grid[nr][nc] == 2:
                        sword_r, sword_c = nr, nc
    if visited[-1][-1] == -1:
        visited[-1][-1] = float('inf')
    result = min(visited[-1][-1], visited[sword_r][sword_c] + (n - sword_r - 1) + (m - sword_c - 1))

    if result > t:
        print("Fail")
    else:
        print(result)
                
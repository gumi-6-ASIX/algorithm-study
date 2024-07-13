# https://www.acmicpc.net/problem/1245
'''
bfs를 통해 인접한 cell과 차이가 1이하인 cell들을 탐색하여 group을 count
-> counting cells in blobs

1. r,c 탐색을 하며 주뱐이 모두 자신과 같거나 작은 좌표들을 찾는다
-> 봉우리 후보군을 찾는다
2. 가장 높은 수 부터 bfs탐색을 반복한다 
-> 최대 힙을 사용해서 가장 높은 봉우리 후보군부터 탐색을 시작한다.
'''
from collections import deque
import sys, heapq
def input(): return sys.stdin.readline().rstrip()

def bfs(r, c, group_num):

    visited[r][c] = group_num
    dq = deque([[r,c]])

    while dq:
        r, c = dq.popleft()
        for i in range(8):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < m and visited[nr][nc] == 0:
                if grid[r][c] >= grid[nr][nc] and grid[nr][nc] > 0:
                    visited[nr][nc] = group_num
                    dq.append([nr, nc])
    





if __name__ == "__main__":
    n, m = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(n)]
    visited = [[0] * m for _ in range(n)]


    dr = [-1, 0, 1, 0, -1, -1, 1, 1]
    dc = [0, -1, 0, 1, 1, -1, 1, -1]  
    # 봉우리 후보 탐색
    peaks = []
    for r in range(n):
        for c in range(m):
            if visited[r][c] == 0 and grid[r][c] > 0:
                is_peakable = True
                for i in range(8):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    if 0 <= nr < n and 0 <= nc < m:
                        if grid[nr][nc] > grid[r][c]:
                            is_peakable = False
                            break
                
                if is_peakable:
                    peeks = heapq.heappush(peaks, (-grid[r][c], r, c))
    
    group_num = 0
    while peaks:
        num, r, c = heapq.heappop(peaks)
        num *= -1
        if visited[r][c] == 0:
            group_num += 1
            bfs(r, c, group_num)

    print(group_num)


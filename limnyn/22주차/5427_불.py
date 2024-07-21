# https://www.acmicpc.net/problem/5427
'''
[문제]
    불이 붙어서 번져 나간다.
    불과 사람을 bfs 탐색하여 통과할 수 있는 지 없는 지 확인하는 문제

    while 사람이 이동할 곳이 없을때까지:
        불둘이 한 칸식 번진다
        사람들이 한 칸씩 번진다
        시간 += 1
    
    만약 사람이 탈출했다면 시간출력
    이외에는 impossible

'''
from collections import deque
import sys
def input(): return sys.stdin.readline().rstrip()


dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]

def bfs_fire():
    next_fire_deque = deque([])
    while fire_deque:
        r, c = fire_deque.popleft()
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < m: # 탈출에 성공한 경우
                if grid[nr][nc] == SPACE or grid[nr][nc] == HUMAN: # 불이 번질 수 있는 경우
                    next_fire_deque.append((nr, nc))
                    grid[nr][nc] = FIRE
    return next_fire_deque

        
def bfs_escape():
    global exit_spot_r, exit_spot_c
    next_human_deque = deque([])
    while human_deque:
        r, c = human_deque.popleft()
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if not (0 <= nr < n and 0 <= nc < m): # 탈출에 성공한 경우
                exit_spot_r, exit_spot_c = r, c
                return deque([])
            else: #내부인 경우
                if grid[nr][nc] == SPACE: # 불이 번질 수 있는 경우
                    next_human_deque.append((nr, nc))
                    grid[nr][nc] = FIRE
    return next_human_deque

if __name__ == "__main__":
    SPACE = 0
    WALL = -1
    FIRE = -2
    HUMAN = -3

    for t_c in range(int(input())):
        m, n = map(int, input().split())
        grid = []
        fire_deque = deque([])
        human_deque = deque([])
        visited = [[0] * m for _ in range(n)]

        for r in range(n):
            line = list(input())
            char_to_int_line = []
            for c in range(m):
                if line[c] == '#':
                    char_to_int_line.append(WALL)
                elif line[c] == '.':
                    char_to_int_line.append(SPACE)
                elif line[c] == '*':
                    char_to_int_line.append(FIRE)
                    fire_deque.append((r,c))
                elif line[c] == '@':
                    char_to_int_line.append(HUMAN)
                    human_deque.append((r,c))
            grid.append(char_to_int_line)

        time = 0
        exit_spot_r, exit_spot_c = -1, -1
        while human_deque: # 사람이 이동 가능한 동안
            fire_deque = bfs_fire() # 불이 먼저 번진다
            human_deque = bfs_escape() # 사람이 이동한다
            time += 1

        if exit_spot_r != -1 and exit_spot_c != -1: # 탈출을 했으면 종료
            print(time)
        else:
            print('IMPOSSIBLE')        



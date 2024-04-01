
# https://www.acmicpc.net/problem/15685
'''
드래곤 커브 g <= 10
1. 시작 할 때 드래곤 커브를 max(g)만큼 생성해 놓고, 
2. 방향별로 좌표를 돌려주는 함수를 생성하자

드래곤 커브 생성 함수
   
    next_generation()
    


def next_generation(curve, num_curve):

    "시계방향 90도 회전한다"
        해당 지점 좌표들에 대해 90도 회전
        
    축에 대해
        1. 현재 지점과 축에 대한 거리를 구한다
        
        2. 만약 현재 지점에 축에 대해 (현재지점 r,c_spot, 축 r,c_axis)
            r,c_next가 다음 좌표일 때

            r_spot, c_spot = spot

            r_s = r_spot - r_axis
            c_s = c_spot - c_axis
            r_next, c_next = 0, 0
            
            if r_s < 0 and c_s >= 0: # 1
                r_next = c_s + r_axis
                c_next = -r_s + c_axis
            elif r_s <= 0 and c_s < 0: # 2
                r_next = c_s + r_axis
                c_next = -r_s + c_axis
            elif r_s > 0 and c_s <= 0: # 3
                r_next = c_s + r_axis
                c_next = -r_s + c_axis
            elif r_s >= 0 and c_s > 0: # 4
                r_next = c_s + r_axis
                c_next = -r_s + c_axis

    축 변경은 이전 축 - 현재 축 두개를 사용하여 축 저장 및 초기화

'''
import sys
input = sys.stdin.readline
dir = [(0,1), (-1, 0), (0, -1), (1, 0)]


dr = [0, 1, 1]
dc = [1, 0, 1]

def next_generation():
    global axis_after, axis_now
    
    next_curve = []
    r_axis, c_axis = axis_now
    
    for i, spot in enumerate(dragon_curves):

        r_spot, c_spot = spot

        r_s = r_spot - r_axis
        c_s = c_spot - c_axis
        r_next, c_next = 0, 0
        
        if r_s < 0 and c_s >= 0: # 1
            r_next = c_s + r_axis
            c_next = -r_s + c_axis
        elif r_s <= 0 and c_s < 0: # 2
            r_next = c_s + r_axis
            c_next = -r_s + c_axis
        elif r_s > 0 and c_s <= 0: # 3
            r_next = c_s + r_axis
            c_next = -r_s + c_axis
        elif r_s >= 0 and c_s > 0: # 4
            r_next = c_s + r_axis
            c_next = -r_s + c_axis
        
        
        if not (r_next == 0 and c_next == 0):
            grid[r_next][c_next] = 1
            next_curve.append((r_next, c_next))

        if axis_before[0] == r_spot and axis_before[1] == c_spot:
            axis_after = [r_next, c_next]


    axis_now = axis_after

    dragon_curves.extend(next_curve)

result = 0
n = int(input())
grid = [[0] * 102 for _ in range(102)]


for i in range(n):
    dragon_curves = []
    c, r, d, g = map(int, input().split())
    axis_before = [r, c]
    grid[r][c] = 1
    grid[dir[d][0] + r][dir[d][1] + c] = 1
    dragon_curves.append((r, c))
    dragon_curves.append((dir[d][0] + r , dir[d][1] + c))

    axis_after = [dir[d][0] + r , dir[d][1] + c] 
    axis_now = axis_after[:]

    for _ in range(g):
        next_generation()


for r in range(100):
    for c in range(100):
        if grid[r][c] == 1:
            is_arounded = False
            for i in range(3):
                nr = r + dr[i]
                nc = c + dc[i]
                if 0 <= nr < 101 and 0 <= nc < 101:
                    if grid[r+dr[i]][c+dc[i]] == 0:
                        is_arounded = False
                        break
                    else:
                        is_arounded = True
            if is_arounded:
                result += 1
                
                    




print(result)
'''
2
3 3 0 1
4 2 2 1

1
4 2 1 3
'''
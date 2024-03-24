# https://www.acmicpc.net/problem/15683

"""
n,m 크기 1~8, 최대 8*8
cctv의 최대 개수는 8개를 넘지 않는다

cctv의 최대 방향 갯수 = 4
cctv 최대 배치 경우의 수 4^8 -> 65536 가지 경우

모든 경우에 대해 사각지대의 값을 충분히 시간 내에 찾을 수 있다

필요한 연산
    cctv방향의 경우 처리하기
    ->dfs연산으로 각 cctv에 대해 방향을 바꿔가면서 조합을 찾을 수 있을 것 같다

"""
import sys
sys.setrecursionlimit(10**5)
# dfs 시 재귀제한에 걸리지 않기 위해서
n, m = map(int, input().split())

grid = []
cam_spot = []
for row in range(n):
    line = list(map(int, input().split()))
    for col, num in enumerate(line):
        if 1<=num<=5:
            cam_spot.append((row,col, num))
    grid.append(line)


import copy
def calc_blind_spot(cam_dir_list):
    """
    cctv 방향에 대한 계산 처리
    """
    dr=[0,1,0,-1]
    dc=[1,0,-1,0]
    #계산하기 위해 깊은 참조로 임시 grid 생성
    grid_for_calc = copy.deepcopy(grid)
    for i in range(len(cam_spot)):
        # i번째 cctv의 종류와 좌표 구하기
        r, c, cam_num = cam_spot[i]
        cam_dir = cam_dir_list[i]

        # cctv 계산 처리
        if cam_num == 1:
            r1 = r
            c1 = c
            while True:
                r1 = r1 + dr[cam_dir]
                c1 = c1 + dc[cam_dir]
                if not (0<=r1<n and 0<=c1<m):
                    break
                if grid_for_calc[r1][c1] == 6:
                    break
                elif 1 <= grid_for_calc[r1][c1] <= 5:
                    continue
                grid_for_calc[r1][c1] = -1
        
        elif cam_num == 2:
            r1, r2 = r, r
            c1, c2 = c, c
            
            while True:
                r1 = r1 + dr[cam_dir]
                c1 = c1 + dc[cam_dir]
                if not (0<=r1<n and 0<=c1<m):
                    break
                if grid_for_calc[r1][c1] == 6:
                    break
                elif 1 <= grid_for_calc[r1][c1] <= 5:
                    continue
                grid_for_calc[r1][c1] = -1

            while True:
                r2 = r2 + dr[(cam_dir+2) % 4]
                c2 = c2 + dc[(cam_dir+2) % 4]
                if not (0<=r2<n and 0<=c2<m):
                    break
                if grid_for_calc[r2][c2] == 6:
                    break
                elif 1 <= grid_for_calc[r2][c2] <= 5:
                    continue
                grid_for_calc[r2][c2] = -1
            
        elif cam_num == 3:
            r1, r2 = r, r
            c1, c2 = c, c
            
            while True:
                r1 = r1 + dr[cam_dir]
                c1 = c1 + dc[cam_dir]
                if not (0<=r1<n and 0<=c1<m):
                    break
                if grid_for_calc[r1][c1] == 6:
                    break
                elif 1 <= grid_for_calc[r1][c1] <= 5:
                    continue
                grid_for_calc[r1][c1] = -1

            while True:
                r2 = r2 + dr[(cam_dir+1) % 4]
                c2 = c2 + dc[(cam_dir+1) % 4]
                if not (0<=r2<n and 0<=c2<m):
                    break
                if grid_for_calc[r2][c2] == 6:
                    break
                elif 1 <= grid_for_calc[r2][c2] <= 5:
                    continue
                grid_for_calc[r2][c2] = -1
            
        elif cam_num == 4:
            r1, r2, r3 = r, r, r
            c1, c2, c3 = c, c, c
            
            while True:
                r1 = r1 + dr[cam_dir]
                c1 = c1 + dc[cam_dir]
                if not (0<=r1<n and 0<=c1<m):
                    break
                if grid_for_calc[r1][c1] == 6:
                    break
                elif 1 <= grid_for_calc[r1][c1] <= 5:
                    continue
                grid_for_calc[r1][c1] = -1

            while True:
                r2 = r2 + dr[(cam_dir+1) % 4]
                c2 = c2 + dc[(cam_dir+1) % 4]
                if not (0<=r2<n and 0<=c2<m):
                    break
                if grid_for_calc[r2][c2] == 6:
                    break
                elif 1 <= grid_for_calc[r2][c2] <= 5:
                    continue
                grid_for_calc[r2][c2] = -1
            
            while True:
                r3 = r3 + dr[(cam_dir+3) % 4]
                c3 = c3 + dc[(cam_dir+3) % 4]
                if not (0<=r3<n and 0<=c3<m):
                    break
                if grid_for_calc[r3][c3] == 6:
                    break
                elif 1 <= grid_for_calc[r3][c3] <= 5:
                    continue
                grid_for_calc[r3][c3] = -1

        elif cam_num == 5:
            start_r = r
            start_c = c
            for i in range(4):
                r1 = start_r
                c1 = start_c
                while True:
                    r1 = r1 + dr[(cam_dir+i)%4]
                    c1 = c1 + dc[(cam_dir+i)%4]
                    if not (0<=r1<n and 0<=c1<m):
                        break
                    if grid_for_calc[r1][c1] == 6:
                        break
                    elif 1 <= grid_for_calc[r1][c1] <= 5:
                        continue
                    grid_for_calc[r1][c1] = -1

    safe_area = n*m
    for i in range(n):
        for j in range(m):
            if grid_for_calc[i][j] != 0:
                safe_area -= 1
        
    return safe_area

#cam_len = 전체 카메라 갯수
cam_len = len(cam_spot)
result = 1e9
def dfs(cam_dir_list):
    global result
    cam_dir_len = len(cam_dir_list)
    last_cam_num = cam_spot[cam_dir_len-1][2]
    if cam_dir_len == cam_len:
        result = min(result, calc_blind_spot(cam_dir_list))

    else:

        for i in range(4):

            cam_dir_list.append(i)
            dfs(cam_dir_list)
            cam_dir_list.pop()

result = n*m
if cam_len == 0:
    for i in range(n):
        for j in range(m):
            if grid[i][j] != 0:
                result -= 1
    print(result)
    exit()
dfs([])
print(result)



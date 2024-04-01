# https://www.acmicpc.net/problem/17472

'''
1 <= N , M <= 10
3 <= N x M <= 100
2 <= 섬의 개수 <= 6


섬을 노드라고 할 때

문제의 요구사항
    -> "모든 노드가 연결되어 있을 때 최소 간선 길이합"
    따라서 MST를 생각해 볼 수 있다.

    MST를 구성하되 다리 길이가 2 이상이 되게 하면 가능 하다

1. 섬들이 모두 1로 되어있기 떄문에 섬에 대해 번호를 붙여 주기 및 거리 저장할 자료구조 생성
    
    # 섬에서 섬 까지 거리 저장하는 자료구조 -> 섬의 개수가 작기 때문에 2차원 배열로 가능하다
    
    섬들을 구분하기위해 번호를 붙여준다
    bfs탐색을하면서 섬에 대해 번호를 붙여줄 수 있다.

2. 섬에서 섬 까지 거리를 구하는 경우를 생각해 보자




    거리는 직선으로만 뻗어야 하고 길이가 2 이상이어야 한다.
    따라서 각 모서리 좌표별로 직선을 뻗어서 다른 섬에 도달할 때
    해당 섬의 길이가 현재 까지 중 최소이면서 2 이상이라면 
    섬_start - 섬_end 거리를 갱신하여 섬 간 거리의 최소를 구한다.



3. MST - > 1번 섬에서 부터 집합에 넣고 출발한다.
    
    while heap:
        그룹에 속한 node들에 대해서 가장 가까운 섬을 구하고
        그 섬에 대해 그룹에 넣고 반복한다

        if 집합의 크기가 섬의 개수 만큼 된다면,  다리의 길이를 리턴한다.
    else: return -1


'''
import sys, heapq
input = sys.stdin.readline
from collections import deque
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

island_count = 1

# 함수 1 : 1로 이루어진 섬에 대해 섬 번호 부여 및 섬 갯수 갱신
def set_island_number(r, c):
    global island_count
    if grid[r][c] == 0 or visited_for_set_island_number[r][c] == True:
        return
    
    dq = deque([])
    visited_for_set_island_number[r][c] = True
    grid[r][c] = island_count
    dq.append((r, c))

    while dq:
        r, c = dq.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < n and 0 <= nc < m and grid[nr][nc] == 1:
                if visited_for_set_island_number[nr][nc] == False:
                    visited_for_set_island_number[nr][nc] = True
                    grid[nr][nc] = island_count
                    dq.append((nr, nc))
    island_count += 1

    return

# 함수 2 : 각 섬 별 거리를 계산하는 함수
def calc_nearlest_distance(r, c):
    start_island_num = grid[r][c]
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        bridge_distance = 0
        while 0 <= nr < n and 0 <= nc < m:
            # 다음 칸에 대해 자기 자신이 아닐 때 까지 진행
            if grid[nr][nc] == start_island_num:
                break
            
            # 바다 일 때 다리길이 추가
            elif grid[nr][nc] == 0:
                bridge_distance += 1
            
            # 다른 섬에 닿았을 때
            else:
                arrived_island_num = grid[nr][nc]

                if distance_between_islands[start_island_num-1][arrived_island_num-1] > bridge_distance and bridge_distance > 1:
                    distance_between_islands[start_island_num-1][arrived_island_num-1] = bridge_distance
                    distance_between_islands[arrived_island_num-1][start_island_num-1] = bridge_distance
                break

            nr += dr[i]
            nc += dc[i]


# 함수 3 : MST를 생성하는 함수
def make_mst():
    bridge_length = 0
    
    min_heap = []
    visited = [False] * island_count
    group_cnt = 0
    heapq.heappush(min_heap, (0, 0)) # 0번 섬까지 거리 0

    while min_heap:
        dist, end = heapq.heappop(min_heap)

        if visited[end] == False:
            visited[end] = True
            group_cnt += 1
            bridge_length += dist
            for next in range(island_count):
                
                if visited[next] == False:
                    if distance_between_islands[end][next] <= 1000:
                        heapq.heappush(min_heap, (distance_between_islands[end][next], next))
        
        if island_count == group_cnt:
            return bridge_length

    return -1
            


# 입력
n, m = map(int,input().split())

visited_for_set_island_number = [[False] * m for _ in range(n)]
grid = [list(map(int,input().split())) for _ in range(n)]
# 1. 섬 마다 번호 부여
for i in range(n):
    for j in range(m):
        set_island_number(i, j)
    
island_count -= 1 # 섬 번호 - 1

# 2. 섬에 대한 거리를 저장하기 위한 2차원 배열
distance_between_islands = [[1001]*island_count for _ in range(island_count)]
for i in range(n):
    for j in range(m):
        if grid[i][j] != 0:
            calc_nearlest_distance(i, j) # 섬 간 최단 거리 계산

# 3. 최단 거리 2차원 배열을 이용해 MST 생성하기
result = make_mst()
print(result)

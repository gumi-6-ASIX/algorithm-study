# https://www.acmicpc.net/submit/17835/75923985
'''
도시의 수 n
    2 <= n <= 100,000
면접장의 수 k
    도시의 개수보다 같거나 적다.

입력
    n, m, k

    for _ in range(m):
        u(출발) v(도착) c(가중치)

    면접장들


각 도시에 대해
    '면접장에 도달하는 가장 가까운 거리'를 구하고
    가장 가까운 거리 중 가장 큰 값을 구하고
    만약 값이 같으면 가장 작은 번호를 출력하면 된다.

    면접장에서부터 모든 노드에 대해 가장 가까운 번호를 구하면되는데
    면접장이 여러 개 일 수 있으니

    다익스트라를 '시작'할때 '모든 면접장 위치'를 그룹에 넣고 구하면 
    면접장에 대해 가장 가까운 위치가 나올 것 같다.


    문제 키 포인트
        - > [시간 초과를 막기 위해 '죽은 노드 처리'가 필요]

'''
# https://www.acmicpc.net/problem/17835
import sys, heapq
input = sys.stdin.readline
INF = float('inf')

def djikstar():
    group = [False] * n
    cost = [INF]*n
    heap = []
    
    group_count = 0
    for room in interview_rooms:
        cost[room] = 0
        heapq.heappush(heap, (0,room))
        
    
    while heap:
        current_cost, current_node = heapq.heappop(heap)
        
        # '죽은 노드 처리'
        # 다익스트라는 음의 가중치가 없다. 
        # 따라서 힙에서 꺼낸 노드가 지금까지의 최단 거리보다 크면 
        # 그 인접노드도 이미 cost가 더 커져서 힙에 넣을 필요가 없다.
        if current_cost > cost[current_node]:
            continue

        group[current_node] = True
        for next_node, edge_cost in graph[current_node]:
            if not group[next_node]:
                new_cost = current_cost + edge_cost
                if new_cost < cost[next_node]:
                    cost[next_node] = new_cost
                    heapq.heappush(heap, (new_cost, next_node))
        

    maxdist = -1
    interview_num = -1
    for i, v in enumerate(cost):
        if maxdist < v:
            maxdist = v
            interview_num = i + 1
    print(interview_num)
    print(maxdist)
                
if __name__ == "__main__":
    n, m, k = map(int, input().split())
    graph = [[] for _ in range(n)]
    for _ in range(m):
        u, v, c = map(int, input().split())
        graph[v-1].append((u-1, c))
    interview_rooms = [x-1 for x in list(map(int, input().split()))]
    
    

djikstar()



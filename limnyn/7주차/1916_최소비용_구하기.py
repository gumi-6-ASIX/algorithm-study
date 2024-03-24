# https://www.acmicpc.net/problem/1916


'''
문제에서 물어보는 것
    : 출발점 -> 도착점 까지 최소 비용
    
    그래프 문제에서의 단일 출발점에서 최소 비용
    => 다익스트라 문제
    힙을 사용해 구현하면 VlogE시간에 구현가능하다

'''

'''
다익스트라
    현재 출발점의 집합 A에 대해
    집합A의 여집합 B 중에서
    A와 가장 가까운 노드에 대해 (이 때 최소 힙을 사용할 수 있다)
    기존의 거리값과 비교해 가장 가까운 값으로 초기화 하고 집합에 포함한다
    
'''
n = int(input())
m = int(input())
graph = [[] for _ in range(n)]
INF = 1e9
distance = [INF]*n
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a - 1].append([b - 1, c])
start, end = map(int, input().split())
start -= 1
end -= 1


import heapq
def dijkstra(start):
    q = []
    distance[start] = 0
    heapq.heappush(q, (0, start))
    
    while q:
        dist, now  = heapq.heappop(q)
        
        if distance[now] < dist:
            continue

        for node in graph[now]:
            cost = dist + node[1]
            
            if cost < distance[node[0]]:
                distance[node[0]] = cost
                heapq.heappush(q,(cost, node[0]))
                
dijkstra(start)
print(distance[end])




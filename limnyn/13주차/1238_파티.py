# https://www.acmicpc.net/problem/1238

'''
N개의 노드, M개의 간선
1 <= N <= 1000 
1 <= M <= 10000
한 점에서 다른 점 까지의 최단 거리 - 다익스트라

다익스트라 알고리즘을 통해서 특정 점에서 모든 점 까지의 최단 거리를 구할 수 있어

1. X에서 각 모든 집을 방문하는 경우 (파티가 끝나고 돌아가는 경우)
    -> 다익스트라로 해결
        party_start graph 생성
2. 모든 집에서 X로 오는 경우
    -> 그래프 간선이 단방향이므로 출발 도착지를 반대로 입력받아서 다익스트라
        party_end graph 생성

'''
import sys, heapq
input = sys.stdin.readline


def dijkstra(start, graph):
    n = len(graph)
    shortest_time = [float('inf')] * n
    shortest_time[start] = 0

    hq = []
    heapq.heappush(hq, [0, start])

    while hq:
        time, destination = heapq.heappop(hq)
        if shortest_time[destination] < time:
            continue
        for dest, t in graph[destination]:
            new_time = time + t
            if new_time < shortest_time[dest]:
                shortest_time[dest] = new_time
                heapq.heappush(hq, [new_time, dest])

    return shortest_time



if __name__ == "__main__":
    n, m, x = map(int, input().split())
    party_start = [[] for _ in range(n)]
    party_end = [[] for _ in range(n)]
    for i in range(m):
        start, end, time = map(int, input().split())
        start -= 1
        end -= 1
    
        party_start[start].append([end, time])
        party_end[end].append([start, time])

    start_times = dijkstra(x-1, party_start)
    end_times = dijkstra(x-1, party_end)
    max_time = 0
    for i in range(n):
        if start_times[i] + end_times[i] > max_time:
            max_time = start_times[i] + end_times[i]
        
    print(max_time)


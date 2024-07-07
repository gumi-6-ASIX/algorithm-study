# https://www.acmicpc.net/problem/10159
'''
[문제]
각 노드에 대해 우선순위가 확실한 결과가 아닌 것들의 개수를 출력

[입력]
    물건의 개수 5 <= N < 100
    물건 쌍의 개수 0 <= M <= 2000

각 노드에 대해 bfs 탐색하며 그래프 연결된 것은 우선순위가 정해진 것들이다.
bfs를 통해 visited한 노드를 count 해서 방문안한 노드들의 갯수를 구하자.
'''
from collections import deque
import sys
def input(): return sys.stdin.readline().rstrip()

def bfs_checker(start):
    dq = deque([start])
    while dq:
        node = dq.popleft()
        grid[start][node] = 1
        grid[node][start] = 1
        for next in graph[node]:
            if grid[start][next] == 0:
                dq.append(next)
    
if __name__ == "__main__":
    n = int(input())
    m = int(input())
    grid = [[0] * (n + 1) for _ in range(n + 1)]
    graph = [[] for _ in range(n + 1)]
    
    for _ in range(m):
        s, e = map(int, input().split())
        graph[s].append(e)

    for i in range(1, n+1):
        bfs_checker(i)

    for g in grid[1:]:
        print(n - sum(g))
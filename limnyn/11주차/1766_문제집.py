# https://www.acmicpc.net/problem/1766
'''
순서가 주어졌을 떄 경우를 출력하는 문제
-> 위상정렬 문제

위상정렬에 대한 구현
    노드의 in 간선에 대해서 일차원 count 리스트를 만든다
    

    result = [] 
    in 간선이 0개인 그래프를 queue에 넣는다
    while queue:

        node = queue.popleft()
        result.append(node)
        for next_node in node: #인접한 노드들에 대해
            count_list[next_node] -= 1 # 해당 노드들의 in 간선을 없앤다

            if count_list[next_node] == 0: # 해당 노드에 더이상 간선이 없다면 큐에 넣는다.
                queue.append(next_node)
        
                

'''

import sys, heapq
input = sys.stdin.readline
n, m = map(int, input().split())

prob = [[] for _ in range(n)]
count_list = [0]*n
for _ in range(m):
    start, end = map(int, input().split())
    start -= 1
    end -= 1
    prob[start].append(end)
    count_list[end] += 1


result = []    
q = []
for i in range(n):
    if count_list[i] == 0:
        heapq.heappush(q, i)

while q:
    node = heapq.heappop(q)
    result.append(node+1)
    
    for next_node in prob[node]:
        count_list[next_node] -= 1
        if count_list[next_node] == 0:
            heapq.heappush(q, next_node)

for r in result:
    print(r, end=' ')
print()
# https://www.acmicpc.net/problem/12851
'''
bfs를 통해 먼저 방문한 것 출력
중복 방문을 제외하기 위해 visisted 사용,
특정 지점까지 첫 방문한 cnt와 동일한 cnt만 경우의수에 포함
'''
import sys
input = sys.stdin.readline
n, k = map(int, input().split())
visited = [-1] * 200002
from collections import deque

dq = deque([])
dq.append((n,0))
result = -1
result_count = 0
visited[n] = 0

# n == k 일때 처리
if n == k:
    print(0)
    print(1)
else:
    while dq:
        spot, cnt = dq.popleft()
        
        if result != -1:
            if result < cnt:
                break 
        
        next = spot + 1
        if -100000 <= next <= 100000:
            if next == k:
                if result == -1:
                    result = cnt + 1
                    result_count = 1
                elif cnt + 1 == result:
                    result_count += 1
            
            if visited[next-100000] == -1:
                visited[next-100000] = cnt + 1
                dq.append((next, cnt+1))
            elif visited[next-100000] == cnt + 1:
                dq.append((next, cnt+1))
        
        next = spot - 1
        if -100000 <= next <= 100000:
            if next == k:
                if result == -1:
                    result = cnt + 1
                    result_count = 1
                elif cnt + 1 == result:
                    result_count += 1
            
            if visited[next-100000] == -1:
                visited[next-100000] = cnt + 1
                dq.append((next, cnt+1))
            elif visited[next-100000] == cnt + 1:
                dq.append((next, cnt+1))
        
        next = spot * 2
        if -100000 <= next <= 100000:
            if next == k:
                if result == -1:
                    result = cnt + 1
                    result_count = 1
                elif cnt + 1 == result:
                    result_count += 1
            
            if visited[next-100000] == -1:
                visited[next-100000] = cnt + 1
                dq.append((next, cnt+1))
            elif visited[next-100000] == cnt + 1:
                dq.append((next, cnt+1))
        

    print(result)
    print(result_count)
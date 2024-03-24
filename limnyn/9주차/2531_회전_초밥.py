# https://www.acmicpc.net/problem/2531
'''
0부터 k칸 씩 sliding을 할 것이다.

원형 회전에 대한 처리는?
end 에 대해서 index를 idx%n 로 처리해주면 초과해도 상관 없다.

k칸에 대해 deque를 사용하면 될 거 같다.

1. 4칸에 대해 서로 다른 것의 갯수를 계산한다.

2. if 만약 4칸 내부에 쿠폰이 없을 때
    쿠폰을 사용해 +1 한다.

3. 최댓값을 갱신한다


'''
import sys
input = sys.stdin.readline

#입력
n, d, k, c= map(int, input().split())
sushi = [int(input()) for _ in range(n)]


#초기화
visited = [0]*3001
from collections import deque
dq= deque([])
count = 0
result = 0

#k개 까지 채워본 후 갯수 세보기
for i in range(k):
    if visited[sushi[i]] == 0:
        count += 1    
    dq.append(sushi[i])
    visited[sushi[i]] += 1
    
if visited[c] == 0:
    result = max(result, count + 1)
else:
    result = max(result, count)

#k개 채운 이후부터 한 칸 씩 밀어가며 실행
for i in range(k, n + k - 1):
    left = dq.popleft()
    if visited[left] > 0:
        visited[left] -= 1
        if visited[left] == 0:
            count -= 1
    
    idx = i % n
    
    if visited[sushi[idx]] == 0:
        count += 1
    visited[sushi[idx]] += 1
    dq.append(sushi[idx])

    if visited[c] == 0:
        result = max(result, count + 1)
    else:
        result = max(result, count)

    
    

print(result)



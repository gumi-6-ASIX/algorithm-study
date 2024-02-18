# https://www.acmicpc.net/problem/11000
"""
 -readline 안쓰고 input 쓰면 시파이썬은 시간초과 난다-
 -입력갯수 길 때는 주의하기-
가장 빨리 끝나는 시간 중에 가장 늦게 시작하는것만 출력하면 되는 문제

200,000. nlogn으로 정렬 후 계산

"모든 강의"를 수행할 때 필요한 "강의실 수"

알고리즘
    다음 강의시간에 대해
    현재 강의장 중 가장 먼저 끝나는 강의실을 우선적으로 배당한다

1~3
2~4
3~5

1~3
    강의실 끝나는 시간 : [3]
2~4
    강의실 끝나는 시간 : [3]
        시작시간 2가 끝나는 시간 3보다 빠르다
        강의실 추가 배정, 끝나는 시간 삽입 : [3, 4]
3~5
    강의실 끝나는 시간 : [3, 4]
        가장 빨리 끝나는 시간 3 <= 시작 시간 3
        강의실 이어서 사용, 끝나는 시간 갱신
        -> [4, 5]

강의실 별 끝나는 시간 리스트 [4, 5] 의 길이 가 강의실 배정 수
"""
import sys
import heapq
input = sys.stdin.readline
n = int(input())
time_table = []
for i in range(n):
    start, end = map(int, input().split())
    time_table.append((start, end))
time_table.sort()
q = []
heapq.heappush(q, time_table[0][1])

for i in range(1, n):
    
    s, e = time_table[i]
    
    if s >= q[0]:
        heapq.heappop(q)
    heapq.heappush(q, e)

print(len(q))
    

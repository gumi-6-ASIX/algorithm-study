
import sys
input = sys.stdin.readline
# https://www.acmicpc.net/problem/16472
"""
투포인터 문제
"""
import sys
input = sys.stdin.readline
dict = {}
for i in range(26):
    dict[chr(i+97)] = 0

n = int(input())
line = input().strip()



start, end = 0, 0
result = 0
count = 0
while start < len(line) and end < len(line):

    if dict[line[end]] == 0:
        count += 1

    dict[line[end]] += 1
    
    
    while count > n:
        dict[line[start]]-= 1
        if dict[line[start]] == 0:
            count -= 1
        start += 1
    
    result = max(end - start + 1, result)
    
    end += 1
print(result)



# 처음에 deque로 접근을 했었는데 순서에 예외가 생기는 경우가 발생했다.
# 따라서 큐를 사용해서 풀기 어려운 문제다
# 딕셔너리를 써서 풀자.
'''
from collections import deque
n = int(input())
line = input().strip()

chars = deque([])


start, end = 0, 0
count = 0
while start < len(line) and end < len(line):

        
    if line[end] not in chars:
        chars.append(line[end])
    
    while len(chars) > n:
        left_c = chars.popleft()
        tmp = start
        find_idx = 0
        
        for i in range(end,start-1,-1):
            if line[i] == left_c:
                find_idx = i
                break
        start = find_idx + 1
    count = max(end-start+1, count)        
    end += 1
    


print(count)

'''


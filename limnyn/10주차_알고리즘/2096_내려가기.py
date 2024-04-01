'''
RGB 거리 문제와 유사한 DP 문제
'''

# https://www.acmicpc.net/problem/2096

import sys
input = sys.stdin.readline
n = int(input())


dp_min = [0] * (n)
dp_max = [0] * (n)


grid = list(map(int, input().split()))
dp_min = grid
dp_max = grid
for r in range(1, n):
    grid = list(map(int, input().split()))
    next_dp_max = [0] * 3
    next_dp_min = [0] * 3
    for c in range(3):
        if c == 0:
            next_dp_min[c] = min(dp_min[0], dp_min[1]) + grid[c]
            next_dp_max[c] = max(dp_max[0], dp_max[1]) + grid[c]
        
        elif c == 1:
            next_dp_min[c] = min(dp_min[0], dp_min[1], dp_min[2]) + grid[c]
            next_dp_max[c] = max(dp_max[0], dp_max[1], dp_max[2]) + grid[c]
        
        elif c == 2:
            next_dp_min[c] = min(dp_min[1], dp_min[2]) + grid[c]
            next_dp_max[c] = max(dp_max[1], dp_max[2]) + grid[c]
    
    dp_max = next_dp_max
    dp_min = next_dp_min

print(max(dp_max), min(dp_min))


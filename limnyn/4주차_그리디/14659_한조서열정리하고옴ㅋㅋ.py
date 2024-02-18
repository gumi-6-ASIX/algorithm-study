# https://www.acmicpc.net/problem/1783


n = int(input())

mountains = list(map(int, input().split()))

max_height, max_count = 0, 0
count = 0
for height in mountains:
    if  height > max_height:
        max_height = max(height,max_height)
        count = 0
    else:
        count += 1
        max_count = max(count, max_count)

print(max_count)
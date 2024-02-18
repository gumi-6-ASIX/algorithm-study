# https://www.acmicpc.net/problem/1783

"""
n이 짧은 면 일 때
if n == 1:
    return 1

if n == 2:
    if m < 8:
        return (m+1)//2
    else:
        print(4)

if n >= 3:
    if m <= 4:
        return m
    elif m <= 6:
        return 4
    else:
        return 4 + (m - 6)
"""

n, m = map(int, input().split())


if n == 1:
    print(1)

if n == 2:
    if m < 8:
        print((m+1)//2)
    else:
        print(4)

if n >= 3:
    if m <= 4:
        print(m)
    elif m < 6:
        print(4)
    else:
        print(4+(m-6))



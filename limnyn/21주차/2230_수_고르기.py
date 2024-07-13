# https://www.acmicpc.net/problem/2230
'''
[문제]
    N개의 정수로 이루어진 수열에서
    두 수를 골랐을 때 그 차이가M 이상이면서 제일 작은 경우를 구하는 프로그램
[제한]
    1 ≤ N ≤ 100,000
    0 ≤ M ≤ 2,000,000,000
    0 ≤ |A[i]| ≤ 1,000,000,000
[입력]
    첫째 줄에 두 정수 N, M이 주어진다. 다음 N개의 줄에는 차례로 A[1], A[2], …, A[N]이 주어진다.
'''
import sys
def input(): return sys.stdin.readline().rstrip()

if __name__ == "__main__":
    n, m = map(int, input().split())
    arr = sorted([int(input()) for _ in range(n)])

    left, right = 0, 1
    result = float('inf')

    while right < n:
        diff = arr[right] - arr[left]
        if diff >= m:
            result = min(result, diff)
            left += 1
        else:
            right += 1

        if left == right:
            right += 1

    print(result)
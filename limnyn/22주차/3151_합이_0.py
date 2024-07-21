# https://www.acmicpc.net/problem/3151
'''
[문제]
세 수의 합이 0이 되는 모든 경우의 수를 출력하기

[제한]
1 ≤ N ≤ 10000
-10000 ≤ Ai ≤ 10000

[접근]
수열을 정렬 이후 수를 하나 고정해 놓고 투포인터로 탐색한다고 했을 때
10000 * 10000 = 100,000,000번 탐색
-> 시간 제한 4초안에 가능할 것 같다.

포인터를 세개 사용해서
base, start, end를 지정하고
base, start, end = 0, 1, n - 1 -> 1~n-1에서 투포인터
base, start, end = 1, 2, n - 1 -> 2~n-1에서 투포인터
이런 순으로 탐색하면 모든 경우에서 탐색이 가능할 것 같다.
-> 시간초과

중복에 대한 처리를 줄여아 한다.

-1 -1 -1 -1 1 1 1 1 인 경우에 대해

합이 0인 경우에
    1. start, end의 값이 같은 경우
        -> start, end 사이의 값들도 다 동일한 값(정렬되어 있기 때문에)
        해당 구간의 조합을 구한다
    2. start, end의 값이 다른 경우
        start의 오른쪽에 같은 값이 몇개 있는지 계산
        end의 왼쪽에 같은 값이 몇개 있는지 계산
        start 갯수 * end 갯수로 조합 계산
    해당 경우를 통해 중복 연산을 제외해야 한다
'''
import sys
def input(): return sys.stdin.readline().rstrip()

if __name__ == "__main__":
    n = int(input())
    arr = sorted(list(map(int, input().split())))
    if n < 2:
        print(0)
        exit()

    result = 0
    base, start, end = 0, 1, n - 1
    while base < n-2: # base는 고정하고 base 이후의 값들 중에서 투포인터를 탐색한다
        while start < end:
            sum_num = arr[base] + arr[start] + arr[end]

            if sum_num == 0: # 0인 경우에
                
                if arr[start] == arr[end]: # start와 end가 같은 값일 때는 정렬 되어 있기 떄문의 그 사이의 수도 동일한 수다
                    result += (end - start + 1) * (end - start) // 2 # (end - start + 1) C 2 가지 경우가 존재
                    break
                
                else: # start의 오른쪽에 start와 같은 수가 몇개 존재하는지 찾는다
                    start_count = 1
                    end_count = 1
                    while start + 1 < end and arr[start + 1] == arr[start]:
                        start_count += 1
                        start += 1
                    while end - 1 > start and arr[end - 1] == arr[end]:
                        end_count += 1
                        end -= 1
                    result += start_count * end_count
                    start += 1
                    end -= 1
            elif sum_num < 0:
                start += 1
            elif sum_num > 0:
                end -= 1

        base += 1
        start, end = base + 1, n - 1
    
    print(result)


                
            



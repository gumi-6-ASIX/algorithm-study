# https://www.acmicpc.net/problem/2473
'''
양 끝을 1, 2번째 점이라고 할 때
3번째 점을 1부터 n-1까지 이동하며 탐색해본다.

투 포인터에 대한 포인터 이동 규칙을 설정해 보자


def meld(mid):
    meld_result = float('inf')
    left, right = 0, n-1
    r_l, r_m, r_r = left, mid, right

    while left < mid and mid < right: 
        mixture = solution[left]+solution[right] + solution[mid]
        
        if abs(mixture) < meld_result:
            meld_result = abs(mixture)
            r_l = left
            r_r = right
            

        if mixture == 0:
            return r_l, mid, r_r, meld_result

        elif mixture < 0:
            left += 1
        elif mixture > 0:
            right -= 1

    return r_l, r_m, r_r, meld_result

'''

import sys
input = sys.stdin.readline


def meld(mid):
    meld_result = float('inf')
    left, right = 0, n-1
    r_l, r_m, r_r = left, mid, right

    while left < mid and mid < right: 
        mixture = solution[left]+solution[right] + solution[mid]
        
        if abs(mixture) < meld_result:
            meld_result = abs(mixture)
            r_l = left
            r_r = right
            

        if mixture == 0:
            return r_l, mid, r_r, meld_result

        elif mixture < 0:
            left += 1
        elif mixture > 0:
            right -= 1

    return r_l, r_m, r_r, meld_result

if __name__ == "__main__":
    n = int(input())
    solution = sorted(list(map(int,input().split())))
    result_left, result_mid, result_right = -1, -1, -1
    meld_result = float('inf')
    
    for i in range(1, n-1):
        r_l, r_m, r_r, r_meld = meld(i)
        
        if r_meld <= meld_result:
            meld_result = r_meld
            result_left = r_l
            result_mid = r_m
            result_right = r_r
    print(solution[result_left], solution[result_mid], solution[result_right])
# https://www.acmicpc.net/problem/2467
'''
특성값 : 두 용액의 특성값의 합

문제 요구 사항
    "두 수의 합 중 가장 작은 값"

접근
    완전탐색
        100,000 C 2 -> 시간 초과
        따라서 다른 방법을 사용해야 한다
    
    투 포인터
        양 끝에서 시작해서 
            만약 
                두개의 합이 0보다 작다면 음수 크기를 줄여야 함으로 start += 1
                두개의 합이 0보다 크다면 양수 크기를 줄여야 함으로 end -= 1

        1. 용액들을 정렬한다
            [-99, -2, -1, 4, 98]
        2. start, end에 대해서 투포인터 이동
            
            start, end = 0, n-1                
            min_character = abs(solution[start] + solution[end])
            min_start, min_end = solution[start] , solution[end]

            while start < end:
                character = solution[start] + solution[end]
                if min_character > abs(character):
                    min_character = abs(character)
                    min_start = solution[start]
                    min_end = solution[end]

                if character < 0:
                    start += 1
                elif character > 0:
                    end -= 1
                else:
                    break
'''

import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    solution = list(map(int, input().split()))

    start, end = 0, n-1                
    min_character = abs(solution[start] + solution[end])
    min_start, min_end = solution[start] , solution[end]

    while start < end:
        character = solution[start] + solution[end]
        if min_character > abs(character):
            min_character = abs(character)
            min_start = solution[start]
            min_end = solution[end]

        if character < 0:
            start += 1
        elif character > 0:
            end -= 1
        else:
            break

    print(min_start, min_end)
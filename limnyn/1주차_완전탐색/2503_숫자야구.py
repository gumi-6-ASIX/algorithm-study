# https://www.acmicpc.net/problem/2503

n = int(input())
line = [list(map(int, input().split())) for _ in range(n)]

'''
알고리즘 생각하기

완전탐색하기
모든 수에 대해 통과하는지 확인해보기

'''

from itertools import permutations
nums = list(permutations(['1','2','3','4','5','6','7','8','9'], 3))

result = []

def baseball(num):
    for quest, strike, ball in line:
        strike_cnt, ball_cnt = 0, 0
        question = [x for x in str(quest)]
        for i in range(3):
            if question[i] == num[i]:
                strike_cnt += 1
            elif question[i] in num:
                ball_cnt += 1
        if strike_cnt != strike or ball_cnt != ball:
            return False
    return True

for num in nums:
    if baseball(num) :
        result.append(num)

print(len(result))

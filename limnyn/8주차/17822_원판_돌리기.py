# https://www.acmicpc.net/problem/17822

# 원형에 대한 구현
#     -> deque를 사용하자
    
#     deque는 rotate함수가 있다.
#     deque.rotate(x)
#     x가 양수면 시계방향 x 칸 회전
#     x가 음수면 반시계방향 x 칸 회전
    
# 원판 회전 규칙
#     1. 번호가 xi의 배수인 원판을 k 칸 회전한다
#         if di == 0:
#             시계방향 k 칸 회전
#         else:
#             반시계방향 k 칸 회전
    
#     2. if 원판에 수가 남아있다 
        
#         2-1. 인접하면서 수가 같은 것을 모두 찾는다
#             next_delete_spot = []
            
#             for i in range(n):
#                 for j in range(m):
                    
#                     if 자신의 [좌 or 우 or 상 or 하] 와 같다면
#                         next_delete_spot.append( [i, j] )

#         2-2
#             if 지울 숫자가 있다면
#                 for r, c in next_delete_spot:
#                     circles[r][c] = 0
        
#             else 지울 숫자가 없다면
            
#             원판에 있는 숫자의 평균을 구하고
#                 -> (인접한 수 찾을 때 0이 아닌 값 계산으로 구하기)
            
#             if circles[i][j] > 평균:
#                 circles[i][j] -= 1
#             elif circles[i][j] < 평균:
#                 circles[i][j] += 1

# k번 원판을 회전하여 값을 구한다.
            
            
            
                        
                        
# rotate 연습
# dq =  deque([1,1,2,3])
# print(dq)
# dq.rotate(-1)
# print(dq)
# dq[2] = 0
# print(dq)
# print(dq.rotate(1))
# print(dq)
    


from collections import deque

import sys
input= sys.stdin.readline


def rotate_circle(x,d,k):
    if d == 1:
        k = -1*k
    for i in range(n):
        if (i + 1) % x == 0:
            circles[i].rotate(k)
    
    next_delete_spot = []
    sum_for_avg = 0
    count_for_avg = 0
    for c_num in range(n):
        for idx in range(m):
            spot_num = circles[c_num][idx]
            if spot_num != 0:
                neighbor = [
                    circles[c_num][(idx + 1)%m], 
                    circles[c_num][idx - 1],
                ] 
                if c_num == 0:
                    neighbor.append(circles[c_num+1][idx])
                elif c_num == n-1:
                    neighbor.append(circles[c_num-1][idx])
                elif 0 < c_num < n-1:
                    neighbor.append(circles[c_num-1][idx])
                    neighbor.append(circles[c_num+1][idx])
            
                if spot_num in neighbor:
                    next_delete_spot.append((c_num, idx))
                    
                sum_for_avg += spot_num
                count_for_avg += 1
    
    # 같은 수가 없는 경우 평균과 비교해 값 변경
    if next_delete_spot:    

        for nc, i in next_delete_spot:
            circles[nc][i] = 0
    
    elif count_for_avg != 0:
        avg_circle = sum_for_avg/count_for_avg

        for c_num in range(n):
            for idx in range(m):
                if circles[c_num][idx] != 0:
                    if circles[c_num][idx] > avg_circle:
                        circles[c_num][idx] -= 1
                    elif circles[c_num][idx] < avg_circle: 
                        circles[c_num][idx] += 1
        
    # 같은 수가 있는 경우
    
    
    

n, m, t = map(int, input().split())
circles = []
result = 0
for _ in range(n):
    circles.append(deque(list(map(int, input().split()))))

#회전 수행
for round in range(t):
    xi, di, ki = map(int, input().split())
    rotate_circle(xi, di, ki)


#결과 출력
for cir in circles:
    result += sum(cir)
print(result)


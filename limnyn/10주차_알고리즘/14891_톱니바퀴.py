# https://www.acmicpc.net/problem/14891

'''
단순 구현 문제

효율을 위해서 list와 index에 대한 복잡한 구현을 통해 시간을 효율적으로 작성할 수 있을 것 같다.

하지만 일단 시간내에 빠른 구현 및 실행을 위해
회전에 대해 python의 deque.rotate를 통해서 구현해보자

기어는 12시방향부터 시계방향으로 주어진다. 


검사
    갯수는 4개로 고정되어 있으며 맞닿는 부분은 다음과 같다

    0번 기어 : 2
    1번 기어 : 6, 2
    2번 기어 : 6, 2
    3번 기어 : 6

회전
    회전 시 해당 좌표들의 값을 확인해 주면 된다.
    
    회전 방향에 대해 현재 기어와 인접 기어의 값이 다르면 
        인접 기어는 현재 기어와 반대로 회전한다

    구현에 대해
        회전을 할 때  큐에넣고
        while r_queue:
            gear, dir = r_queue.popleft()
            
            gear.rotate(dir)
            if 방문을 안한 양옆의 기어가 회전을 한다면: ->  현재 기어와 인접 기어의 값이 다르면
                r_queue.append(next_gear, -1*dir) -> 반대로 회전
        
        이런 방식으로 구현 할 수 있을 것 같다.



'''
from collections import deque
import sys
input = sys.stdin.readline




# 톱니 회전 처리 함수
def gears_rotate(num, dir):
    
    r_queue = deque([])
    r_queue.append((num, dir))
    visited = [False] * 4
    visited[num] = True
    
    while r_queue:
        num, dir = r_queue.popleft()

        # 같이 회전하게 되는 인접 기어를 queue에 삽입

        if num > 0:
            if visited[num-1] == False and gears[num-1][2] != gears[num][6]:
                visited[num-1] = True
                r_queue.append((num-1, -1*dir))
        if num < 3:
            if visited[num+1] == False and gears[num+1][6] != gears[num][2]:
                visited[num+1] = True
                r_queue.append((num+1, -1*dir))
        gears[num].rotate(dir)


# 결과 점수 계산 함수
def calc_result():
    result = 0
    for i in range(4):
        result += (gears[i][0]) * 2 ** i
    return result



# main 함수
if __name__ == "__main__":
    
    # 입력
    gears = []
    for i in range(4):
        gears.append(deque(list(map(int, list(input().strip())))))
    k = int(input())

    # 한 줄 입력 받을 때 마다 회전
    for i in range(k):
        num, dir = map(int, input().split())
        gears_rotate(num-1, dir)

    # 계산한 결과 출력
    print(calc_result())
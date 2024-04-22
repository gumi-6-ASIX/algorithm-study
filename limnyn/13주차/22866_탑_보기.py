# https://www.acmicpc.net/problem/22866
'''
1 <= N, L <= 100,000

n^2 불가

문제에서 요구하는 출력
    건물에서 "거리가 가장 가까운 건물의 번호" 중  "작은 번호"
    
    -> 모든 건물을 비교할 필요 없이 "자신보다 큰" 건물 번호를 출력하면 된다
    
    이 중 가까운 "작은 번호"를 출력하기

    왼쪽 오른쪽 한번씩 탐색하면서 자신보다 가장 가까운 큰 것을 찾아보자
    
    왼쪽에서 오른쪽으로 이동하는 동안

    1. stack[-1], 즉 stack의 top이 현재 빌딩 높이보다 높을 때 까지 pop을 한다.
    2. 만약 현재 빌딩 높이보다 높은 빌딩이 stack에 존재한다면 
        결과 리스트에 해당 빌딩의 index를 넣고 현재 빌딩을 stack에 넣는다.    
        2-1
            현재 스택에 남아 있는 갯수 = 관측할 수 있는 빌딩의 수
            따라서 스택의 크기를 계산처리해준다.
    3. 만액 스텍에 현재 빌딩 높이보다 높은 빌딩이 없다면 결과 리스트에 0을 넣는다.

    왼쪽 탐색        
    def search_left():
        stack = []
        stack.append((buildings[0], 0))

        for i in range(1, n):
            while stack:
                if stack[-1][0] <= buildings[i]:
                    stack.pop()
                else:
                    result[i] = stack[-1][1]
                    building_counts[i] += len(stack)
                    break
            stack.append((buildings[i], i))
            
    오른쪽 탐색은 위 코드에서 가까운 거리가 오른쪽 탐색이 더 가까울 때 갱신하도록 추가하면 된다.
        while stack:
            if stack[-1][0] <= buildings[i]:
                stack.pop()
            else:
                if result[i] == -1 or abs(i - stack[-1][1]) < abs(i - result[i]):
                    result[i] = stack[-1][1]
                break
        building_counts[i] += len(stack)
        stack.append((buildings[i], i))
            
'''

import sys
input = sys.stdin.readline

def search_left():
    stack = []
    stack.append((buildings[0], 0))

    for i in range(1, n):
        while stack:
            if stack[-1][0] <= buildings[i]:
                stack.pop()
            else:
                result[i] = stack[-1][1]
                building_counts[i] += len(stack)
                break
        stack.append((buildings[i], i))
        
        
    # print(result)


def search_right():
    stack = []
    stack.append((buildings[-1], n-1))

    for i in range(n-2, -1, -1):
        while stack:
            if stack[-1][0] <= buildings[i]:
                stack.pop()
            else:
                if result[i] == -1 or abs(i - stack[-1][1]) < abs(i - result[i]):
                    result[i] = stack[-1][1]
                break
        building_counts[i] += len(stack)
        stack.append((buildings[i], i))
        
    # print(result)

if __name__ == "__main__":
    n = int(input())
    buildings = list(map(int, input().split()))
    result = [-1]*n
    building_counts = [0] * n
    search_left()
    search_right()
    

    for idx, building_index in enumerate(result):
        if building_index == -1:
            print(0)
        else:
            print(f"{building_counts[idx]} {building_index + 1}")    
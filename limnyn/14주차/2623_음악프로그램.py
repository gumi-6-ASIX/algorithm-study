# https://www.acmicpc.net/problem/2623

'''
음악 프로그램

"순서를 정하는" 문제
    순서 
        -> 위상 정렬을 통해 정해진 부분 순서를 지키면서 전체 순서를 정할 수 있다.

"위상 정렬 구현"
    [fan in | fan out 사용]
    1. 순서에 대한 입력을 그래프로 받는다.
    2. fan_in에 대한 count 배열을 만든다
    3. deque dq에 fan_in이 0인 노드들을 넣는다.
    

    4. 반복수행
        while dq:
            node = dq.popleft()
            print(node, end=' ') 출력
            for next_node in graph[node]:
                fan_in[next_node] -= 1
                if fan_in[next_node] == 0:
                    dq.append(next_node)

    여기서 위상 정렬이 불가능한 경우에 대해서 예외처리? 해결을 해 보자
        [입력에서 싸이클이 있을때]
            입력 받을 때 싸이클을 어떻게 검증할 수 있을까?

        [반복 수행을 한 이후]
            while dq로 순회를 한 이후에도 fan_in이 0 이상인 노드가 존재하면 사이클이 존재한다!
            따라서 반복문 이후 fan_in이 1이상인 노드가 존재하는지 검사해서 판별한다

'''
from collections import deque
import sys
input = sys.stdin.readline

def topology_sort():
    dq = deque([])
    for i in range(n):
        if fan_in[i] == 0:
            dq.append(i)
    
    result = []
    while dq:
        node = dq.popleft()
        result.append(node + 1) # 0~n-1 노드 번호를 1~n 노드 번호로 변경
        for next_node in graph[node]:
            fan_in[next_node] -= 1
            if fan_in[next_node] == 0:
                dq.append(next_node)
    for f_i in fan_in:
        if f_i > 0:
            print(0)
            return
    for r in result:
        print(r)
    return

if __name__ == "__main__":
    n, m = map(int, input().split())
    graph = [[] for _ in range(n)]
    fan_in = [0] * n
    for _ in range(m):
        line_up = list(map(int,input().split()))
        # 1부터 시작하는 노드 번호를 0부터 시작하게 수정하기 위헤
        line_up = [x - 1 for x in line_up] 
        start = 1
        while start + 1 < len(line_up):
            start_node = line_up[start]
            end_node = line_up[start + 1]
            graph[start_node].append(end_node)
            fan_in[end_node] += 1
            start += 1
    
    topology_sort()

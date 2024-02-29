# https://www.acmicpc.net/problem/17471

"""
접근 - bruteforce
2<=n<=10
node갯수 n = 10일 때 부분집합의 갯수 : 2^10

모든 조합(부분 집합 A에)에 대해 B를 A^c( A의 여집합)라고 하면
    1. 부분집합A 내부가 연결되어 있는지
    2. 나머지집합 B가 서로 연결되어 있는지 
    3. 1  &  2를 만족하면 result = max(result, abs(sum_a - sum_b))

    "1 & 2"를 만족하면 선거구가 두개이다. 
        -A와 A의 여집합이 각각 서로 연결되어 있기 때문에

"연결"에 대한 구현
    BFS | DFS 를 통해 노드 A[0] 대해 탐색을 하고, 
    탐색을 마쳤을 때 조합에 대해 미방문 한 노드가 존재하면 연결이 되지 않은 것이다

"""
from collections import deque

### "연결"에 대한 구현 함수 ### 
def is_connected(graph_comb):
    """
    # 각 조합에 대해 
    # -> 연결이 되어 있으면 인구수 총합,
    # -> 연결 안되어 있으면 -1
    """
    #graph_comb 예시 = (0, 3, 4) -> 구획을 (0, 3, 4) 노드와 나머지 노드로 나눈다고 가정할 때
    visited = [0]*n
    dq = deque([])
    
    # 연결되어있다면 아무 노드에서 시작해도 
    # bfs의 결과 상태에서 sum(visited) == len(graph_comb) 를 만족해야한다
    dq.append(graph_comb[0]) 
    visited[graph_comb[0]] = 1
    while dq:
        node = dq.popleft()

        for neighbor in graph[node]:
            if visited[neighbor] == 0 and neighbor in graph_comb:
                visited[neighbor] = 1
                dq.append(neighbor)


    if sum(visited) == len(graph_comb):
        result = 0
        for g in graph_comb:
            result += population[g]
        return result
    else:
        return -1    


############################# main ##################################
# 인구 차이 비교를 위해 
answer = 1e9
n = int(input())
graph = []
# 인구를 입력받는다.
population = list(map(int,input().split()))
# 그래프 입력
for i in range(n):
    # 그래프 번호를 인덱스를 위해 0~n-1까지 처리하기 위해 -1씩 해준다.
    line_input = list(map(int,input().split()))
    line = [x-1 for x in line_input[1:]]
    graph.append(line)

### 각 조합 별 탐색 ###
from itertools import combinations
# 숫자들의 조합을 위한 리스트 [0~n-1]
cmb_num_list = [x for x in range(n)]
for i in range(1, n):
    comb = combinations(cmb_num_list, i)
    for a in comb:
        a = list(a)
        # a 는 부분집합 A
        b = [x for x in cmb_num_list if x not in a]
        # print(a, b)

        result_a = is_connected(a)
        if result_a == -1:
            continue
        result_b = is_connected(b)
        if result_b == -1:
            continue
        answer = min(answer, abs(result_a-result_b))

### 결과 출력 ###
# 구획이 2개가 아니면
if answer == 1e9:
    print(-1)
else:
    print(answer)


# 6
# 5 2 3 4 1 2
# 2 2 4
# 4 1 3 6 5
# 2 4 2
# 2 1 3
# 1 2
# 1 2
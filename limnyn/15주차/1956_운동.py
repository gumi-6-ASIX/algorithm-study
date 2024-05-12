# https://www.acmicpc.net/problem/1956

'''
[목적]
    그래프가 주어졌을 때 "최소 길이 싸이클"을 찾아라

[조건]
    간선에 "음수"가 주어지지 않는다.
    도로는 "일방 통행"이다

[접근]
    싸이클을 찾으려면?
        이어져 있어야 한다.
    
    2 <= V <= 400 제약 조건이 주어져 있기 때문에
    400^3 -> 64,000,000, 
    FloydWashall 연산이 가능하다

    1. FloydWasahll 연산을 통해 두 점간의 최단거리를 구한다.
    2. graph[x][y] + graph[y][x]가 최소값인 경우가 정답
        이유 : 싸이클인 경우
            x -> y && y -> x 서로 이동 가능한 경우
            싸이클 중 최소값을 구하기

'''
import sys
input = sys.stdin.readline

def floydwashall():
    '''
    graph[x][y]에 대해 x->y까지 가는 경로의 최소값을 갱신
    '''
    for k in range(v):
        for i in range(v):
            for j in range(v):
                if i == j:
                    graph[i][j] = 0
                    continue
            
                if graph[i][k] + graph[k][j] < graph[i][j]:
                    graph[i][j] = graph[i][k] + graph[k][j]
            

if __name__ == "__main__":
    v, e = map(int, input().split())
    graph = [[1e9]*v for _ in range(v)]
    for _ in range(e):
        start, end, distance = map(int, input().split())
        graph[start-1][end-1] = distance

    floydwashall()
    
    # 싸이클의 최소값 찾기
    result = 1e9
    for x in range(v):
        for y in range(v):
            if x == y:
                continue
            result = min(graph[x][y] + graph[y][x], result)

    if result >= 1e9:
        print(-1)
    else:
        print(result)
    



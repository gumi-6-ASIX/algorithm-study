# https://www.acmicpc.net/problem/1202

'''
보석 도둑
knabsack문제와 유사한 유형으로 보이는데 
가방에는 하나의 보석만 넣을 수 있다는 조건으로 인해 knapsack으로는 접근 불가

입력 조건
    보석 갯수, 가방 갯수 
        1 <= N , K <= 300,000
    각 보석의 정보 M_i, V_i
        0 <= M_i, V_i <= 100,000,000
    
보석을 최대한 많이 훔칠 때의 가격의 합
    1. 그리디? 하게 넣어보기
        -보석에 대해 가장 적은 크기의 가방부터 그리디하게 채운다.
        -> 가장 적은 크기 가방에 대해 해당 허용 무게 중 가장 비싼걸 채워넣는다
        가능한 이유 -> 가방에 "보석 1개" 만 가능하기 때문에

        일단 weight,value를 w, v라고 할 때

        입력받은 w, v에 대해 w를 기준으로 쌍으로 정렬하여 heapq를 만든다
            입력
            jewels = []
            for _ in range(n):
                w, v = map(int, input().split())
                heapq.heappush(jewels, (w, v))
        그리고 가방 크기 또한 크기가 적은 순서대로 입력받는다.
            backpacks = []
            for _ in range(k):
                C_i = int(input())
                heapq.heappush(backpacks, C_i)

        
        이제 가방 크기에 대해 채우는 함수를 작성하자
            1. 전역적으로 사용할 우선순위 큐 하나 선언
                이 안에는 (가치, 무게) 쌍으로 최대 힙으로 값을 넣는다.
                v_w_max_pq = []
            2. 가방의 무게까지 jewels pq에서 하나씩 꺼내 v_w_max_pq에 채워 넣는다
            
            3. 다 채운 이후 v_w_max_pq에서 하나를 꺼내서 해당 value를 최대값으로 지정한다.
                즉 result에 해당 value를 추가한다
            
            v_w_max_pq = []
            result = 0
            def pick_jewels():
                global result

                while backpacks:
                    backpack_limit = heapq.heappop(backpacks)
                    while jewels and jewels[0][0] <= backpack_limit:
                        w, v = heapq.heappop(jewels)
                    if v_w_max_pq: #만약 해당 가방 크기보다 작은 보석들이 존재할 때
                        heapq.heappush(v_w_max_pq, (-v, w)) #파이썬은 최소힙, 따라서 -붙여 최대힙 구현
                    result -= heapq.heappop(v_w_max_pq)
            
'''

import sys, heapq
input = sys.stdin.readline


def pick_jewels():
    global result

    while backpacks:
        backpack_limit = heapq.heappop(backpacks)
        while jewels and jewels[0][0] <= backpack_limit:
            w, v = heapq.heappop(jewels)
            heapq.heappush(v_w_max_pq, (-v, w)) #파이썬은 최소힙, 따라서 -붙여 최대힙 구현
        if v_w_max_pq:
            result -= heapq.heappop(v_w_max_pq)[0]

if __name__ == "__main__":
        
    n, k = map(int, input().split())

    jewels = []
    for _ in range(n):
        w, v = map(int, input().split())
        heapq.heappush(jewels, (w, v))

    backpacks = []
    for _ in range(k):
        C_i = int(input())
        heapq.heappush(backpacks, C_i)

    
    v_w_max_pq = []
    result = 0
    pick_jewels()
    print(result)

            



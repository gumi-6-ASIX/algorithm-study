# https://www.acmicpc.net/problem/1826

'''
접근
    
    1 <= n <= 10000

    주유소의 갯수는 10000
    현재부터 마을까지의 거리는 1 <= L <= 1,000,000


    1~1,000,000 까지의 크기에 대해 한 번 씩 접근해서 dp처리를 하면 시간 안에 수행

    "우선순위 큐"
    
    1. 일단 지나칠 수 있을 만큼 다 지나치면서
        지나친 주유소들에 대해 기름을 우선순위 큐에 넣는다
    2. 다음주유소에 대해서 기름이 다 떨어졌다면. 지나친 주유소 중 가장 기름이 많은 곳에서 넣는다
        -> 채워 질 때 까지 반복
    3. 1과 2를 반복한다.
    
    pypy는 이 방법 아니여도 통과되지만 python3는 이 방법으로만 시간 내에 통과된다. 
'''


import sys
input = sys.stdin.readline

#입력단###
n = int(input())
#주유소 입력
gas_stations = [list(map(int, input().split())) for _ in range(n)]

end, start_gas = map(int, input().split())
gas_stations.append([end, 0])
gas_stations.sort()
##########

import heapq
unvisited_gas_station = []

visit_count = 0
gas_now = start_gas
for km, gas in gas_stations:
    if gas_now < km:
        #힙에서 km 넘길 때 까지 가스 주유하고 count
        while(gas_now < km):
            
            # 만약 더이상 주유할 곳이 없다면? -1 출력하고 종료
            if len(unvisited_gas_station) == 0:
                print(-1)
                exit()

            #파이썬은 최소힙만 지원하기 때문에 최대힙은 내부 값을 반전시켜 사용한다.
            gas_now += -1*(heapq.heappop(unvisited_gas_station))
            visit_count += 1

    #파이썬은 최소힙을 지원하기 때문에 최대힙은 값을 반전시켜 사용한다.
    heapq.heappush(unvisited_gas_station, -gas)

print(visit_count)
    

    
"""
아래는 처음 푼 heapq를 사용하지 않고 탐색하는 방식
근데 이거는 -1 처리를 안해줘서 틀렸고 pypy로 시간 내에 통과되는 문제이다.
방문횟수별 최대 가스를 저장하는 counts[] 1차원 배열에 대해
현재 gasstation번호 s_num부터 1까지 i에 대해
    for i in range(station_num+1, 0, -1):
        if counts[i-1] < km:
            break
        counts[i] = max(counts[i], counts[i-1]+gas)
    를 반복해서 각 기름 넣는 횟수 별 갈 수 있는 거리를 초기화 해주었다.
    이를 통해 기름 넣는 횟수 별 최대 거리를 구할 수 있고
    counts[] 배열은 방문횟수 별 최대 거리 값이 들어있다.
    이 중 마을 까지 거리보다 긴 거리를 가는 최소 방문 횟수를 구할 수 있다.


    

import sys
input = sys.stdin.readline
n = int(input())
gas_station = []
for _ in range(n):
    gas_station.append(list(map(int, input().split())))
end, start_gas = map(int, input().split())
gas_station.sort()
counts = [0]*(n+1)
counts[0] = start_gas

for station_num in range(n):
    km, gas = gas_station[station_num]
    
    for i in range(station_num+1, 0, -1):
        if counts[i-1] < km:
            break
        counts[i] = max(counts[i], counts[i-1]+gas)

for i in range(n):
    if counts[i] >= end:
        print(i)
        exit()
print(-1)
    

"""
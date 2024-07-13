def solution(cap, n, deliveries, pickups):
    '''
    [택배 배달]
    1. 상차 - min(최대 실을 수 있는 갯수, 남은 배달할 양) 만큼 실는다.
    2. 가장 먼 곳까지의 거리를 값에 더한다.
    3. 가장 먼 곳에서부터 역순으로 상차 한 만큼 배달을 한다.
    [택배 수거]
    1. 택배 배달한 가장 먼 곳에서 부터 (물류창고에서 가장 먼) 택배 수거할 장소까지의 거리를 더한다.
    2. 해당 수거할 장소에서부터 물류창고 까지의 거리를 구한다.
    3. 가장 먼 수거장소부터 역순으로 수거할 수 있을만큼 수거한다.
    '''
    left_delivery = sum(deliveries)
    left_pickup = sum(pickups)
    answer = 0
    
    # 스택으로 풀기위해 0이 아닌 값들에 대해 좌표와 택배 수로 변환
    deliver_stack = [] # (집주소, 배달할 택배수)
    for i in range(n):
        if deliveries[i] > 0:
            deliver_stack.append((i+1, deliveries[i]))
    pickup_stack = []
    for i in range(n): # (집주소, 수거할 택배수)
        if pickups[i] > 0:
            pickup_stack.append((i+1, pickups[i]))
    

    while deliver_stack or pickup_stack: #택배 배달할 곳과 수거할 곳이 존재 할 때
        truck_loaded = cap
        
        # 택배 배달 --------------------------------------------------------------------
        if deliver_stack: #배달할 곳이 있을 때
            last_deliver = deliver_stack[-1][0] # 물류창고에서 가장 먼 배달지점까지 이동
            while deliver_stack and truck_loaded: #배달할게 남아 있으면
                addr, deliver = deliver_stack.pop()
                if truck_loaded >= deliver:
                    deliveries[addr-1] = 0
                    truck_loaded -= deliver
                else:
                    deliveries[addr-1] -= truck_loaded
                    deliver_stack.append((addr, deliveries[addr-1]))
                    truck_loaded = 0
                    break
        else: #배달할 곳이 없을 때
            last_deliver = 0
        answer += last_deliver #배달하러 이동한 거리 더함

        # 택배 수거 --------------------------------------------------------------------
        if pickup_stack: # 수거 할 곳이 있을때
            last_pickup = pickup_stack[-1][0] # 물류창고에서 가장 먼 픽업지점
            truck_loaded = 0
            while pickup_stack and truck_loaded < cap: #픽업할게 남아 있으면
                addr, pickup = pickup_stack.pop()
                if truck_loaded + pickup <= cap:
                    pickups[addr-1] = 0
                    truck_loaded += pickup
                else:
                    pickups[addr-1] -= (cap - truck_loaded)
                    pickup_stack.append((addr, pickups[addr-1]))
                    truck_loaded = cap
                    break
        else:
            last_pickup = 0
        answer += abs(last_deliver - last_pickup)
        answer += last_pickup
        
        
    return answer
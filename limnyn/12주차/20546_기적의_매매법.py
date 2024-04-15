# https://www.acmicpc.net/problem/20546

'''
준현
    1. 주식을 살 수 있으면 전부 매수한다
    2. 절대 팔지 않는다.
    -> 그리디 하게 구매를 진행

성민
    1. 전량 매수 전량 매도
    2. 3연상일때 전량매도
    3. 3연하일때 젼량매수
    4. 가격 동일 시 연속이 아니다.
    -> 투 포인터로 가격이 상승하는지 하락하는지 알 수 있다.

'''
import sys
input = sys.stdin.readline


def bnp(cost_joonhyeon): #Buy and Pray
    account_stock_count = 0

    for stock_price in stock_market:
        if stock_price <= cost_joonhyeon:
            stock_count =  (cost_joonhyeon // stock_price)
            account_stock_count += stock_count
            cost_joonhyeon -= stock_price * stock_count
        
    return account_stock_count * stock_market[-1] + cost_joonhyeon # 남은 돈 까지 더해주기


def timing(cost_sungmin):
    account_stock_count = 0 # 계좌의 주식 수
    
    trend = 0 # 상승 시 1, 하강 시 -1
    day_count = 0 #index
    before = 0 #two pointer
    now = 1 #two pointer
    while now < len(stock_market):
        if stock_market[before] > stock_market[now]:
            if trend != -1:
                trend = -1
                day_count = 1
            else:
                day_count += 1
            
        elif stock_market[before] < stock_market[now]:
            if trend != 1:
                trend = 1
                day_count = 1
            else:
                day_count += 1
    
        elif stock_market[before] == stock_market[now]:
            trend = 0
            day_count = 0

        if day_count >= 3:
            if trend == -1: # 전량 매수
                if stock_market[now] <= cost_sungmin:
                    stock_count =  (cost_sungmin // stock_market[now])
                    account_stock_count += stock_count
                    cost_sungmin -= stock_market[now] * stock_count
            elif trend == 1: # 전량 매도
                cost_sungmin += account_stock_count * stock_market[now]
                account_stock_count = 0    

        
        before, now = now, now + 1
    
    cost_sungmin += account_stock_count*stock_market[-1]
    return cost_sungmin
            
            



if __name__ == "__main__":
    cost_joonhyeon = int(input())
    cost_sungmin = cost_joonhyeon
    stock_market = list(map(int, input().split()))
    bnp_result = bnp(cost_joonhyeon) 
    timing_result = timing(cost_sungmin)
    if bnp_result == timing_result:
        print("SAMESAME")
    elif bnp_result > timing_result:
        print("BNP")
    else:
        print("TIMING")
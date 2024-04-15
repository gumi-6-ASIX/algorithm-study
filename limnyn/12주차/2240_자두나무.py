# https://www.acmicpc.net/problem/2240
'''
문제
    매 초 2개의 나무 중에 하나에 떨어진다
    최대 W번 움직일 수 있을 때. 최대 자두의 개수를 구하는 방법은?

    완전탐색하는 경우 시간복잡도를 초과한다 - 제외
    
    t w에 대해 냅색을 응용해서 풀기

    주의점 - 
        한번도 안 이동했을 때, 0일때를 체크
        또한 시작을 0초부터 하고 처음에도 이동할 수 있다는 점 알기


    for i in range(t + 1):

        # 0번 움직이는 경우, 즉 시작위치 1에사 아예움직이지 않는 경우에 대해 처리
        if jadoo_list[i] == 1:
            dp[i][0] = dp[i-1][0] + 1
        else:
            dp[i][0] = dp[i-1][0]
        
        # 1번 이상 움직이는 경우
        for j in range(1, w+1):
            
            홀수번 이동하면 항상 2 위치에 있다
            if jadoo_list[i] == 2 and j % 2 == 1:
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + 1

            짝수번 이동하면 항상 1 위치에 있다.
            elif jadoo_list[i] == 1 and j % 2 == 0:
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + 1
            
            else:
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])
                

'''
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
t, w = map(int, input().split())
jadoo_list = [0] + [int(input()) for _ in range(t)]
# print(jadoo_list)
dp = [[0]* (w+1) for _ in range(t+1)]
for i in range(t + 1):
    if jadoo_list[i] == 1:
        dp[i][0] = dp[i-1][0] + 1
    else:
        dp[i][0] = dp[i-1][0]
    
    for j in range(1, w+1):
        if jadoo_list[i] == 2 and j % 2 == 1:
            dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + 1

        elif jadoo_list[i] == 1 and j % 2 == 0:
            dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + 1
        
        else:
            dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])
            
print(max(dp[t]))
            


'''
7 3
2
1
1
2
2
1
1
'''
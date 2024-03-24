# https://www.acmicpc.net/problem/12101

"""
이 코드는 dp로 구현해본건데
tc에 다 잘 나오는데 백준 넣으면 어디서 틀렸는지 모르겠음 
ㅠㅠ
그래서 버리고 백트래킹 하러감
"""


n, k = map(int, input().split())

dp = [[] for _ in range(12)]

dp[2] = ["1+1"]
dp[3] = ["1+1+1","1+2", "2+1"]
         
def dp_maker(n):
    for i in range(1,4):
        for dp_before in dp[n-i]:
            dp[n].append(str(i)+"+"+ dp_before)
        if i < 4 and n-i < 4:
            dp[n].append(str(i)+"+"+str(n-i))


def dp_return(n, k):
    #여기에 4이상일 때 4부터 n까지 dp만들고, 그 다음에 len(dp[n]) >= k 면 -1 반환 아니면 dp[n][k-1]반환!
    if n == 1:
        if k == 1:
            return 1
        else:
            return -1


    for i in range(4, n+1):
        dp_maker(i)
    if len(dp[n]) >= k:
        return dp[n][k-1]
    else:
        return -1


print(dp_return(n,k))
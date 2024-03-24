# 180ms -> 140ms 로 이전코드 대비 개선

n = int(input())
dp_before = []

for r in range(1, n+1):
    
    line = list(map(int, input().split()))
    dp_now = []
    if n == 1:
        print(line[0])
        exit()
    if r == 1:
        dp_before = line
        continue

    for c in range(r):
        if c == 0:
            dp_now.append(line[0] + dp_before[0])
        elif c == r-1:
            dp_now.append(line[c] + dp_before[c-1])
        else:
            dp_now.append(
                line[c] + max(dp_before[c-1], dp_before[c])
            )
    dp_before = dp_now
    
print(max(dp_now))


# 개선 전 코드
"""
n = int(input())
triangle=[]
for _ in range(n):
    triangle.append(list(map(int, input().split())))
dp = [[] for _ in range(n)]

for r, tri in enumerate(triangle):
    if r == 0 :
        dp[r].append(triangle[0][0])
        continue

    for c, v in enumerate(tri):
        # 왼쪽 끝
        if c == 0:
            dp[r].append(tri[c]+dp[r-1][0])
        
        # 중간
        elif c == len(tri)-1:
            dp[r].append(tri[c]+dp[r-1][len(tri)-2])

        # 오른쪽 끝
        else:
            dp[r].append(max(
                dp[r-1][c-1], dp[r-1][c]
            ) + tri[c])
print(max(dp[n-1]))
        

"""
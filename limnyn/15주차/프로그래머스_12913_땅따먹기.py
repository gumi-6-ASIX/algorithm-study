# https://school.programmers.co.kr/learn/courses/30/lessons/12913

def solution(land):
    '''
    요구사항 - 같은 열을 연속으로 밟을 수 없다.
    1<= N <= 100,000
    [접근]
        백트래킹 또는 dp로 접근 할 수 있을 것 같다.
        N의 크기로 인해 백트래킹은 제외하고 dp로 접근해보자.

    [Dynamic Programming]
        dp[r][c]에 대해서 
            dp[r-1] 중에서 c열이 아닌 값 중 최댓값 + grid[r][c] 값을 넣는다.
        이를 통해 dp[r][c]에는 이전에 c열을 밟지않은 경우중 최댓값 + [r,c]값이 들어간다.

        이를 반복해서 맨 마지막 행의 최댓값을 결과로 선택하면 된다.

    '''
    n = len(land)
    dp = [[0] * 4 for _ in range(n)]
    
    dp[0] = land[0]
    
    for r in range(1,n):
        for c in range(4):
            max_of_before_row = 0
            for i in range(1,4):
                max_of_before_row = max(dp[r-1][(c+i)%4], max_of_before_row)
            dp[r][c] = max_of_before_row + land[r][c]

    return max(dp[-1])
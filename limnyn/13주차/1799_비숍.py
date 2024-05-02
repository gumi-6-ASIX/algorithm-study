# https://www.acmicpc.net/problem/1799

'''
bishop
solution explore

목표 - 최대한 많이 놓을 수 있는 경우의 놓은 수

좌상단
12345
23456
34567
45678
56789

1 2 3 4 5 6 7 8 9 10
비숍은 "대각선 당 한 개만 놓을 수 있다"
따라서 각 대각선 길이 1~10 중 한 개 씩 넣어 보며 조합을 계산해 보면 된다
좌상단, 우상단 부터 출발하여 한 번 씩 탐색하면 (0,0을 흑색 , 0,1을 백색 칸이라고 했을 때)
따라서 좌상단 대각선 한번 우상단 대각선 한번 조사하면 끝


좌상단에서부터 0,0이라고 할 때
1. 대각선에 대해서 좌표를 레벨별로 놓는다 (짝수번 white, 홀수번 block dict 또는 list를 만들어서 하기)
2. 대각선아래(우하향)으로 진행하면서 해당 칸에 대해 같은 레벨인 것으로 표기하기 (딕셔너리로?)
    2-1 딕셔너리로 (r,c)에 대해서 어느 visited idx인지 저장하고
    2-2 탐색 시 visited 배열에 값이 있는지 없는지 판별하는 과정으로 비숍이 겹치는지 아닌 지 확인 할 수 있음


3. black와 white에 대해 각각 최대값을 구하고 더한다
    3-1. 각 level에 대해서 비숍을 선택하고 다음 level로 진행한다
    3-2. 비숍을 선택할 때 killzone에서 visited 인덱스를 찾고 visited에 이미 방문했는지 확인한다
    3-3-1. visisted가 비어있으면 놓고 다음 레벨로 진행
    3-3-2. 만약 놓을 수 없다면 현재까지 최대값을 갱신하고 가지치기 한다.
'''

def n_bishop_black(bishops, level):
    '''
    흑, 백은 n, n-1 level이 존재한다. 따라서 흑 백 두개 나눠서 구현한다
    level == n-1에 도달했거나 더이상 놓을 수 없을 때 max값과 비교한다.
    '''
    global result_black

    result_black = max(result_black, bishops)
    if level == n:
        return
    for r,c in black_spot[level]:
        if visited[black_killzone[(r,c)]] == False and board[r][c] == 1:
            # 비숍을 놓을 수 있는 경우
            visited[black_killzone[(r,c)]] = True # 비숍을 놓고
            n_bishop_black(bishops+1, level + 1) # 다음 단계에 비숍을 놓는 경우 탐색
            visited[black_killzone[(r,c)]] = False # 비숍을 뺀다
    n_bishop_black(bishops, level + 1) # 이번 대각선에 비숍을 안놓는 경우

def n_bishop_white(bishops, level):
    '''
    n_bishop_black에서 level이 n-1번 되는 차이가 있다. 
    또한 bishop을 white에 두었을 때 killzone을 비교해서 탐색한다(black에 서로 영향을 주지 않는다.)
    '''
    global result_white

    result_white = max(result_white, bishops)
    if level == n-1:
        return
    for r,c in white_spot[level]:
        if visited[white_killzone[(r,c)]] == False and board[r][c] == 1:
            # 비숍을 놓을 수 있는 경우
            visited[white_killzone[(r,c)]] = True # 비숍을 놓고
            n_bishop_white(bishops+1, level + 1) # 다음 단계에 비숍을 놓는 경우 탐색
            visited[white_killzone[(r,c)]] = False # 비숍을 뺀다
    n_bishop_white(bishops, level + 1) # 이번 대각선에 비숍을 안놓는 경우

        
        





def bishop_spot_setter():
    '''
    검은칸과 흰 칸에 대해 레벨 별 좌표를 세팅해준다.
    '''
    for i in range(n):
        r, c = i, 0
        level = []
        while 0<=r and c<n:
            level.append((r,c))
            r-=1
            c+=1
        if i % 2 == 0:
            black_spot.append(level)
        else:
            white_spot.append(level)
    for i in range(1,n):
        # if n % 2 == 0 
        r, c = n-1, i
        level = []
        while 0<=r and c<n:
            level.append((r,c))
            r-=1
            c+=1
        if n % 2 == 1:
            if i % 2 == 0:
                black_spot.append(level)
            else:
                white_spot.append(level)
        else:
            if i % 2 == 1:
                black_spot.append(level)
            else:
                white_spot.append(level)
        
        
def bishop_killzone():
    '''
    좌하단부터 우하단까지 진행하면서 동일한 \방향 대각선에 대해서 서로 공격할 수 있다는 표시를 하기 위해
    딕셔너리에 좌표 : 대각선 index 값을 지정해 주어서 공격할 수 있다고 표시해준다 
        -> 이후 visisted 배열의 index를 통해 해당 좌표에 놓으면 비숍이 겹치는지 아닌지 확인할 수 있다.
    (n-1,0)부x터 열을 한 칸씩 전진하면서, 좌상단값들을 다 dict에 넣어준다
    이 때, 시작 r값이 짝수이면 black, 홀수이면 white에 넣어준다.
    '''
    for i in range(n):
        r, c = n-1, i
        
        while 0 <= r and 0 <= c:
            if n % 2 == 1:
                if i % 2 == 0:
                    black_killzone[(r,c)] = i
                else:
                    white_killzone[(r,c)] = i
            if n % 2 == 0:
                if i % 2 == 0:
                    white_killzone[(r,c)] = i        
                else:
                    black_killzone[(r,c)] = i
            r-=1
            c-=1
    
    for i in range(n-1):

        r, c = i, n-1
        
        while 0 <= r and 0 <= c:
            if n % 2 == 1:
                if i % 2 == 0:
                    black_killzone[(r,c)] = i + n + 1
                else:
                    white_killzone[(r,c)] = i + n + 1
            else:
                if i % 2 == 1:
                    black_killzone[(r,c)] = i + n + 1
                else:
                    white_killzone[(r,c)] = i + n + 1                
            r-=1
            c-=1




    
dr = [-1, -1, 1, 1]
dc = [-1, 1, -1, 1]
black_spot = []
white_spot = []
black_killzone = {}
white_killzone = {}
result_black = 0
result_white = 0


import sys, copy
input = sys.stdin.readline
if __name__ == "__main__":
    n = int(input())
    visited = [False]*(2*n)
    board = [list(map(int, input().split())) for _ in range(n)]
    
    bishop_spot_setter()
    bishop_killzone()

    n_bishop_black(0,0)
    n_bishop_white(0,0)
    print(result_black+result_white)


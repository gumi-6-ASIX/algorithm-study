# https://www.acmicpc.net/problem/7682
'''
[문제]
    한줄에 9개의 문자
    첫번쨰 사람이 X, 두번째 사람이 O
    한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는데 성공하면 게임 끝
    게임판이 가득차도 게임이 끝난다.

[목표]
    틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별
    보드가 완성되었다는 가정하에
    1. X가 이기는 경우
        x가 한줄이상이고 o는 줄이 없고 x == o+1 갯수 일때
    2. O가 이기는 경우
        x가 줄이 없고 o는 줄이 있고 
    3. 비기는 경우
        x o 둘다 줄이 없고 x가 o보다 하나 많을때,
    나머지는 불가능한 경우
    

'''
import sys
def input(): return sys.stdin.readline().rstrip()


def check_winner(game):
    x_count = 0
    o_count = 0
    for line in game:
        for char in line:
            if char == 'X':
                x_count += 1
            elif char == 'O':
                o_count += 1
                
    grid = []
    for i in range(3):
        grid.append(game[i*3:(i+1)*3])
    
    win_x, win_o = 0, 0
    for r in range(3):
        if grid[r][0] == grid[r][1] == grid[r][2]:
            if grid[r][0] == 'X': win_x += 1
            if grid[r][0] == 'O' : win_o += 1
        if grid[0][r] == grid[1][r] == grid[2][r]:
            if grid[0][r] == 'X': win_x += 1
            if grid[0][r] == 'O': win_o += 1
    if grid[0][0] == grid[1][1] == grid[2][2]:
        if grid[0][0] == 'X': win_x += 1
        if grid[0][0] == 'O': win_o += 1
    if grid[0][2] == grid[1][1] == grid[2][0]:
        if grid[0][2] == 'X': win_x += 1
        if grid[0][2] == 'O': win_o += 1
        
    
    # X 승리
    # - x는 줄 존재하고 O는 줄 존재 X, X는 O보다 하나 많아야함
    if win_x >= 1 and win_o == 0 and x_count == o_count + 1:
        return True
    # O 승리
    # - x는 줄 존재하지 않고 o는 줄 존재한다, x와 o 갯수 동일해야함
    if win_o == 1 and win_x == 0 and x_count == o_count:
        return True
    # 비길때
    # x, o 둘다 이긴게 존재하지 않고, x 5 o 4개
    if win_x == 0 and win_o == 0 and x_count == 5 and o_count == 4:
        return True
    # 그외
    # 실패
    return False
    



if __name__=="__main__":
    result = []
    game = input()
    while game != "end":
        game = list(game)
        if check_winner(game):
            # print("valid")
            result.append("valid")
            game = input()
            continue
        # print('invalid')
        result.append("invalid")
        game = input()

        
    for r in result:
        print(r)

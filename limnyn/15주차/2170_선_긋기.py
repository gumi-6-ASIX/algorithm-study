# https://www.acmicpc.net/problem/2170

'''
문제 x,y가 2차원 상의 좌표를 나타내는 줄 알고 문제 이해에 좀 걸렸다

[목표]
    1차원 상의 start-end가 주어진다.
    해당 start-end에 대해서 선을 이었을때 "전체" 길이를 구하는 것

[접근]
    겹치는 선에 대해서는 하나로 본다는 조건이 존재한다.
    따라서 중복범위에 대한 것은 

    start, end에 대해서 
        start는 오름차순, end는 내림차순으로 정렬한다
            -> key = lambda x : (x[0], -x[1]) 로 정렬
        
        그리고 min start, max end를 정렬된 리스트의 첫번째 값으로 지정하자.
        -> 제일 작은 x 부터 출발해서 그리기 시작한다.

        if min_start <= start <= end <= max_end:
            continue
        
        if min_start <= start <= max_end <= end:
            해당 경우 max_end를 end로 갱신해준다 
            (겹치는 부분은 제일 작은값과 제일 큰 값으로 양 끝을 나타낸다)
        
        if max_end < start:
            해당 경우 이제 선이 겹치지 않기 때문에 
            이때까지 겹친 범위에 대해 result += abs(max_end-min_start) 해준다
            그리고 start,end를 새로운 min_start, max_end 로 시작한다
            min_start, max_end = start, end

'''

def drawline():
    result = 0
    min_start, max_end = start_end_list[0]
    for start, end in start_end_list[1:]:
        if min_start <= start <= max_end:
            if end > max_end:
                max_end = end
        else: # 이제 선이 겹치지 않음. 새 선 긋기 시작
            result += abs(max_end - min_start)
            min_start = start
            max_end = end

    result += abs(max_end-min_start)
    print(result)


import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    start_end_list = []
    for _ in range(n):
        start, end = map(int, input().split())
        start_end_list.append((start,end))
    
    start_end_list = sorted(start_end_list, key = lambda x : (x[0], -x[1]))
    drawline()
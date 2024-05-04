# https://school.programmers.co.kr/learn/courses/30/lessons/150368

# 아래는 전역변수들 - solution 외부에서도 users, emoticons를 접근할 수 있게 하기 위해서
import copy
discount = [0.9, 0.8, 0.7, 0.6]
m, n = 0, 0
emos = []
global_users = []
result_sign = -1
result_sell = -1

def solution(users, emoticons):
    '''
    [목표]
        1. 가장 많은 가입자 수
        2. 그 중 판매액이 가장 많은 것
        
    [알고리즘 선택]
        
        [제약 조건을 통해 어떤 시간복잡도 까지 사용 가능할 지 알아보자]
            1 <= 이모티콘의 갯수 <= 7
                이모티콘당 할인율은 4개, 즉 최대 4^7 = 16,384가지 경우가 존재한다.
                해당 경우에 대해 100*7*16384 = 11,468,800 
            
            -> 완전탐색으로 시간 내에 가능하다.
        
    [알고리즘 -> 완전탐색 선택]
        [필요한 모듈]
            [1. 조합 생성]
                조합은 permutation 모듈을 사용하거나 for문 dfs로 생성가능하다.
            [2. 검사 함수]
                조합이 완성되었을 때. 모든 유저에 대해서 이모티콘 플러스 서비스 가입자 수, 이모티콘 판매액을 구한다.
                그리고 해당 값을 max값과 비교한다.
                
    '''
    global n, m, emos, global_users
    n = len(users)
    m = len(emoticons)
    emos = copy.deepcopy(emoticons)
    global_users = copy.deepcopy(users)
    dfs([])
    
    return [result_sign, result_sell]




def dfs(lst):
    '''
    1. 조합 생성 함수
    '''
    if len(lst) == m:
        calc(lst)
        return
    for i in range(4):
        dfs(lst + [i])
        
def calc(lst):
    '''
    2. 검사 함수
    '''
    global result_sign, result_sell
    
    # 할인된 가격 적용
    sale_emoticons = []
    emoticon_plus_sign = 0
    emoticon_sell = 0
    
    for i, v in enumerate(emos):
        sale_emoticons.append(discount[lst[i]]*v)
    
    # 각 사람에 대해 이모티콘 계산하기
    for user in global_users:
        emoticon_sell_by_user = 0
        
        user_per, account = user
        user_per = (100-user_per)*(0.01)
    
        for i in range(m):
            emos_discount_percent = discount[lst[i]]
            if user_per >= emos_discount_percent:
                emoticon_sell_by_user += sale_emoticons[i]
                if account <= emoticon_sell_by_user:
                    emoticon_sell_by_user = -1
                    break
        if emoticon_sell_by_user == -1:
            emoticon_plus_sign += 1
        else:
            emoticon_sell += emoticon_sell_by_user
    
    # 해당 조합에 대한 결과 값을 최대값과 비교 갱신
    if emoticon_plus_sign == result_sign:
        if emoticon_sell > result_sell:
            result_sign = emoticon_plus_sign
            result_sell = emoticon_sell  
    if emoticon_plus_sign > result_sign:
            result_sign = emoticon_plus_sign
            result_sell = emoticon_sell  


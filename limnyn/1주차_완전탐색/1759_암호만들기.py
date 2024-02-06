# https://www.acmicpc.net/problem/1759

"""
'서로다른' L개의 알파벳소문자로 구성
모음 : 최소 한개
자음 : 최소 두개
알파벳은 적어도 앞에 문자보다 증가해야 한다.
중복되는 수는 제공되지 않는다.
"""

l, c = map(int, input().split()) # l자리 비밀번호, c개의 글자
line = sorted(list(input().split()))
line.sort()
# print(line)

#입력문자 중 자음모음 구별하기    
consonant = [x for x in line if x not in ['a','e','i','o','u']] # 자음
vowel = [x for x in line if x in ['a', 'e', 'i', 'o', 'u']] # 모음

def is_able(word):
    v, c =  0, 0
    for w in word:
        if w in vowel:
            v += 1
        else:
            c += 1        
    if c >= 2 and v >= 1:
        return True
    else:
        return False

def backtracking(arr):
    if len(arr) == l:
        if is_able(arr):
            print("".join(arr))
            return

    for i in range(len(arr), c):
        if arr[-1] < line[i]:
            arr.append(line[i])
            backtracking(arr)
            arr.pop()

for i in range(c - l + 1):
    a = [line[i]]
    backtracking(a)


"""
순차적으로 접근하며
만약 길이가 L일 때 
모음이 최소 1개, 자음이 최소 2개가 아니면 백트래킹?
"""

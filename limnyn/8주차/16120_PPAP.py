# https://www.acmicpc.net/problem/16120

"""
ppap
1. 문자열을 뒤에서부터 pop하면서
2. check_is_ppap 리스트에 넣으면서
    3-1. check_is_ppap의 길이가 4 이상일 때
        3-2. while 뒤의 4자리 글자가 PAPP인 동안:
                햬당 문자열 PAPP를 P로 대체

4. 만약 스택에 P만 남으면 정상 통과
4-1. 아니면 NP 출력


"""


strlist = list(input())
check_is_ppap = []
while strlist:
    check_is_ppap.append(strlist.pop())
    
    if len(check_is_ppap) >= 4:
        while ''.join(check_is_ppap[-4:]) == "PAPP":
            for i in range(3):
                check_is_ppap.pop()
            

if ''.join(check_is_ppap) == "P":
    print("PPAP")
else:
    print("NP")

            
# https://www.acmicpc.net/problem/12101

n, k = map(int, input().split())

count = 0 # 0부터 k 번째가 될 때까지 측정

def backtracking(index, string_sum, line):
    # index : 1~3순서로 들어간다
    global count
    
    #만약 string합이 sum보다 크다면 다시 pop
    if string_sum > n:
        return 
    
    if string_sum == n:
        count += 1
        if count == k:
            print(''.join(line)[:-1])
            exit()
    
    for i in range(1, 4):
        line.append(str(i)+'+')
        backtracking(index+1, string_sum + i, line)
        line.pop()

    
backtracking(0,0,[])
print(-1)

# https://www.acmicpc.net/problem/10974


from itertools import permutations
for p in [num for num in permutations([ i for i in range(1,int(input())+1)])]:
    for n in p:
        print(n, end=' ')
    print()
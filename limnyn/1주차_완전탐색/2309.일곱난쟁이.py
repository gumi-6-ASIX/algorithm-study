# # https://www.acmicpc.net/problem/2309
# import itertools
line = [int(input()) for _ in range(9)]

# comb = itertools.combinations(line, 7)
# for c in comb:
#     if sum(c) == 100:
#         result = sorted(c)
#         break
# for r in result:
#     print(r)


#또는 아래 코드
for i in range(9):
    for j in range(9):
        line_not_ij = [x for x in line if x not in [line[i], line[j]]]
        if sum(line_not_ij)== 100:
            for c in sorted(line_not_ij):
                print(c)
            break


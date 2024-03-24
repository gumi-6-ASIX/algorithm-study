# https://www.acmicpc.net/problem/1339



"""
1. 단어별 자리수 비중을 구한다
2. 비중이 큰 값 순서대로 9부터 부여한다
3. 계산한다
"""
n = int(input())
word_num_dict = {}


words_origin = []
sum_by_char = {}
idx = 0
for _ in range(n):
    line = list(input())
    words_origin.append(line)


for word in words_origin:
    for i, c in enumerate(word):
        if sum_by_char.get(c):
            sum_by_char[c] += 10**(len(word)-i-1)
        else:
            sum_by_char[c] = 10**(len(word)-i-1)
word_sorted = []
for i, v in sum_by_char.items():
    word_sorted.append((i, v))
word_sorted.sort(key=lambda x: -x[1])
num = 9
for word, _ in word_sorted:
    word_num_dict[word] = num
    num -= 1




result = 0
for word in words_origin:
    for i, c in enumerate(word):
        result += word_num_dict[c] * 10 ** (len(word)-1-i)
print(result)
"""
이전처럼 2차원 행렬말고
1차원 행렬로 처리 

10
ABB
BB
BB
BB
BB
BB
BB
BB
BB
BB    


"""




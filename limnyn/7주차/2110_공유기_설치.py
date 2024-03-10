# https://www.acmicpc.net/problem/2110


# 파라메트릭서치 문제

"""
파라메트릭 서치 문제

공유기 간격 gap에 대해 1 , nums[-1] - nums[0] 사이에서 파라메트릭 서치를 통해 찾는다
공유기 간격이 작으면 count 는 c보다 커지고
공유기 간격이 크면 count 는 c보다 작아지는데
count가 c보다 크거나 같은 값 중에 gap이 최대인 경우를 찾을 수 있다.
"""
n, c = map(int, input().split())
nums = []


for _ in range(n):
    nums.append(int(input()))
nums.sort() # 이진탐색 수행을 위해 정렬

result = 1e9
start = 1
end = nums[-1]-nums[0]
while(start <= end):
    gap = (end + start) // 2

    count = 1

    s = nums[0]
    for i in range(1,n):
        if nums[i] - s >= gap:
            count += 1
            s = nums[i]
    
    if count >= c:
        result = gap
        start = gap + 1
    else:
        end = gap - 1

print(result)
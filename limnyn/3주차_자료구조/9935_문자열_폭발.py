# https://www.acmicpc.net/problem/9935

line = input()
boom = input()

stack = []
for c in line:
    stack.append(c)
    if len(stack) >= len(boom):
        # list 끝 boom자리수 글자가 boom이랑 같을 때 pop
        if ''.join(stack[-len(boom):]) == boom:
            for i in range(len(boom)):
                stack.pop()

result = ''.join(stack)

if result == "":
    print("FRULA")
else:
    print(result)
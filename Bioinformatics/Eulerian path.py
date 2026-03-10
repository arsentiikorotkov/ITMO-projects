from sys import stdin

n, m = map(int, stdin.readline().split())

neighbours = [[] for _ in range(n)]
for _ in range(m):
    a, b = map(int, stdin.readline().split())
    neighbours[a - 1].append(b)
# print(neighbours)

ans = []
def Euler(start):
    stack = [start]
    while stack:
        v = stack[-1]
        found_edge = False

        if neighbours[v - 1]:
            u = neighbours[v - 1].pop()
            stack.append(u)
            found_edge = True

        if not found_edge:
            ans.append(v)
            stack.pop()

Euler(1)
# print(ans)
for i in ans[-1:0:-1]:
    print(i, end=' ')

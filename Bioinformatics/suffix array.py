from sys import stdin


str = stdin.readline().strip() + "$"

suf_str = [str[i:] for i in range(len(str) - 1)]
suf_str.sort()

suf = [len(str) - len(suf_str[i]) for i in range(len(suf_str))]

print(*suf)

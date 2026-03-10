from sys import stdin


def suf(s):
    suf_str = [s[i:] for i in range(len(s))]
    suf_str.sort()

    return [len(s) - len(suf_str[i]) for i in range(len(suf_str))]

def bws(s):
    return ["$" if ind == 0 else s[ind - 1] for ind in suf(s)]


s = stdin.readline().strip() + "$"
    
print("".join(bws(s)))

import numpy as np
from sys import stdin


def NW_init(x, y, d):
    dp = np.empty((len(x) + 1, len(y) + 1), dtype=object)
    dp.fill((0, "", ""))

    for i in range(len(x) + 1):
        dp[i][0] = (i * d, x[:i], "-" * i)
    for j in range(len(y) + 1):
        dp[0][j] = (j * d, "-" * j, y[:j])

    return dp

def NW_update(dp, x, y, w, i, j):
    w_arg_max = np.argmax(w)

    if w_arg_max == 0:
        dp[i][j] = (w[w_arg_max], 
                        dp[i - 1][j - 1][1] + x[i - 1],
                        dp[i - 1][j - 1][2] + y[j - 1])
    elif w_arg_max == 1:
        dp[i][j] = (w[w_arg_max], 
                        dp[i - 1][j][1] + x[i - 1],
                        dp[i - 1][j][2] + "-")
    elif w_arg_max == 2:
        dp[i][j] = (w[w_arg_max], 
                        dp[i][j - 1][1] + "-",
                        dp[i][j - 1][2] + y[j - 1])

def Needleman_Wunsch(s, t, n=1, m=-1, d=-2):
    dp = NW_init(s, t, d)
    
    for i in range(1, len(s) + 1):
        for j in range(1, len(t) + 1):
            w = [dp[i - 1][j - 1][0] + n if s[i - 1] == t[j - 1] else dp[i - 1][j - 1][0] + m, 
                 dp[i - 1][j][0] + d, 
                 dp[i][j - 1][0] + d]

            NW_update(dp, s, t, w, i, j)
                    
    return dp[-1][-1][1:]


def Align_weight(s, t, n=1, m=-1, d=-2):
    w_cur = [j * d for j in range(len(t) + 1)]
    
    for i in range(1, len(s) + 1):
        w_new = [i * d]

        for j in range(1, len(t) + 1):
            w = [w_cur[j - 1] + n if s[i - 1] == t[j - 1] else w_cur[j - 1] + m, 
                 w_cur[j] + d, 
                 w_new[j - 1] + d]

            w_new.append(max(w))

        w_cur = w_new
                    
    return w_cur


def Hirschberg(s, t, n=1, m=-1, d=-2):
    s_size = len(s)
    t_size = len(t)

    if s_size == 0:
        return "-" * t_size, t
    if t_size == 0:
        return s, "-" * s_size
    if s_size == 1 or t_size == 1:
        return Needleman_Wunsch(s, t, n, m, d)
    else:
        xmid = s_size // 2

        left = Align_weight(s[:xmid], t, n, m, d)
        right = Align_weight(s[xmid:][::-1], t[::-1], n, m, d)[::-1]
        # right = Align_weight(s[:xmid - 1:-1], t[::-1], n, m, d)[::-1]

        ymid = np.argmax([a + b for a, b in zip(left, right)])

        opt1 = Hirschberg(s[:xmid], t[:ymid], n, m, d)
        opt2 = Hirschberg(s[xmid:], t[ymid:], n, m, d)

        return opt1[0] + opt2[0], opt1[1] + opt2[1]


s1 = stdin.readline().strip()
s2 = stdin.readline().strip()

if len(s1) < len(s2):
    s1, s2 = s2, s1
    
print(*Hirschberg(s1, s2, n=2), sep="\n")

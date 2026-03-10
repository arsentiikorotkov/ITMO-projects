import numpy as np
from sys import stdin


def dp_init(s, t, p, q):
    dp = np.empty((len(s) + 1, len(t) + 1), dtype=object)
    dp.fill((0, "", "", 0))

    for i in range(1, len(s) + 1):
        dp[i][0] = (p + i * q, s[:i], "-" * i, 1)
    for j in range(1, len(t) + 1):
        dp[0][j] = (p + j * q, "-" * j, t[:j], 2)

    return dp

def dp_update(dp, x, y, w, i, j):
    w_arg_max = np.argmax(w)

    if w_arg_max == 0:
        dp[i][j] = (w[w_arg_max], 
                        dp[i - 1][j - 1][1] + x[i - 1],
                        dp[i - 1][j - 1][2] + y[j - 1], 
                        w_arg_max)
    elif w_arg_max == 1:
        dp[i][j] = (w[w_arg_max], 
                        dp[i - 1][j][1] + x[i - 1],
                        dp[i - 1][j][2] + "-",
                        w_arg_max)
    elif w_arg_max == 2:
        dp[i][j] = (w[w_arg_max], 
                        dp[i][j - 1][1] + "-",
                        dp[i][j - 1][2] + y[j - 1],
                        w_arg_max)

def alignment_gap_penalty(s, t, p, q, n=1, m=-1):
    dp = dp_init(s, t, p, q)
    
    for i in range(1, len(s) + 1):
        for j in range(1, len(t) + 1):
            w = [dp[i - 1][j - 1][0] + n if s[i - 1] == t[j - 1] else dp[i - 1][j - 1][0] + m, 
                 dp[i - 1][j][0] + p + q if dp[i - 1][j][3] != 1 else dp[i - 1][j][0] + q, 
                 dp[i][j - 1][0] + p + q if dp[i][j - 1][3] != 2 else dp[i][j - 1][0] + q]

            dp_update(dp, s, t, w, i, j)
                    
    return dp[-1][-1][1:3]

s1 = stdin.readline().strip()
s2 = stdin.readline().strip()
# p, q = list(map(int, stdin.readline().split()))

if len(s1) < len(s2):
    s1, s2 = s2, s1
    
print(*alignment_gap_penalty(s1, s2, 0, -2), sep="\n")

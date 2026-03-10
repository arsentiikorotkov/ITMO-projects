import numpy as np
from sys import stdin

def max_sub_str(x, y):
    dp = np.empty((len(x) + 1, len(y) + 1), dtype=object)
    dp.fill((0, ""))
    for i in range(1, len(x) + 1):
        for j in range(1, len(y) + 1):
            if x[i - 1] == y[j - 1]:
                dp[i][j] = (dp[i - 1][j - 1][0] + 1, dp[i - 1][j - 1][1] + x[i - 1])
            else:
                dp[i][j] = dp[i - 1][j] if dp[i - 1][j] >= dp[i][j - 1] else dp[i][j - 1]

    return dp[-1][-1][1]


s1 = stdin.readline().strip()
s2 = stdin.readline().strip()
    
print(max_sub_str(s1, s2))

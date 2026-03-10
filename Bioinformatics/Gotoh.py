import numpy as np
from sys import stdin


def mvh_init(s, t, p, q):
    m = np.zeros((len(s) + 1, len(t) + 1), dtype=int)
    v = np.zeros((len(s) + 1, len(t) + 1), dtype=int)
    h = np.zeros((len(s) + 1, len(t) + 1), dtype=int)

    for i in range(1, len(s) + 1):
        h[i][0] = p + i * q
        m[i][0] = p + i * q
    for j in range(1, len(t) + 1):
        v[0][j] = p + j * q
        m[0][j] = p + j * q

    return m, v, h

def alignment_gap_penalty(s, t, p, q, match=1, miss=-1):
    m, v, h = mvh_init(s, t, p, q)
    
    for i in range(1, len(s) + 1):
        for j in range(1, len(t) + 1):
            v[i][j] = max(v[i - 1][j] + q, m[i - 1][j] + p + q)
            h[i][j] = max(h[i][j - 1] + q, m[i][j - 1] + p + q)
            m[i][j] = max(v[i][j], h[i][j], m[i - 1][j - 1] + (match if s[i - 1] == t[j - 1] else miss))
                    
    return m[-1][-1]

s1 = stdin.readline().strip()
s2 = stdin.readline().strip()
p, q = list(map(int, stdin.readline().split()))

# if len(s1) < len(s2):
    # s1, s2 = s2, s1
    
print(alignment_gap_penalty(s1, s2, p, q), sep="\n")

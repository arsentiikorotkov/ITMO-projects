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
                    
    return m, v, h

def sequences_recovery(s, t, m, v, h, match=1, miss=-1):
    s_recovered, t_recovered = "", ""
    i, j = len(s), len(t)
    ind = 'm'

    while(i != 0 and j != 0):
        if ind == 'm':
            arg = np.argmax([v[i][j], h[i][j], m[i - 1][j - 1] + (match if s[i - 1] == t[j - 1] else miss)])
                            
            if arg == 0: ind = 'v'
            elif arg == 1: ind = 'h'
            elif arg == 2:
                s_recovered += s[i - 1]
                t_recovered += t[j - 1]

                i -= 1
                j -= 1

        elif ind == 'v':
            s_recovered += s[i - 1]
            t_recovered += '-'

            if v[i - 1][j] + q < m[i - 1][j] + p + q: ind = 'm'

            i -= 1

        elif ind == 'h':
            s_recovered += '-'
            t_recovered += t[j - 1]
            
            if h[i][j - 1] + q < m[i][j - 1] + p + q: ind = 'm'

            j -= 1  

    return '-' * j + s[:i] + s_recovered[::-1], '-' * i + t[:j] + t_recovered[::-1]


s1 = stdin.readline().strip()
s2 = stdin.readline().strip()
p, q = list(map(int, stdin.readline().split()))
    
print(*sequences_recovery(s1, s2, *alignment_gap_penalty(s1, s2, p, q)), sep="\n")

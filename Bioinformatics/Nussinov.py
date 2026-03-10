import numpy as np
from sys import stdin

def is_complementary(nucleotide_1, nucleotide_2):
    return nucleotide_1 + nucleotide_2 in ("AU", "UA", "CG", "GC")

def Nussinov(s):
    N = np.zeros((len(s), len(s)), dtype=int)

    for diff in range(2, len(s)):
        for i in range(len(s) - diff):
            j = i + diff

            N[i][j] = max(N[i + 1][j - 1] + 1 if is_complementary(s[i], s[j]) else -1,
                          *tuple(N[i][k] + N[k + 1][j] for k in range(i, j)))

    return N[0][len(s) - 1]


s = stdin.readline().strip()
    
print(Nussinov(s))

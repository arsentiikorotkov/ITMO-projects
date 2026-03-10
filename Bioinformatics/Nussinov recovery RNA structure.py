import numpy as np
from sys import stdin

def is_complementary(nucleotide_1, nucleotide_2):
    return nucleotide_1 + nucleotide_2 in ("AU", "UA", "CG", "GC")

def alternatives(s, N, i, j):
    return [N[i][k] + N[k + 1][j] for k in range(i, j)] + \
        [N[i + 1][j - 1] + 1 if is_complementary(s[i], s[j]) else -1]

def Nussinov(s):
    N = np.zeros((len(s), len(s)), dtype=int)

    for diff in range(2, len(s)):
        for i in range(len(s) - diff):
            j = i + diff

            N[i][j] = max(*alternatives(s, N, i, j))

    return N

def recovery_rna_structure(s, N, i, j):
    if j - i < 2:
        return '.' * (j - i + 1)

    recovered = ""

    arg = np.argmax(alternatives(s, N, i, j))

    if arg == j - i:
        return "(" + recovery_rna_structure(s, N, i + 1, j - 1) + ")"
    else:
        return recovery_rna_structure(s, N, i, i + arg) + recovery_rna_structure(s, N, i + arg + 1, j)


s = stdin.readline().strip()
    
print(recovery_rna_structure(s, Nussinov(s), 0, len(s) - 1))

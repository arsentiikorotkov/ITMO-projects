import numpy as np
from sys import stdin


A = np.array([[0.9, 0.1], 
              [0.2, 0.8]])

B = np.array([[0.4, 0.4, 0.1, 0.1], 
              [0.2, 0.2, 0.3, 0.3]])

S = np.array([0.5, 0.5])

open_to_num = {'C': 0, 'G': 1, 'A': 2, 'T': 3}


def Forward_Backward(O, A, B, S, open_to_num):
    alpha = np.full((len(S), len(O)), -1.0, dtype=float)
    beta = np.full((len(S), len(O)), -1.0, dtype=float)

    for i in range(len(S)): 
        alpha[i][0] = S[i] * B[i][open_to_num[O[0]]] 
        beta[i][-1] = 1

    for t in range(1, len(O)):
        for i in range(len(S)):
            alpha[i][t] = sum([alpha[k][t - 1] * A[k][i] * B[i][open_to_num[O[t]]] for k in range(len(S))])
            beta[i][-1 - t] = sum(beta[k][-t] * A[i][k] * B[k][open_to_num[O[-t]]] for k in range(len(S)))

    P_O = sum(alpha.T[-1])

    res = np.full((len(S), len(O)), -1.0, dtype=float)
    for i in range(len(S)):
        for t in range(len(O)):
            res[i][t] = round(alpha[i][t] * beta[i][t] / P_O, 2)

    return res


O = stdin.readline().strip()

promoter, non_promoter = Forward_Backward(O, A, B, S, open_to_num)

print(*promoter)
print(*non_promoter)

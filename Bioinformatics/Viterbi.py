import numpy as np
from sys import stdin
from numpy.linalg import matrix_power


A = np.array([[0.9, 0.1], 
              [0.2, 0.8]])

B = np.array([[0.4, 0.4, 0.1, 0.1], 
              [0.2, 0.2, 0.3, 0.3]])

S = np.array([0.5, 0.5])

num_to_hidden = {0: 'P', 1: 'N'}
open_to_num = {'C': 0, 'G': 1, 'A': 2, 'T': 3}


def init_delta(n, m):
    delta = np.empty((n, m), dtype=object)
    delta.fill((-1, -1))

    return delta

def hidden_seq(delta, seq_size, num_to_hidden):
    res = np.full((seq_size), -1, dtype=int)

    res[-1] = np.argmax(delta.T[-1])
    for i in range(seq_size - 1):
        res[-2 - i] = delta[res[-1 - i]][-1 - i][1]

    return ''.join(map(lambda num: num_to_hidden[num], res))

def Viterbi(O, A, B, S, open_to_num, num_to_hidden):
    delta = init_delta(len(S), len(O))

    for i in range(len(S)): delta[i][0] = (S[i] * B[i][open_to_num[O[0]]], -1)

    for t in range(1, len(O)):
        for i in range(len(S)):
            arr = [delta[k][t - 1][0] * A[k][i] * B[i][open_to_num[O[t]]] for k in range(len(S))]
            delta[i][t] = (max(arr), np.argmax(arr))

    return hidden_seq(delta, len(O), num_to_hidden)


O = stdin.readline().strip()

print(Viterbi(O, A, B, S, open_to_num, num_to_hidden))

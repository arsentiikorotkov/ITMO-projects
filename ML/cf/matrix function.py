import numpy as np
from sys import stdin


def get_opers_and_args(N):
    opers = []
    args = []
    for _ in range(N):
        s = stdin.readline().split()

        opers.append(s[0])
        args.append(list(map(int, s[1:])))

    return opers, args

def get_matrixes(M, args):
    matrixes = []
    for i in range(M):
        n, m = args[i]

        matrix = []
        for _ in range(n):
            matrix.append(list(map(int, stdin.readline().split())))
        
        matrixes.append(np.array(matrix))

    return matrixes

def compute_values(opers, args, matrixes):
    for i in range(M, N):
        matrix = []

        if opers[i] == "tnh":
            matrix = np.tanh(matrixes[args[i][0] - 1])
        elif opers[i] == "rlu":
            temp = matrixes[args[i][1] - 1]
            matrix = np.where(temp >= 0, temp, temp / args[i][0])
        elif opers[i] == "mul":
            if matrixes[args[i][0] - 1].shape[1] == matrixes[args[i][1] - 1].shape[0]:
                matrix = np.dot(matrixes[args[i][0] - 1], matrixes[args[i][1] - 1])
            else:
                matrix = np.dot(matrixes[args[i][1] - 1], matrixes[args[i][0] - 1])
        elif opers[i] == "sum":
            matrix = np.array(sum([matrixes[args[i][1 + k] - 1] for k in range(args[i][0])]))
        elif opers[i] == "had":
            matrix = np.prod([matrixes[args[i][1 + k] - 1] for k in range(args[i][0])], axis=0)

        matrixes.append(matrix)

def get_derivatives(matrixes):
    derivatives = []
    for i in range(N - K):
        derivatives.append(np.zeros(matrixes[i].shape))
    for i in range(K):
        n, m = matrixes[N - K + i].shape

        matrix = []
        for _ in range(n):
            matrix.append(list(map(int, stdin.readline().split())))
        
        derivatives.append(np.array(matrix))

    return derivatives

def compute_derivatives(opers, args, matrixes, derivatives):
    for i in range(N - 1, -1, -1):
        if opers[i] == "tnh":
            index = args[i][0] - 1
            derivatives[index] += derivatives[i] * (1 - matrixes[i] ** 2)
        elif opers[i] == "rlu":
            derivatives[args[i][1] - 1] += derivatives[i] * (np.where(matrixes[args[i][1] - 1] >= 0, 1, 1 / args[i][0]))
        elif opers[i] == "mul":
            index_1 = args[i][0] - 1
            index_2 = args[i][1] - 1
            if matrixes[index_1].shape[1] == matrixes[index_2].shape[0]:
                derivatives[index_1] += np.dot(derivatives[i], matrixes[index_2].T)
                derivatives[index_2] += np.dot(matrixes[index_1].T, derivatives[i])
            else:
                derivatives[index_1] += np.dot(matrixes[index_2].T, derivatives[i])
                derivatives[index_2] += np.dot(derivatives[i], matrixes[index_1].T)            
        elif opers[i] == "sum":
            for k in range(args[i][0]):
                derivatives[args[i][1 + k] - 1] += derivatives[i]
        elif opers[i] == "had":
            for k in range(args[i][0]):
                index = args[i][1 + k] - 1
                matrix = np.prod([matrixes[args[i][1 + j] - 1] for j in range(args[i][0]) if j != k], axis=0)
                derivatives[index] += derivatives[i] * matrix

def matrix_function(N, M, K):
    opers, args = get_opers_and_args(N)

    matrixes = get_matrixes(M, args)
    compute_values(opers, args, matrixes)

    derivatives = get_derivatives(matrixes)
    compute_derivatives(opers, args, matrixes, derivatives)

    for matrix in matrixes[-K:]: 
        for str in matrix: print(*list(str))
            
    for derivative in derivatives[:M]: 
        for str in derivative: print(*list(str))


N, M, K = map(int, stdin.readline().split())


matrix_function(N, M, K)
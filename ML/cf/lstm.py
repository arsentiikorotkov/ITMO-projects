import numpy as np
from sys import stdin


FORMULAS_NAMES = ['f', 'l', 'o', 'c']


def input_vector(): return np.array(list(map(float, stdin.readline().split())))

def input_matrix(n):
    matrix = []
    for _ in range(n): matrix.append(list(map(float, stdin.readline().split())))

    return np.array(matrix)

def input():
    data = {'N': int(stdin.readline().strip())}

    data.update({'W': {}, 'U': {}, 'b': {}})

    for ch in FORMULAS_NAMES:
        data['W'][ch] = input_matrix(data['N'])
        data['U'][ch] = input_matrix(data['N'])
        data['b'][ch] = input_vector()

    data.update({'M': int(stdin.readline().strip())})

    data.update({'h_0': input_vector(), 'c_0': input_vector(), 
            'x': input_matrix(data['M']), 'dL_dh_m': input_vector(), 
            'dL_dc_m': input_vector(), 'dL_do': input_matrix(data['M'])[::-1]})
    
    return data


def sigmoid(x): return 1 / (1 + np.exp(-x))

def compute_arg(data, ch, t): 
    return data['W'][ch] @ data['x'][t] + data['U'][ch] @ data['h'][t] + data['b'][ch]

def forward_pass(data):
    shape_flo, shape_ch = (data['M'], data['N']), (data['M'] + 1, data['N'])
    data.update({'f': np.zeros(shape_flo), 'l': np.zeros(shape_flo), 'o': np.zeros(shape_flo), 
                 'c': np.zeros(shape_ch), 'h': np.zeros(shape_ch)})

    data['c'][0] = data['c_0']
    data['h'][0] = data['h_0']

    for t in range(data['M']):
        data['f'][t] = sigmoid(compute_arg(data, 'f', t))
        data['l'][t] = sigmoid(compute_arg(data, 'l', t))
        data['o'][t] = sigmoid(compute_arg(data, 'o', t))

        data['c'][t + 1] = data['f'][t] * data['c'][t] + data['l'][t] * np.tanh(compute_arg(data, 'c', t))
        data['h'][t + 1] = data['o'][t] * data['c'][t + 1]


def d_tanh(tanh_arg): return 1 - np.tanh(tanh_arg) ** 2

def d_sigmoid(sigmoid_value): return sigmoid_value * (1 - sigmoid_value)

def backward_pass(data):
    M, N = data['M'], data['N']
    shape_d_xfl, shape_d_ch, shape_d_WU = (M, N), (M + 1, N), (N, N)

    data.update({'dL_dW': {ch: np.zeros(shape_d_WU) for ch in FORMULAS_NAMES}, 
                 'dL_dU': {ch: np.zeros(shape_d_WU) for ch in FORMULAS_NAMES}, 
                 'dL_db': {ch: np.zeros(N) for ch in FORMULAS_NAMES}, 
                 'dL_dx': np.zeros(shape_d_xfl), 'dL_df': np.zeros(shape_d_xfl), 
                 'dL_dl': np.zeros(shape_d_xfl), 'dL_dc': np.zeros(shape_d_ch), 
                 'dL_dh': np.zeros(shape_d_ch)})

    data['dL_dh'][M] =  data['dL_dh_m']
    data['dL_dc'][M] = data['dL_dc_m'] + data['dL_dh'][M] * data['o'][M - 1]
    
    for t in range(M - 1, -1, -1):
        data['dL_df'][t] = data['dL_dc'][t + 1] * data['c'][t]
        data['dL_dl'][t] = data['dL_dc'][t + 1] * np.tanh(compute_arg(data, 'c', t))
        data['dL_do'][t] = data['dL_do'][t] + data['dL_dh'][t + 1] * data['c'][t + 1]

        d_vector_components = \
            {'f': d_sigmoid(data['f'][t]) * data['dL_df'][t], 
            'l': d_sigmoid(data['l'][t]) * data['dL_dl'][t], 
            'o': d_sigmoid(data['o'][t]) * data['dL_do'][t], 
            'c': data['l'][t] * d_tanh(compute_arg(data, 'c', t)) * data['dL_dc'][t + 1]}

        data['dL_dx'][t] = sum([data['W'][ch].T @ d_vector_components[ch] for ch in FORMULAS_NAMES])
        data['dL_dh'][t] = sum([data['U'][ch].T @ d_vector_components[ch] for ch in FORMULAS_NAMES])
        data['dL_dc'][t] = data['dL_dc'][t + 1] * data['f'][t] + (data['dL_dh'][t] * data['o'][t - 1] if t > 0 else 0)

        for ch in FORMULAS_NAMES: 
            data['dL_dW'][ch] += np.outer(d_vector_components[ch], data['x'][t])
            data['dL_dU'][ch] += np.outer(d_vector_components[ch], data['h'][t])
            data['dL_db'][ch] += d_vector_components[ch]


def print_matrix(m): 
    for raw in m: print(*raw)

def print_vector(v):
    print(*v)

def print_lstm_result(data):
    print_matrix(data['o'])
    print_vector(data['h'][data['M']])
    print_vector(data['c'][data['M']])

    print_matrix(data['dL_dx'][::-1])
    print_vector(data['dL_dh'][0])
    print_vector(data['dL_dc'][0])

    for ch in FORMULAS_NAMES:
        print_matrix(data['dL_dW'][ch])
        print_matrix(data['dL_dU'][ch])
        print_vector(data['dL_db'][ch])


def lstm(data):
    forward_pass(data)
    backward_pass(data)
    print_lstm_result(data)


lstm(input())
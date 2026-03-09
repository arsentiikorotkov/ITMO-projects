import numpy as np
from sys import stdin


def get_values(size):
    values = []
    for _ in range(size): values.append(int(stdin.readline().strip()))

    inv = sum(values) <= (size / 2)
    if inv: values = list(map(lambda x: 1 if not x else 0, values))

    return values, inv, size - sum(values)

def one_layer(M, inv):
    print(1)
    print(1)
    if not inv: print("0.0 " * M + "0.5")
    else: print("0.0 " * M + "-0.5")

def two_layers(values, inv, n_1):
    print(2)
    print(f"{n_1} 1")

    for i in range(len(values)):
        if not values[i]:
            binary = list(map(int, list(bin(i)[:1:-1])))
            binary = binary + [0] * (M - len(binary))

            w = ' '.join(map(lambda x: "-1.0" if x == 1 else "1.0", binary))

            print(f"{w} {sum(binary) - 0.5}")

    if not inv: print("1.0 " * n_1 + f"{0.5 - n_1}")
    else: print("-1.0 " * n_1 + f"{n_1 - 0.5}")


def log_expr(M):
    values, inv, n_1 = get_values(2 ** M)

    if (n_1 == 0): return one_layer(M, inv)

    two_layers(values, inv, n_1)


M = int(stdin.readline().strip())


log_expr(M)
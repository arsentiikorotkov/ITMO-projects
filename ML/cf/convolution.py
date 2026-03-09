import numpy as np
from sys import stdin
from enum import Enum
from math import exp


LearningRateScheduling = Enum('LearningRateScheduling', ['Classic', 'Stepwise', 'Exponential'])
Regularization = Enum('Regularization', ['WithoutRegularization', 'L1', 'L2', 'Elastic'])


def lrs_exp(decay):
    return lambda lr, epoch: lr * exp(-decay * epoch)

def lrs_step(decay, epoch_update):
    return lambda lr, epoch: lr * (decay ** (epoch // epoch_update))

def lrs_handler(lrs, epoch_update=10):
    if lrs == LearningRateScheduling.Classic:
        return lambda lr, epoch: lr
    elif lrs == LearningRateScheduling.Stepwise:
        return lrs_step(0.75, epoch_update)
    elif lrs == LearningRateScheduling.Exponential:
        return lrs_exp(0.1)


def sign(x):
    if x > 0: return 1
    elif x == 0: return 0
    else: return -1


class Convolution:
    def __init__(self, core, input, output, regularization=Regularization.WithoutRegularization, l1=0.1, l2=0.1):
        self.core = core
        self.input = input
        self.output = output
        self.n = input.shape[0]
        self.m = output.shape[0]
        self.k = self.n - self.m + 1
        self.regularization = regularization
        self.l1 = l1
        self.l2 = l2

    def f(self, core):
        output = np.zeros((self.m, self.m))    

        for i in range(self.m):
            for j in range(self.m):
                output[i][j] = np.dot(self.input[i:i + self.k, j:j + self.k].reshape(self.k ** 2), core)

        return output   

    def loss(self, core): 
        result = np.sum((self.output - self.f(core)) ** 2)

        if self.regularization == Regularization.L1:
            result += self.l1 * sum([abs(x) for x in core])
        elif self.regularization == Regularization.L2:
            result += self.l2 * sum([x ** 2 for x in core])
        elif self.regularization == Regularization.Elastic:
            result += (self.l1 * sum([abs(x) for x in core])) + (self.l2 * sum([x ** 2 for x in core]))

        return result

    def grad_by_components(self, index_components, core):
        grad_with_batch = np.zeros(core.shape)

        for index in index_components:
            i = index // self.m
            j = index % self.m
            component = self.input[i:i + self.k, j:j + self.k].reshape(self.k ** 2)
            
            grad_with_batch += (2 * (np.dot(component, core) - self.output[i][j]) * component)

        if self.regularization == Regularization.L1:
            grad_with_batch += self.l1 * np.array([np.sign(x) for x in core])
        elif self.regularization == Regularization.L2:
            grad_with_batch += self.l2 * 2 * core
        elif self.regularization == Regularization.Elastic:
            grad_with_batch += (self.l1 * np.array([np.sign(x) for x in core])) + (self.l2 * 2 * core)

        return grad_with_batch


def sgd(convolution, lr, lrs, batch, max_num_of_step, beta_1, beta_2, eps_adam, 
        is_corr_beta_1=True, is_corr_beta_2=True):
    i = -1
    V = np.zeros(convolution.core.shape)
    S = np.zeros(convolution.core.shape)
    lrs_func = lrs_handler(lrs)

    while True:
        i += 1

        components = [(i * batch + j) % (convolution.m ** 2) for j in range(batch)]
        grad_with_batch = convolution.grad_by_components(components, convolution.core)

        alpha = lrs_func(lr(lambda a: convolution.loss(convolution.core - a * grad_with_batch)), (i * batch) // (convolution.m ** 2))

        V = (beta_1 * V) + (1 - beta_1) * grad_with_batch
        S = (beta_2 * S) + (1 - beta_2) * (grad_with_batch ** 2)
        V_norm = V / (1 - beta_1 ** (i + 1)) if is_corr_beta_1 else V
        S_norm = S / (1 - beta_2 ** (i + 1)) if is_corr_beta_2 else S

        convolution.core = convolution.core - alpha * (V_norm / ((S_norm + eps_adam) ** 0.5))

        # if np.all(convolution.f(convolution.core) - convolution.output) < 1e-6: break
        if convolution.loss(convolution.core) < 1e-12: break
        # if i >= max_num_of_step: break

    return convolution.core


def get_matrix(n):
    matrix = []
    for _ in range(n): matrix.append(list(map(int, stdin.readline().split())))

    return np.array(matrix)

def get_core(n, m):
    input = get_matrix(n)
    output = get_matrix(m)

    convolution = Convolution(np.random.normal(size=(n - m + 1) ** 2), input, output, regularization=Regularization.WithoutRegularization)
    res = sgd(convolution, lambda *args: 0.05, LearningRateScheduling.Classic, m ** 2, 15000, 0.9, 0.999, 10 ** -8)

    for s in res.reshape((convolution.k, convolution.k)): print(*s)


n, m = map(int, stdin.readline().split())


get_core(n, m)
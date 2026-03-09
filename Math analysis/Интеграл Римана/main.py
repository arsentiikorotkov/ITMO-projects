
import math
import random
import numpy as np
import matplotlib.pyplot as plt
from array import *

n = int(input("Введите натуральное число: "));
s = input("Выберите и введите один из типов оснащения (left, right, middle, random): ");

sum = 0;
c = 6 / math.log(2, math.e);

x = array('f', []);
y = array('f', []);

if s == "left" :
    for i in range (0, n) :
        v = i / n;
        sum += pow(4, v) * (4 / n);
        x.insert(i, 1 + ((i + 0.5) / n));
        y.insert(i, sum);
elif s == "right" :
    for i in range (1, n + 1) :
        v = i / n;
        sum += pow(4, v) * (4 / n);
        x.insert(i - 1, 1 + ((i - 0.5) / n));
        y.insert(i - 1, sum);
elif s == "middle" :
    for i in range (1, n + 1) :
        v = (i - (1 / 2)) / n;
        sum += pow(4, v) * (4 / n);
        x.insert(i - 1, 1 + ((i - 0.5) / n));
        y.insert(i - 1, sum);
elif s == "random" :
    for i in range (0, n) :
        v = (i + random.random()) / n;
        sum += pow(4, v) * (4 / n);
        x.insert(i, 1 + ((i + 0.5) / n));
        y.insert(i, sum);

print("\n" + "Значение интегральной суммы: " + str(y[n - 1]));
print("Модуль разности значения интеграла и интегральной суммы: " + str(math.fabs(c - y[n - 1])));

xr = array('f', []);
yr = array('f', []);

for i in range(0, 1001):
    xr.insert(i, 1 + (i / 1000));
    yr.insert(i, (pow(4, xr[i]) - 4) / math.log(4, math.e));

fig, ax = plt.subplots();
ax.set_title("График значений интегральных сумм: ", fontsize = 20)
ax.set_xlabel("Ось x", fontsize = 15);
ax.set_ylabel("Ось y", fontsize = 15);
ax.set_xticks(np.arange(1, 2.1, 0.1));
ax.set_yticks(np.arange(0, 9.5, 1));
ax.grid(linewidth = 1);
ax.bar(x, y, color = 'lightskyblue', width = 1 / n, label = "Интегральные суммы");
ax.plot(xr, yr, color = 'darkblue', label = "Интеграл с переменным верхним пределом");
ax.legend(loc = 'best');
plt.show();

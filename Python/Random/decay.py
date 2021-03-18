import matplotlib.pyplot as plt
import random
from math import *

N = int(input('Number of particles: '))
rate = float(input('Decay rate: '))

num = []
num.append(N)
t = 0

while N > 0:
    tmp = N
    for i in range(tmp):
        r = random.random()
        if r <= rate:
            N -= 1
    t += 1
    num.append(N)

num = num[:-1]


for i in range(len(num)):
    num[i] = log(abs(num[i]))


plt.figure()
plt.plot(range(t), num)
plt.title('Decay')
plt.xlabel('$t$')
plt.ylabel('$N$')
plt.show()
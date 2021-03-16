import matplotlib.pyplot as plt 
import random

def rnd(a, c, M, r):
    r.append((a * r[-1] + c) % M)

a = 57
c = 1
M = 256
r_1 = 10

r = []
r.append(r_1)

rnd(a, c, M, r)
count = 1
while r[-1] != r[0]:
    rnd(a, c, M, r)
    count += 1
    if count > 300:
        break

x = []
y = []

for i in range(0, count - 1, 2):
    x.append(r[i])
    y.append(r[i + 1])



plt.scatter(x, y, color='black')
plt.title('Linear congruent')
plt.xlabel('$r_i$')
plt.ylabel('$r_{i+1}$')

plt.figure()
plt.plot(range(count // 2), r[:count//2], color='black')
plt.title('Random number over iteration')
plt.xlabel('$i$')
plt.ylabel('$r_i$')

real_r = []

for i in range(len(r)):
    real_r.append(random.randint(0, 255))

x = []
y = []

for i in range(0, count - 1, 2):
    x.append(real_r[i])
    y.append(real_r[i + 1])

plt.figure()
plt.scatter(x, y, color='red', marker='x')
plt.title('Built-in function')
plt.xlabel('$r_i$')
plt.ylabel('$r_{i+1}$')

plt.show()
import matplotlib.pyplot as plt
from math import *

def fd(x, y, h):
    return (y(x + h) - y(x)) / h

def cd(x, y, h):
    return (y(x + h/2) - y(x - h/2)) / h

def ed(x, y, h):
    return (8 * (y(x+h/4) - y(x-h/4)) - (y(x+h/2) - y(x-h/2)))/3/h

def prec():
    eps = 1.0
    while (0.5 * eps + 1.0) != 1.0:
        eps /= 2
    return eps


f = []
c = []
e = []

h = 1
eps = prec()
x = float(input('x: '))
history = []
while h > eps:
    history.append(h)
    f.append(abs((fd(x, lambda t: cos(t), h) + sin(x))/sin(x)))
    c.append(abs((cd(x, lambda t: cos(t), h) + sin(x))/sin(x)))
    e.append(abs((ed(x, lambda t: cos(t), h) + sin(x))/sin(x)))
    h = h / 2

plt.figure()
plt.subplots_adjust(hspace=1)
plt.subplot(3, 1, 1)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Forward difference (cos)')
plt.plot(history, f)
plt.subplot(3, 1, 2)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Central difference (cos)')
plt.plot(history, c)
plt.subplot(3, 1, 3)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Extrapolated difference (cos)')
plt.plot(history, e)

h = 1
f = []
c = []
e = []
history = []
while h > eps:
    history.append(h)
    f.append(abs((fd(x, lambda t: exp(t), h) - exp(x))/exp(x)))
    c.append(abs((cd(x, lambda t: exp(t), h) - exp(x))/exp(x)))
    e.append(abs((ed(x, lambda t: exp(t), h) - exp(x))/exp(x)))
    h = h / 2

plt.figure()
plt.subplots_adjust(hspace=1)
plt.subplot(3, 1, 1)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Forward difference (exp)')
plt.plot(history, f)
plt.subplot(3, 1, 2)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Central difference (exp)')
plt.plot(history, c)
plt.subplot(3, 1, 3)
plt.yscale('log')
plt.xscale('log')
plt.xlabel('$h$')
plt.ylabel('$\epsilon$')
plt.title('Extrapolated difference (exp)')
plt.plot(history, e)
plt.show()
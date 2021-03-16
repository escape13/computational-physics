from vpython import *
import random
from math import *

num_walks = int(input('Number of walks: '))
N = int(input('Number of steps: '))

lim = sqrt(N)*1.4

window = graph(width=500, height=500,
                title='<b>Random walk</b>',
                xtitle='x', ytitle='y', fast=False,
                xmin=-lim, xmax=lim, ymin=-lim, ymax=lim)



walks = []
errors = []
x = [0] * num_walks
y = [0] * num_walks

for i in range(num_walks):
    r = random.random()
    g = random.random()
    b = random.random()
    walks.append(gcurve(color=vec(r, g, b)))


error_window = graph(width=2000, height=1000,
                    title='<b>Error to predicted value</b>',
                    xtitle='sqrt(N)', ytitle='R', fast=False)

for i in range(num_walks):
    r = random.random()
    g = random.random()
    b = random.random()
    errors.append(gcurve(color=vec(r, g, b)))

for i in range(N + 1):
    for j in range(num_walks):
        walks[j].plot(pos=(x[j], y[j]))
        errors[j].plot(pos=(sqrt(i), sqrt(x[j]**2 + y[j]**2)))
        x[j] += (random.random() - 0.5)*2
        y[j] += (random.random() - 0.5)*2
    

r = []
for i in range(num_walks):
    r.append(x[i]**2 + y[i]**2)

print('Calculated average distance: ', sqrt(sum(r)/len(r)))
print('Predicted average distance: ', sqrt(N))

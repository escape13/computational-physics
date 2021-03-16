from vpython import *
import random

N = int(input('Number of steps: '))

window = graph(width=700, height=700,
                title='Self Avoiding Random Walk', fast=False)

h = []
p = []
#e = []
connections = []

route = gcurve(color=color.black, width=4)
h_dots = gdots(color=color.blue, radius=7)
p_dots = gdots(color=color.red, radius=7)
#energy = gdots(color=color.green, radius=5)

x = 0
y = 0

for i in range(N):
    route.plot(x, y)
    h_chance = random.random()
    if h_chance <= 0.8:
        h_dots.plot([x, y])
        h.append([x, y])
        #tmp = [[x - 0.5, y], [x + 0.5, y], [x, y - 0.5], [x, y + 0.5]]
        #energy.plot(tmp)
        #e.extend(tmp)
    else:
        p_dots.plot([x, y])
        p.append([x, y])
    free = []
    if [x + 1, y] not in h and [x + 1, y] not in p:
        free.append([x + 1, y])
    if [x - 1, y] not in h and [x - 1, y] not in p:
        free.append([x - 1, y])
    if [x, y + 1] not in h and [x, y + 1] not in p:
        free.append([x, y + 1])
    if [x, y - 1] not in h and [x, y - 1] not in p:
        free.append([x, y - 1])
    print(free)
    if len(free) == 0:
        break
    
    connections.append([[x, y]])
    [x, y] = random.choice(free)
    connections[-1].append([x, y])
    rate(2)
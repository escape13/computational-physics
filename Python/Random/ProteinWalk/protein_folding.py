from vpython import *
import random

N = int(input('Number of steps: '))

window = graph(width=700, height=700,
                title='Self Avoiding Random Walk', fast=False)

h = []
p = []
e = []
connections = []

route = gcurve(color=color.black, width=4)
h_dots = gdots(color=color.blue, radius=7)
p_dots = gdots(color=color.red, radius=7)
energy = gdots(color=color.green, radius=5)

x = 0
y = 0

for i in range(N):
    route.plot(x, y)
    h_chance = random.random()
    if h_chance <= 0.8:
        h_dots.plot([x, y])
        h.append([x, y])
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

    if len(free) == 0:
        break
    
    connections.append([[x, y]])
    [x, y] = random.choice(free)
    connections[-1].append([x, y])
    rate(3)

for hydro in h:
    [x, y] = hydro
    if [x + 1, y] in h and [[x, y], [x + 1, y]] not in connections and [[x + 1, y], [x, y]] not in connections:
        if [x + 0.5, y] not in e:
            e.append([x + 0.5, y])
            energy.plot([x + 0.5, y])
    if [x - 1, y] in h and [[x, y], [x - 1, y]] not in connections and [[x - 1, y], [x, y]] not in connections:
        if [x - 0.5, y] not in e:
            e.append([x - 0.5, y])
            energy.plot([x - 0.5, y])
    if [x, y + 1] in h and [[x, y], [x, y + 1]] not in connections and [[x, y + 1], [x, y]] not in connections:
        if [x, y + 0.5] not in e:
            e.append([x, y + 0.5])
            energy.plot([x, y + 0.5])
    if [x, y - 1] in h and [[x, y], [x, y - 1]] not in connections and [[x, y - 1], [x, y]] not in connections:
        if [x, y - 0.5] not in e:
            e.append([x, y - 0.5])
            energy.plot([x, y - 0.5])

print('Number of H-H bonds: ', len(e))
print('Chain length: ', len(h) + len(p))
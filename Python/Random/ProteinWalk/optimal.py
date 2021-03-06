from vpython import *
import random
import time

N = int(input('Number of steps: '))
prob = float(input('Probability of an H residue: '))

final_start = time.time()

num_bonds = []
times = [] 

final_cons = []
final_h = []
final_p = []
final_e = []

for sims in range(5000, 100000, 5000):
    

    best_h = []
    best_p = []
    best_e = []
    best_cons = []

    for sim in range(sims):
        h = []
        p = []
        e = []
        connections = []

        x = 0
        y = 0

        for i in range(N):
            h_chance = random.random()
            if h_chance <= prob:
                h.append([x, y])
            else:
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
                continue
        
            connections.append([[x, y]])
            [x, y] = random.choice(free)
            connections[-1].append([x, y])
        
        for hydro in h:
            [x, y] = hydro
            if [x + 1, y] in h and [[x, y], [x + 1, y]] not in connections and [[x + 1, y], [x, y]] not in connections:
                if [x + 0.5, y] not in e:
                    e.append([x + 0.5, y])
                
            if [x - 1, y] in h and [[x, y], [x - 1, y]] not in connections and [[x - 1, y], [x, y]] not in connections:
                if [x - 0.5, y] not in e:
                    e.append([x - 0.5, y])
            
            if [x, y + 1] in h and [[x, y], [x, y + 1]] not in connections and [[x, y + 1], [x, y]] not in connections:
                if [x, y + 0.5] not in e:
                    e.append([x, y + 0.5])
                
            if [x, y - 1] in h and [[x, y], [x, y - 1]] not in connections and [[x, y - 1], [x, y]] not in connections:
                if [x, y - 0.5] not in e:
                    e.append([x, y - 0.5])
                
        if len(e) >= len(best_e) and len(h) + len(p) == N:
            best_h = h
            best_p = p
            best_e = e
            best_cons = connections

    
    if len(best_e) >= len(final_e):
        final_e = best_e
        final_cons = best_cons
        final_h = best_h
        final_p = best_p
 
final_end = time.time()

print('Time elapsed: ', final_end - final_start, ' s')
print('H-H connections: ', len(final_e))

window = graph(width=700, height=700, fast=False, title='Protein conformation with lowest potential energy')

route = gcurve(color=color.black, width=4)
h_dots = gdots(color=color.blue, radius=7)
p_dots = gdots(color=color.red, radius=7)
energy = gdots(color=color.green, radius=5)

if [0, 0] in final_h:
    h_dots.plot([0, 0])
else:
    p_dots.plot([0, 0])

route.plot([0, 0])


for con in final_cons:
    if con is not final_cons[-1]:
        route.plot(con[-1])
        if con[-1] in final_h:
            h_dots.plot(con[-1])
        elif con is not final_cons[-1]:
            p_dots.plot(con[-1])

    rate(8)
    


for en in final_e:
    energy.plot(en)


import random
import time
import matplotlib.pyplot as plt

N = int(input('Number of steps: '))
prob = float(input('Probability of an H residue: '))

num_bonds = []
times = [] 

for sims in range(5000, 100000, 5000):
    start = time.time()

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

    end = time.time()
    times.append(end - start)
    num_bonds.append(len(best_e))


plt.figure()
plt.title('Calculation time vs Number of iterations')
plt.xlabel('Iterations')
plt.ylabel('Calculation time')
plt.plot(range(5000, 100000, 5000), times)
plt.savefig(f'/Users/timurvaleev/Desktop/dump/time{N}.png')

plt.figure()
plt.title('No. of H-H bonds vs Number of iterations')
plt.xlabel('Iterations')
plt.ylabel('H-H bonds')
plt.plot(range(5000, 100000, 5000), num_bonds)
plt.savefig(f'/Users/timurvaleev/Desktop/dump/bonds{N}.png')

plt.show()
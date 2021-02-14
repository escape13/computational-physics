import matplotlib.pyplot as plt
from math import sin, cos, pi

g = -9.8
x = 0
y = 0
t = 0
v = float(input('Velocity: '))
a = float(input('Angle (degrees): ')) * pi / 180
dt = float(input('Time interval: '))
vx = v * cos(a)
vy = v * sin(a)

plt.title('Projectile motion')
plt.xlabel('x')
plt.ylabel('y')

x_values = [x]
y_values = [y]

while y >= 0:
    vy += g * dt
    x += vx * dt
    x_values.append(x)
    y += vy * dt
    y_values.append(y)
    
plt.plot(x_values, y_values)
plt.axis('equal')
plt.show()

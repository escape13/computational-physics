from numpy.linalg import solve
from numpy import *
from vpython import *

scene = canvas(x=0, y=0, width=500, height=500, title='String and masses configuration')
tmp = curve(x=range(0, 500), color=color.black)

n = 9
eps = 1e-3
deriv = zeros((n,n), float)
f = zeros((n), float)
x = array([0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 1, 1, 1])

def plotconfig():
    for obj in scene.objects:
        obj.visible = 0
    L1 = 3
    L2 = 4
    L3 = 4
    xa = L1 * x[3]
    ya = L1 * x[0]
    xb = xa + L2 * x[4]
    yb = xa + L2 * x[1]
    xc = xb + L3 * x[5]
    yc = xb - L3 * x[2]
    mx = 100
    bx = -500
    my = -100
    by = 500
    xap = mx * xa + bx
    yap = my * ya + by
    ball1 = sphere(pos=vec(xap, yap, 0), color=color.cyan, radius=15)
    xbp = mx * xb + bx
    ybp = my * yb + by
    ball2 = sphere(pos=vec(xbp, ybp, 0), color=color.cyan, radius=25)
    xcp = mx * xc + bx
    ycp = mx * yc + by
    x0 = bx
    y0 = by
    line1 = curve(pos=[vec(x0, y0, 0), vec(xap, yap, 0)], color=color.yellow, radius=4)
    line2 = curve(pos=[vec(xap, yap, 0), vec(xbp, ybp, 0)], color=color.yellow, radius=4)
    line3 = curve(pos=[vec(xbp, ybp, 0), vec(xcp, ycp, 0)], color=color.yellow, radius=4)
    topline = curve(pos=[vec(x0, y0, 0), vec(xcp, ycp, 0)], color=color.red, radius=4)

def F(x, f):
    f[0] = 3*x[3] + 4*x[4] + 4*x[5] - 8
    f[1] = 3*x[0] + 4*x[1] - 4*x[2]
    f[2] = x[6]*x[0] - x[7]*x[1] - 10
    f[3] = x[6]*x[3] - x[7]*x[4]
    f[4] = x[7]*x[1] + x[8]*x[2] - 20
    f[5] = x[7]*x[4] - x[8]*x[5]
    f[6] = pow(x[0], 2) + pow(x[3], 2)
    f[7] = pow(x[1], 2) + pow(x[4], 2)
    f[8] = pow(x[2], 2) + pow(x[5], 2)

def dFi_dXj(x, deriv, n):
    h = 1e-4
    tmp = 
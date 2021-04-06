from numpy.linalg import *
from numpy import *

I = array([[2./3, -1./4], [-1./4, 2./3]])

Es, evectors = eig(I)

print(Es)
print(evectors)
print()

LHS = dot(I, evectors[:, 0])
RHS = Es[0]*evectors[:, 0]
print(LHS - RHS)

LHS = dot(I, evectors[:, 1])
RHS = Es[1]*evectors[:, 1]
print(LHS - RHS)
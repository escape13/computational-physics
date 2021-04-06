from numpy import *
from numpy.linalg import *

A = array([[1, 2, 3], [22, 32, 42], [55, 66, 100]])
b = array([1, 2, 3])

x = solve(A, b)
print(x)
print(dot(A, x) - b)
print()

x = dot(inv(A), b)
print(x)
print(dot(A, x) - b)
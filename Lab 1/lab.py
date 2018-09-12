import types

def isInside(v, e):
    if v == e:
        return True
    elif type(e) == tuple: #There's GOT to be a better way to do this
        if isInside(v, e[0]) or isInside(v, e[2]):
            return True
        else:
            return False
    else:
        return False

def solve(v, q):
    if isInside(v, q[0]):
        return solving(v, q)
    elif isInside(v, q[2]):
        flippedQ = (q[2], q[1], q[0])
        return solving(v, flippedQ)
    else:
        return None

def solving(v, q):
    #v/x will be in the left side, middle will be =, right will be something
    if v == q[0]:
        return q # already solved
    elif q[0][1] == "+":
        return solvingAddition(v, q)
    elif q[0][1] == "-":
        return solvingSubtract(v, q)
    elif q[0][1] == "*":
        return solvingAddition(v, q)
    elif q[0][1] == "/":
        return solvingDivide(v, q)
    
def solvingAddition(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '-', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[2], '-', q[0][0]))
    return solving(v, newThing)

def solvingSubtract(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '+', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[0][0], '-', q[2]))
    return solving(v, newThing)

def solvingMultiply(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '/', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[2], '/', q[0][0]))
    return solving(v, newThing)

def solvingDivide(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '*', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[0][0], '/', q[2]))
    return solving(v, newThing)

print(isInside('x', 'x'))                          #  True   1 point
print(isInside('x', 'y'))                          #  False  1 point
print(isInside('x', ('x', '+', 'y')))              #  True   2 points
print(isInside('x', ('a', '+', 'b')))              #  False  2 points
print(isInside('+', ('a', '+', 'b')))              #  False  2 points
print(isInside('x', (('m', '*', 'x'), '+', 'b')))  #  True   2 points

print(solve('x', (('a', '+', 'x'), '=', 'c')))
#  ('x', '=', ('c', '-', 'a'))  2 points

print(solve('x', (('x', '+', 'b'), '=', 'c')))
#  ('x', '=', ('c', '-', 'b'))  2 points

print(solve('x', (('a', '-', 'x'), '=', 'c')))
#  ('x', '=', ('a', '-', 'c'))  2 points

print(solve('x', (('x', '-', 'b'), '=', 'c')))
#  ('x', '=', ('c', '+', 'b'))  2 points

print(solve('x', (('a', '*', 'x'), '=', 'c')))
#  ('x', '=', ('c', '/', 'a'))  2 points

print(solve('x', (('x', '*', 'b'), '=', 'c')))
#  ('x', '=', ('c', '/', 'b'))  2 points

print(solve('x', (('a', '/', 'x'), '=', 'c')))
#  ('x', '=', ('a', '/', 'c'))  2 points

print(solve('x', (('x', '/', 'b'), '=', 'c')))
#  ('x', '=', ('c', '*', 'b'))  2 points

print(solve('y', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('y', '=', (('m', '*', 'x'), '+', 'b'))  2 points

print(solve('x', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('x', '=', (('y', '-', 'b'), '/', 'm'))  2 points

print(solve('a', (('b', '+', 'c'), '=', ('d', '*', (('a', '/', 'e'), '-', 'f')))))
# ('a', '=', (((('b', '+', 'c'), '/', 'd'), '+', 'f'), '*', 'e'))  5 points
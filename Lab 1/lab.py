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
    solving(v, newThing)

def solvingSubtract(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '+', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[0][0], '-', q[2]))
    solving(v, newThing)

def solvingMultiply(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '/', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[2], '/', q[0][0]))
    solving(v, newThing)

def solvingDivide(v, q):
    if isInside(v, q[0][0]):
        #x is on the left of the left of the equation
        newThing = (q[0][0], '=', (q[2], '*', q[0][2]))
    elif isInside(v, q[0][2]):
        #x is on the right of the left of the equation
        newThing = (q[0][2], '=', (q[0][0], '/', q[2]))
    solving(v, newThing)
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
    #whatever
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

print(isInside('x', 'x'))                          #  True   1 point
print(isInside('x', 'y'))                          #  False  1 point
print(isInside('x', ('x', '+', 'y')))              #  True   2 points
print(isInside('x', ('a', '+', 'b')))              #  False  2 points
print(isInside('+', ('a', '+', 'b')))              #  False  2 points
print(isInside('x', (('m', '*', 'x'), '+', 'b')))  #  True   2 points
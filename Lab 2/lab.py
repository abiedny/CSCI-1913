class Zillion:
    def __init__(self, digits):
        self.value = []
        ints = ('9', '8', '7', '6', '5', '4', '3', '2', '1', '0')
        for digit in digits:
            if digit == " " or digit == ",":
                #Ignore these, but no exception
                continue
            elif digit in ints:
                #Its a number, add it to the list
                self.value.append(int(digit))
            else:
                #throw exception, because its not an int or space or comma
                raise RuntimeError
        if self.value == []:
            #After sorting, value is still an empty list
            raise RuntimeError
    def increment(self):
        self.incrementing(len(self.value) - 1) #call incrementing with the last element of the list
    def incrementing(self, counter):
        if self.value[counter] == 9:
            if counter == 0:
                #counter is zero and first number is 9, insert a 1 at the front to carry
                self.value[counter] = 0
                self.value.insert(0, 1)
            else:
                #special 9 handling
                self.value[counter] = 0
                self.incrementing(counter - 1)
        else:
            self.value[counter] += 1
    def isZero(self):
        for digit in self.value:
            if digit != 0:
                return False
        return True
    def toString(self):
        retVal = ""
        for digit in self.value:
            retVal += str(digit)
        return retVal

# Tests are below, all comments are the return values


try:
  z = Zillion('')
except RuntimeError:
  print('Empty string')

# It must print 'Empty string' without apostrophes. 2 points.

try:
  z = Zillion(' , ')
except RuntimeError:
  print('No digits in the string')

# It must print 'No digits in the string' without apostrophes. 2 points.

try:
  z = Zillion('1+0')
except RuntimeError:
  print('Non-digit in the string')

# It must print 'Non-digit in the string' without apostrophes. 2 points.

try:
  z = Zillion('0')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000000000')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000 000 000')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('997')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing.  2 points.

print(z.isZero())    #  It must print False. 2 points.

print(z.toString())  #  It must print 997. 2 points.

z.increment()

print(z.toString())  #  It must print 998. 2 points.

z.increment()

print(z.toString())  #  It must print 999. 2 points.

z.increment()

print(z.toString())  #  It must print 1000. 2 points.

try:
  z = Zillion('0 9,9 9')
except:
  print('This must not be printed')

#  It must print nothing.  3 points.

z.increment()
print(z.toString())  #  It must print 1000. 2 points.

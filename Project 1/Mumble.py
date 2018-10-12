import Random
import choosers as ch

class Mumble:
    def __init__(self, seed, choosers):
        self.rng = Random.Random(seed)
        self.choosers = choosers
    
    def make(self, max):
        if max <= 0:
            raise ValueError
            
        
        
    def nextLetter(self, lastLetter=' '):
        letArr = self.choosers[lastLetter][1:]
        randVal = self.rng.choose(self.choosers[lastLetter][0])

        for i, v in enumerate(letArr):
            if type(v) == int:
                randVal -= v
            if randVal <= 0:
                return letArr[i - 1] #next letter

    def test(self, count, min, max):
        for ind in range(0, count - 1):
            print(self.make(max))   

var = Mumble(12345, ch.choosers)
var.test(10, 9, 20)
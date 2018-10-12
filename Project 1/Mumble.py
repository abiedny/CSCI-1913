import Random
import choosers as ch

class Mumble:
    def __init__(self, seed, choosers):
        self.rng = Random.Random(seed)
        self.choosers = choosers
        self.currentWord = ''
    
    def make(self, max):
        if max <= 0:
            raise ValueError   
        self.currentWord = ''
        return self._buildWord(max)        
        
    def _buildWord(self, max, lastLetter=' '):
        letArr = self.choosers[lastLetter][1:]
        randVal = self.rng.choose(self.choosers[lastLetter][0])

        #get ready ready for code spaghetti
        for i, v in enumerate(letArr):
            if type(v) == int:
                randVal -= v
            if randVal <= 0:
                if letArr[i - 1] == ' ' or len(self.currentWord) == max:
                    return 
                self.currentWord += letArr[i - 1]
                self._buildWord(max, letArr[i - 1])
                return #when we get here its time to unclutter the callstack

    def test(self, count, min, max):
        for ind in range(0, count - 1):
            print(self.make(max))   

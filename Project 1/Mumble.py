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
        self._buildWord(max)
        return self.currentWord        

    def _buildWord(self, max, lastLetter=' '):
        letArr = self.choosers[lastLetter][1:]
        randVal = self.rng.choose(self.choosers[lastLetter][0])

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
        if min < 1 or max < min:
            raise ValueError
        for ind in range(0, count):
            while True:
                word = self.make(max)
                if len(word) >= min:
                    break
            print(word)

mumbler = Mumble(101, ch.choosers)
mumbler.test(10, 5, 10)

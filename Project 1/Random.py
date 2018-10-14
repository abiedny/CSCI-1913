class Random:
    PRL = 16807
    PRU = 2147483648

    def __init__(self, seed):
        self.number = (self.PRL * seed) % (self.PRU - 1)
        
    def next(self):
        self.number = (self.PRL * self.number) % (self.PRU - 1)
        return self.number

    def choose(self, max):
        return self.next() % (max)
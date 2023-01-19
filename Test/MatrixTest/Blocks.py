from Block import Block

class Blocks:

    def __init__(self):
        
        self.matrix = []
        self.X = 0
        self.Y = 0

    def create(self, cols:int, rows:int):

        for i in range(cols):

            self.X = 0
            self.Y += 10
            row = []

            for j in range(rows):

                self.X += 10

                row.append(Block(self.X, self.Y))

            print(row)
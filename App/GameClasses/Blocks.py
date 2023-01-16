from GameClasses.Block import Block
from pygame import Surface


class Blocks:

    def __init__(self, cols, rows, screen:Surface):

        self.cols = cols
        self.rows = rows
        self.Block = [[Block]]
        self.X = 10
        self.Y = 750
 
        for i in range(cols):

            for j in range(rows):

                self.Block[i][j] = Block(self.X, self.Y)
                self.Block[i][j].print(screen)
                
                self.X += self.Block[i][j].X

                j += 1

            self.X = 10
            self.Y -= self.Block[i][j].Y

            i += 1

from GameClasses.Block import Block
from pygame import Surface

class Blocks:

    def __init__(self, cols, rows):

        # LA CANTIDAD DE COLORES DEBE SER IGUAL A LA CANTIDAD DE ROWS
        self.colors = [(200, 0, 0), (0, 200, 0), (0, 0, 200),
                       (200, 200, 0), (0, 200, 200), (200, 0, 200)]

        self.cols = cols
        self.rows = rows
        self.width = 60
        self.height = 20
        self.Xseparation = self.width + 10
        self.Yseparation = self.height + 10
        self.X: 10
        self.Y = self.Yseparation

        self.AllBlocks = []

        for i in range(self.rows):

            self.X = 10
            self.Y += self.Yseparation
            row = []

            for j in range(self.cols):

                self.X += self.Xseparation
                row.append(Block(self.X - self.width, self.Y,
                           self.height, self.width, self.colors[i]))

            self.AllBlocks.append(row)

    def print(self, screen: Surface):

        for i in range(self.rows):

            for j in range(self.cols):

                self.AllBlocks[i][j].print(screen)

    # GETTERS
    def __getY__(self, col: int, row: int):

        return self.AllBlocks[col][row].Y

    def __getX__(self, col: int, row: int):

        return self.AllBlocks[col][row].X

    def __getWidth__(self, col: int, row: int):

        return self.AllBlocks[col][row].width

    def __getHeight__(self, col: int, row: int):

        return self.AllBlocks[col][row].height

    # SETTERS
    def __setNull__(self, col: int, row: int):

        self.AllBlocks[col][row] = None

    def __setBlocks__(self, AllBlocks):

        self.AllBlocks = AllBlocks
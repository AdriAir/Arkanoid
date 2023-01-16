import random
from pygame import Surface
from pygame import draw


class Block:

    def __init__(self, X: float, Y: float, height: int, width: int, color:int):

        self.color = color
        self.X = X
        self.Y = Y
        self.width = width
        self.height = height
        self.rectangle = [self.X, self.Y, self.width, self.height]

    def print(self, screen: Surface):

        draw.rect(screen, self.color, self.rectangle)

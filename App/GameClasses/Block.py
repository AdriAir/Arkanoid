import random
from pygame import Surface
from pygame import draw

class Block:

    def __init__(self, X:float, Y:float):

        self.color = (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))
        self.X = X
        self.Y = Y
        self.width = 60
        self.height = 20
        self.rectangle = [X, Y, self.width, self.height]

    def print(self, screen:Surface):

        draw.rect(screen, self.color, self.rectangle)
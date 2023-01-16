from pygame.display import *
from pygame.surface import *
from pygame import draw

class Player:

    def __init__(self, X:float, Y:float, height:int, width:int, color:int):
        
        self.X = X
        self.Y = Y
        self.width = width
        self.height = height
        self.velocity = 5
        self.myPlayer = [self.X, self.Y, self.width, self.height]
        self.color = color
    
    def print(self, screen:Surface):

        draw.rect(screen, self.color, self.myPlayer, 0, 30)

    def move(self, direction, screen:Surface):
        
        if direction[97]:

            self.X = self.X - self.velocity
            self.myPlayer = [self.X, self.Y, self.width, self.height]

        if direction[100]:

            self.X = self.X + self.velocity
            self.myPlayer = [self.X, self.Y, self.width, self.height]

        if self.X > (screen.get_width() - self.width):

            self.X = screen.get_width() - self.width

        elif self.X < 0:

            self.X = 0
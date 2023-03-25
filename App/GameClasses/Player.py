from pygame import display
from pygame import surface
from pygame import draw

class Player:

    def __init__(self, X:float, Y:float, height:int, width:int, color:int, Xvelocity:float):
        
        self._X:float = X
        self._Y:float = Y
        self._width:int = width
        self._height:int = height
        self._color:int = color
        self._Xvelocity = Xvelocity
        self._player = [self.X, self.Y, self._Xvelocity, self.width, self.height, self._color]
        
    
    def print(self, screen:Surface):

        draw.rect(screen, self.color, self.myPlayer, 0, 30)

    def move(self, direction, screen:Surface):
        
        if direction[97]:

            self.X = self.X - self.velocity
            self.myPlayer = [self.X, self.Y, self.width, self.height]

        if direction[100]:

            self.X = self.X + self.velocity
            self.myPlayer = [self.X, self.Y, self._width, self._height]

        if self.X > (screen.get_width() - self._width):

            self.X = screen.get_width() - self._width

        elif self.X < 0:

            self.X = 0
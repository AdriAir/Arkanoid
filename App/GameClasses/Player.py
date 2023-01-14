from pygame.display import *
from pygame.surface import *
from pygame import draw
from pygame import key

class Player:

    def __init__(self, X:float, Y:float, height:int, width:int, color:int):
        
        self.myPlayer = [X, Y, width, height]
        self.color = color
    
    def print(self, screen:Surface):

        draw.rect(screen, self.color, self.myPlayer)

    def move(direction:str):
        
        if key.get_pressed()[direction]:
            pass
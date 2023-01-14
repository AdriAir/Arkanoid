import pygame  
import sys
from GameClasses.Player import *

if __name__ == "__main__":

    pygame.init()

    height = 1000
    width = 800
    screen = pygame.display.set_mode((width, height))
    pygame.display.set_caption("Arkanoid")
    myPlayer = Player(screen.get_width() / 2, screen.get_height() / 4, 20, 60, (255,0,0))
    
    exit = False
    frame_rate = 60
    
    while not exit:

        keys = pygame.key.get_pressed()

        for event in pygame.event.get():

            if event.type == pygame.QUIT:

                exit = True

        if keys[pygame.K_ESCAPE]:

            exit = True
        
        myPlayer.print(screen)

        pygame.display.update()
        clock = pygame.time.Clock()
        clock.tick(frame_rate)

    sys.exit

else:

    print("Este archivo se importa como módulo")
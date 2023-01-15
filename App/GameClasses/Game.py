import sys
from GameClasses.Player import Player
import pygame

class Game:

    def __init__(self):

        pygame.init()

        #Variables
        self.height = 800
        self.width = 800
        self.finish = False
        self.frame_rate = 60
        self.keys = None
        self.events = None
        self.screen = pygame.display.set_mode((self.width, self.height))
        pygame.display.set_caption("Arkanoid")
        
        #Objects
        self.myPlayer = Player(self.screen.get_width() / 2, (self.screen.get_height() / 8) * 7, 20, 60, (255,0,0))
        

    def update(self):

        #Updating Variable
        self.keys = pygame.key.get_pressed()
        self.events = pygame.event.get()

        #Key Manager
        if self.keys[pygame.K_ESCAPE]:

            print(pygame.K_a,  " ", pygame.K_d)
            #self.finish = True

        #Event Manager
        for event in self.events:

            if event.type == pygame.QUIT:

                self.finish = True

        if self.finish:
            
            #Exit Game
            sys.exit()       

        #Printing Object
        self.cleanScreen()
        self.myPlayer.print(self.screen)

        #Moving Objects
        self.myPlayer.move(self.keys)

        #Updating Game
        pygame.display.update()
        clock = pygame.time.Clock()
        clock.tick(self.frame_rate)

    def isFinish(self):

        return self.finish
    
    def cleanScreen(self):

        self.screen.fill((0, 0, 0))
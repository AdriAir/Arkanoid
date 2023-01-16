import math
from pygame.display import *
from pygame.surface import *
from pygame import draw
from GameClasses.Player import Player
import random


class Ball:

    def __init__(self, X: float, Y: float, radius: float, color: int):

        self.X = X
        self.Y = Y
        self.radius = radius
        self.velocity = 5
        self.angle = math.radians(25)
        self.myBall = [self.X, self.Y, self.radius]
        self.color = color

    def print(self, screen: Surface):

        draw.circle(screen, self.color, (self.X, self.Y), self.radius, 0)

    def move(self, screen: Surface, player: Player):

        # Movement
        self.X += self.velocity * math.cos(self.angle)
        self.Y += self.velocity * math.sin(self.angle)

        # Top Hit
        if self.Y < self.radius:

            self.angle = -self.angle

        # Game Over (Under the player)
        if self.Y > (player.Y + self.radius + player.height):

            self.velocity = 0

        # Left or Right Hit
        if self.X < self.radius or self.X > (screen.get_width() - self.radius):

            self.angle = math.pi - self.angle

        # Player Collision
        if self.Y >= player.Y - self.radius and (self.X >= player.X and (self.X <= (player.X + player.width))):

            self.angle = -self.angle
            
            if self.velocity != 0:

                self.Y = player.Y - self.radius

import math
from pygame.display import *
from pygame.surface import *
from pygame import draw
from GameClasses.Player import Player
from GameClasses.Blocks import Blocks
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

    def move(self, screen: Surface, player: Player, allBlocks: Blocks):

        # Movement
        self.X += self.velocity * math.cos(self.angle)
        self.Y += self.velocity * math.sin(self.angle)

        # Collisions
        self.windowsCollision(screen)
        self.playerCollision(player)

        isColliding = self.isCollision(allBlocks)[0]
        newBlocks = self.isCollision(allBlocks)[1]
        rows = self.isCollision(allBlocks)[2]
        cols = self.isCollision(allBlocks)[3]

        if isColliding:

            newBlocks[rows][cols] = None

            allBlocks.__setBlocks__(newBlocks)

    # Other Funcitions
    def windowsCollision(self, screen: Surface):

        # Top Hit
        if self.Y < self.radius:

            self.angle = -self.angle

        # Left or Right Hit
        if self.X < self.radius or self.X > (screen.get_width() - self.radius):

            self.angle = math.pi - self.angle

    def playerCollision(self, player: Player):

        # Player Collision
        if self.Y >= player.Y - self.radius and (self.X >= player.X and (self.X <= (player.X + player.width))):

            self.angle = -self.angle

            if self.velocity != 0:

                self.Y = player.Y - self.radius

        # Game Over (Under the player)
            if self.Y > (player.Y + self.radius + player.height):

                self.velocity = 0

    def isCollision(self, allBlocks: Blocks):

        # Block Collisions
        for i in range(allBlocks.rows):

            for j in range(allBlocks.cols):

                if (self.Y <= (allBlocks.__getY__(i, j) - self.radius)) and (self.Y >= (allBlocks.__getY__(i, j) + allBlocks.__getHeight__(i, j) - self.radius)) and (self.X >= (allBlocks.__getX__(i, j) - self.radius)) and (self.X <= (allBlocks.__getX__(i, j) + allBlocks.__getWidth__(i, j) - self.radius)):

                    self.angle = -self.angle
                    return [allBlocks, i, j]

                else:

                    return False

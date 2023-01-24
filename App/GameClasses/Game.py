class Game:
    
    def __init__(self):

        pygame.init()

        #Variables
        self.height = 900
        self.width = 800
        self.finish = False
        self.frame_rate = 120
        self.keys = None
        self.events = None
        self.screen = pygame.display.set_mode((self.width, self.height))
        pygame.display.set_caption("Arkanoid")
        
        #Objects
        self.myPlayer = Player(self.screen.get_width() / 2, (self.screen.get_height() / 20) * 19, 8, 80, (255,255,255))
        self.myBall = Ball(400, 400, 10, (255,255,0))
        self.myBlocks = Blocks(11, 6)
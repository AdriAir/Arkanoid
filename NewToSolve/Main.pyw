from GameClasses.Game import Game

if __name__ == "__main__":

    #Main Menu

    #Starting Game
    myGame = Game()
    
    #Updating Game
    while True:

        myGame.update()

else:

    print("Este archivo se importa como módulo")
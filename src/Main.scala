object Main {
  def main(args: Array[String]): Unit = {

    //Creation of the grid and the snake
    val grid: Grid = new Grid()
    val music: Audio = new Audio("/music.wav")

    // Démarrer la musique
    music.play()

    grid.startScreen()

    grid.restartPressed = false
    //Attendre jusqu'à qu'une touche valide soit pressé
    while (!grid.startPressed) {
      grid.musicState(music)
      Thread.sleep(100)
    }

    //Drawing the background and header
    grid.display.drawBackground()
    grid.drawBackGround(grid.backgroundGreen)
    grid.drawGrid(grid.green, grid.lightGreen)

    var randomX: Int = (Math.random() * 15).toInt
    var randomY: Int = ((Math.random() * 15).toInt)
    grid.display.syncGameLogic(30)
    grid.drawEmpty(randomX, randomY)
    grid.food.foodGrid(randomX)(randomY) = 1
    grid.display.drawForeground()
    grid.display.drawTransformedPicture(grid.cornerSize * 30 + randomX * grid.cellSize + 15, grid.headerSize * 30 + randomY * grid.cellSize + 45, 0, 0.05, "/res/strawberry.png")
    grid.display.drawForeground()
    grid.drawHeader(grid.headerGreen)

    var continueGame: Boolean = true
    while (continueGame) {
      try {
        while (!grid.tailDeath) {
          grid.musicState(music)
          grid.move()
          grid.drawGame()
          println(grid.snake.position(1))
          var random: Int = (Math.random() * 5).toInt

          if (random > 1) {
            grid.food.createFood()
          } else {
            grid.food.createFood2()
          }

          grid.food.eat()

          Thread.sleep(300)
        }
      } catch {
        case e: ArrayIndexOutOfBoundsException => grid.deathScreen()
      }

      grid.deathScreen()

      // Attendre qu'une touche soit pressé
      grid.restartPressed = false
      while (!grid.restartPressed) {
        grid.musicState(music)
        Thread.sleep(100)
      }

      // Réinitialiser le jeu
      grid.resetGame()
      }
    }
}


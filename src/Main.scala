

object Main {
  def main(args: Array[String]): Unit = {



    //Creation of the grid and the snake
    val grid : Grid = new Grid ()



    //Drawing the background and header
    grid.display.drawBackground()
    grid.drawBackGround(grid.backgroundGreen)
    grid.drawHeader(grid.headerGreen)
    grid.drawGrid()

    grid.display.drawForeground()


    grid.display.syncGameLogic(60)

    while(true){
      grid.drawGame()
      grid.food.createFood()
      grid.move()
      grid.food.eat()
      println(s"${grid.snake.position(0)} and ${grid.snake.position(1)}")
      Thread.sleep(200)



    }
  }


}


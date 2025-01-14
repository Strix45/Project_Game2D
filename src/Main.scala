

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

    try{while(!grid.tailDeath){
      grid.move()
      grid.drawGame()

      grid.food.createFood()
      grid.food.eat()
      println(s"${grid.snake.position(0)} and ${grid.snake.position(1)}")
      Thread.sleep(300)
      } }catch {
      case e : ArrayIndexOutOfBoundsException => grid.deathScreen()
    }

    grid.deathScreen()
    }







}


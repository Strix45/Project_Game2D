

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

    var randomX : Int = (Math.random()*15).toInt
    var randomY : Int = ((Math.random()*15).toInt)
    grid.display.syncGameLogic(30)
    grid.food.foodGrid(randomX)(randomY)= 1
    grid.display.drawTransformedPicture(grid.cornerSize * 30 + randomX*grid.cellSize + 15, grid.headerSize * 30 + randomY*grid.cellSize + 45, 0, 0.05, "/res/strawberry.png")
    try{while(!grid.tailDeath){
      grid.move()
      grid.drawGame()

      if((Math.random()+1).toInt ==1){
      grid.food.createFood()
      } else {
        grid.food.createFood()
        grid.food.createFood()
      }

      grid.food.eat()
      grid.drawHeader(grid.headerGreen)
      grid.scoreDisplay()
      Thread.sleep(250)
      }
    } catch {
      case e : ArrayIndexOutOfBoundsException => grid.deathScreen()
    }

    grid.deathScreen()
    }

}


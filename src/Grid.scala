import hevs.graphics.FunGraphics

import java.awt.{Color, Font}
import java.awt.event.{KeyAdapter, KeyEvent}
import java.util.Random

class Grid (){
  class Snake () {
    val position: Array[Int] = Array(7,10)
    var up : Boolean = false
    var down : Boolean =  true
    var right : Boolean = false
    var left : Boolean = false
    var size : Int = 1
  }

  class Food {
    val foodGrid : Array [Array[Int]] = Array.fill(15,15)(0)

    def eat() : Unit = {
      if (foodGrid(snake.position(0)-1)(snake.position(1)-4) == 1){
        snake.size +=1
        foodGrid(snake.position(0)-1)(snake.position(1)-4) = 0
      }
    }

    var foodTimer : Int = 0

    def createFood() : Unit = {
      var randomX : Int = (Math.random()*15).toInt
      var randomY : Int = (Math.random()*15).toInt
      foodTimer += 1
      if (foodTimer > 2 && gridElement(randomX)(randomY) == 0 && foodGrid(randomX)(randomY) == 0){
        foodTimer = 0

        display.drawTransformedPicture(cornerSize * 30 + randomX*cellSize + 15, headerSize * 30 + randomY*cellSize + 45, 0, 0.05, "/res/strawberry.png")
        foodGrid(randomX)(randomY) = 1
      }
    }

  }

  //Size of the display
  val width : Int = 512
  val height : Int = 600
  val hi : Int = 2
  val cellSize : Int = 30

  //Size of header and space on the sides. Size in number of cells it represents
  val cornerSize : Int = 1
  val headerSize : Int = 3

  //Number of cells in the
  val numCellsX : Int = width/cellSize
  val numCellsY : Int = height/cellSize

  //elements in the grid
  val gridElement : Array[Array[Int]] = Array.fill(15,15)(0)


  //Colors used in the program
  val blue : Color = new Color(100,175,255)
  val lightGreen : Color = new Color (178, 215, 95)
  val green : Color = new Color (157, 201, 82)
  val backgroundGreen : Color = new Color(100, 136, 64)
  val headerGreen : Color = new Color(85, 115, 54)


  //Creation of the display here to make everything cleaner
  //Creation of the snake and food objects
  val display : FunGraphics = new FunGraphics(width, height)
  val snake : Snake = new Snake ()
  val food : Food = new Food ()


  //if condition to stop the snake from going outside the grid
  //it also changes the value of one of the positions for the auto moving snake
  display.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyChar == 'a') println("The key 'A' was pressed")
      if (e.getKeyCode == KeyEvent.VK_RIGHT) {
        snake.left = false
        snake.down = false
        snake.up = false
        snake.right = true
      }
      if (e.getKeyCode == KeyEvent.VK_LEFT) {
        snake.down = false
        snake.up = false
        snake.right = false
        snake.left = true
      }
      if (e.getKeyCode == KeyEvent.VK_UP) {
        snake.left = false
        snake.down = false
        snake.right = false
        snake.up = true
      }
      if (e.getKeyCode == KeyEvent.VK_DOWN) {
        snake.left = false
        snake.up = false
        snake.right = false
        snake.down = true
      }
    }
  })




  var tailDeath : Boolean = false


  //Function that draws a cell of size cellSize starting at the position (x,y) and of color c
  def drawCell(x: Int, y: Int, c: Color): Unit = {
    val startX = x * cellSize
    val startY = y * cellSize

    for (x <- 0 until cellSize) {
      for (y <- 0 until cellSize) {
        display.setPixel(startX + x, startY + y, c)
      }
    }
  }

  def drawBackGround(c: Color): Unit = {
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        display.setPixel(x, y, c)
      }
    }
  }


  def drawHeader(c: Color): Unit = {
    for (x <- 0 until width) {
      for (y <- 0 until headerSize * cellSize) {
        display.setPixel(x, y, c)
      }
    }
  }


  //Draws the grid unless there is the snake (early iteration of the snake head)
  def drawGrid () : Unit = {
    {
      display.drawBackground()
      for (x <- cornerSize until numCellsX - cornerSize) {
        for (y <- headerSize + cornerSize until numCellsY - cornerSize) {
          // Alterne les couleurs en fonction de la position
          if (((x + y) % 2 == 0) )   {
            drawCell(x, y, lightGreen) // vert clair
          } else if(!((x + y) % 2 == 0) ){
            drawCell(x, y, green) // Vert foncé
          }
        }
      }
    }
  }




  def move() : Unit = {
    if (snake.up == true){
      snake.position(1) -= 1
    } else if (snake.down == true) {
      snake.position(1) += 1
    } else if (snake.right == true){
      snake.position(0) += 1
    } else if (snake.left==true){
      snake.position(0) -= 1
    }

    if (gridElement(snake.position(0)-1)(snake.position(1)-4)>0) {tailDeath = true}
    gridElement(snake.position(0)-1)(snake.position(1)-4) += snake.size + 1

    //Important that the lowest value possible is 2 so that when it does -1 the snake head still shows
    //It's that way because of the order of the methods
    //Size of snake = x-1
  }

  def drawGame() : Unit = {
    for(x <- gridElement.indices){
      for (y <- gridElement(x).indices){
        if (gridElement(x)(y)>1){
          drawCell(x+cornerSize,y+headerSize+1,blue)    //draws the snake in the cells where the value is higher than 0
        }
        if (gridElement(x)(y)>0) {      //>0 so that no cell value ever goes under 0
          gridElement(x)(y) -= 1
        }
        if (gridElement(x)(y) == 0 && ((x + y) % 2 == 0 && food.foodGrid(x)(y)==0) ){     //alternates the color of the cells and draws them where the cell's value is 0
          drawCell(x + cornerSize, y + headerSize + 1, lightGreen)
        } else if (gridElement(x)(y) == 0 && !((x + y) % 2 == 0) && food.foodGrid(x)(y)==0){
          drawCell(x + cornerSize, y + headerSize + 1, green)
        }
      }
    }
  }


  def deathScreen() : Unit = {
    display.clear()
    display.drawString(width/4,height/2, "YOU DIEDDDDD", blue, 20)
  }


}

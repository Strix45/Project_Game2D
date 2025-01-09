package Snake
import hevs.graphics.{FunGraphics, ImageGraphics}

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

class Snake() {
  var up : Boolean = false
  var down : Boolean = false
  var right : Boolean = false
  var left : Boolean = false

  val position : Array[Int] = Array(7,7)
  val gridFill : Array[Array[Int]] = Array.ofDim[Int](15,15)

}


object Snaketest extends App {
  val displayWidth = 512
  val displayHeight = 600
  val cellSize = 30

  val spaceOfTheCorner = 1
  val spaceOftheHeader = 3

  val numCellsX = displayWidth / cellSize //
  val numCellsY = displayHeight / cellSize //

  // Grid startX at 32 and end at 480 --> size : 448px
  // Grid atartY at 80 and end at 400 --> 320px
  val sizeOftheGridX = 12 //surement faux !!!!
  val sizeOftheGridY = 9 //surement faux !!!!

  val display: FunGraphics = new FunGraphics(displayWidth, displayHeight)



  def drawCell(x: Int, y: Int, c: Color): Unit = {
    // Dessine une seule cellule à la position (x,y)
    val startX = x * cellSize
    val startY = y * cellSize

    for (x <- 0 until cellSize) {
      for (y <- 0 until cellSize) {
        display.setPixel(startX + x, startY + y, c)
      }
    }
  }

  def backColor(c: Color): Unit = {
    for (x <- 0 until displayWidth) {
      for (y <- 0 until displayHeight) {
        display.setPixel(x, y, c)
      }
    }
  }

  def header(c: Color): Unit = {
    for (x <- 0 until displayWidth) {
      for (y <- 0 until spaceOftheHeader * cellSize) {
        display.setPixel(x, y, c)
      }
    }
  }

  backColor(new Color(100, 136, 64, 255))
  header(new Color(85, 115, 54, 255))


    // Draw Grid
    for (x <- spaceOfTheCorner until numCellsX - spaceOfTheCorner) {
      for (y <- spaceOftheHeader + spaceOfTheCorner until numCellsY - spaceOfTheCorner) {
        // Alterne les couleurs en fonction de la position
        if ((x + y) % 2 == 0) {
          drawCell(x, y, new Color(178, 215, 95, 255)) // vert clair
        } else {
          drawCell(x, y, new Color(157, 201, 82, 255)) // Vert foncé
        }
      }
    }

  var snake1 : Snake = new Snake()
  var hello : Color = new Color(50,50,255)


  display.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyChar == 'a') println("The key 'A' was pressed")
      if (e.getKeyCode == KeyEvent.VK_RIGHT) {snake1.position(0) += 1}
      if (e.getKeyCode == KeyEvent.VK_LEFT) {snake1.position(0) -= 1}
      if (e.getKeyCode == KeyEvent.VK_UP) {snake1.position(1) -= 1}
      if (e.getKeyCode == KeyEvent.VK_DOWN) {snake1.position(1) += 1}
    }
  })

  while (true) {
    for (x <- spaceOfTheCorner until numCellsX - spaceOfTheCorner) {
      for (y <- spaceOftheHeader + spaceOfTheCorner until numCellsY - spaceOfTheCorner) {
        // Alterne les couleurs en fonction de la position
        var isItTheSame : Boolean = snake1.position(0) == x && snake1.position(1) == y
        if (((x + y) % 2 == 0) && !isItTheSame)   {
          drawCell(x, y, new Color(178, 215, 95, 255)) // vert clair
        } else if(!((x + y) % 2 == 0) && !isItTheSame){
          drawCell(x, y, new Color(157, 201, 82, 255)) // Vert foncé
        }
      }
    }






    //refresh the screen at 60 FPS
    display.syncGameLogic(60)
}

}




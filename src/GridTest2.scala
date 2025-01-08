import Grid.display
import hevs.graphics.FunGraphics

import java.awt.Color

object GridTest2 extends App {

  var largeur: Int = 512
  var hauteur: Int = 600
  val display: FunGraphics = FunGraphics(largeur, hauteur)

  var cellule: Int = 0

  display.setColor(new Color(26, 102, 46))
  for (x: Int <- 0 until largeur) {
    for (y: Int <- 0 until hauteur) {
      display.setPixel(x, y)
    }
  }

  for (x <- 0 until (largeur)) {
    for (y <- 0 until hauteur) {
      if (cellule % 2 == 0) {
        display.setPixel(x, y, new Color(0, 255, 0))
      } else {
        display.setPixel(x, y, new Color(0, 160, 0))
      }
      if (x % 32 == 0) {
        cellule += 1
      }
    }
  }
}

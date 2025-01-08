import hevs.graphics.FunGraphics
import java.awt.Color

object Grid extends App {

  val display: FunGraphics = FunGraphics(512, 512)

  for (i : Int <- 0 until 512){
    for (j : Int <- 0 until 512){
      if (j % 32 == 0 || i % 32 == 0){
        display.setPixel(i, j, new Color (0,0,0))
      }
    }

  }

}

import hevs.graphics.FunGraphics
import java.awt.Color

object Grid extends App {

  var largeur : Int = 512
  var hauteur : Int = 600
  val display: FunGraphics = FunGraphics(largeur, hauteur)

  var sameSize : Int = 32


  def drawCell(startX : Int, startY : Int, color : Color) : Unit = {

    for (x <- startX until startX+32){
      for (y <- startY until startY + 32){
        display.setPixel(x,y,color)
      }
    }
  }


  // fond uni
  display.setColor(new Color(26,102,46))
  for(x:Int <- 0 until largeur){
    for(y:Int <- 0 until hauteur){
      display.setPixel(x,y)
    }
  }

  // damier
var cellule : Int = 0
  for(x:Int <- 0 until largeur/32){
    for(y:Int <- 0 until hauteur/32){
      if(x%32 == 0) {
      drawCell(x*32,y,new Color(0,255,0))} else {
      drawCell(31+x*32,y,new Color (0,160,0))}
    }
  }

}

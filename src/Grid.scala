
import hevs.graphics.FunGraphics

import java.awt.Color

object Grid extends App {

  var largeur: Int = 512
  var hauteur: Int = 608
  val display: FunGraphics = FunGraphics(largeur, hauteur)

  var cellulex: Int = 1
  var celluley : Int = 1



  val green : Color =  new Color(0,255,0)
  val light_green : Color = new Color(0,160,0)
  val blue : Color = new Color(0,200,255)


  for (x <- 0 until (largeur)) {
    for (y <- 0 until hauteur) {

      if(cellulex % 2 == 0 && celluley % 2 == 0){
        display.setPixel(x,y,light_green)
      } else if(cellulex%2==0 && celluley%2==1){
        display.setPixel(x,y,green)
      } else if (cellulex%2==1 && celluley%2==0){
        display.setPixel(x,y,green)
      } else {display.setPixel(x,y,light_green)}



      if(y%32 ==0 && y>0){
        celluley +=1
      }
    }
    if (x % 32 == 0 && x>0) {
      cellulex += 1
    }
  }

    for (x <- 0 until largeur) {
      for (y <- 0 until 32 * 3 + 1) {
        display.setPixel(x, y, blue)
      }
    }







}

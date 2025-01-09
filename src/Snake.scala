import Snake.Snaketest.display

import java.awt.event.{KeyAdapter, KeyEvent}

class Snake() {
  var up : Boolean = false
  var down : Boolean = false
  var right : Boolean = false
  var left : Boolean = false

  val position : Array[Int] = Array(7,7)
  val lastposition : Array[Int] = Array.fill(2)(0)
  val gridFill : Array[Array[Int]] = Array.fill(15,15)(0)

  println(gridFill.mkString("/"))

  display.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyChar == 'a') println("The key 'A' was pressed")
      if (e.getKeyCode == KeyEvent.VK_RIGHT) position(0) += 1
      if (e.getKeyCode == KeyEvent.VK_LEFT) position(0) -= 1
      if (e.getKeyCode == KeyEvent.VK_UP) position(1) -= 1
      if (e.getKeyCode == KeyEvent.VK_DOWN) position(1) += 1
    }
  })
}

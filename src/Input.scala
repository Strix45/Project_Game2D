import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * The Class Input allows typing data with the keyboard interactively.
 * The types below are supported by the #Input object : String, Int, Double, Boolean, Char
 *
 * @author Pierre-André Mudry
 * @see #readString()
 * @see #readDouble()
 * @see #readInt()
 * @see #readBoolean()
 * @see #readChar()
 */
object Input {

  /**
   * Reads a valid char value from the console.
   * @return The typed char
   */
  def readChar(): Char = {
    var ok = false
    var res = -1

    while (!ok) {
      try {
        val stdin = new BufferedReader(new InputStreamReader(System.in))
        res = stdin.read
        ok = Character.isDefined(res)
      } catch {
        case ex: Exception =>
          System.out.println("This is not a valid character. Try again")
      }
    }
    res.toChar
  }

  /**
   * Reads a String from the console.
   * @return The typed string
   */
  def readString(): String = {
    val stdin = new BufferedReader(new InputStreamReader(System.in))

    try {
      stdin.readLine
    }
    catch {
      case ex: Exception =>
        "There is a problem. Try again."
    }
  }

  /**
   * Reads a valid integer value from the console.
   * @return The typed integer value
   * @see Int
   */
  def readInt(): Int = {
    var ok = false
    var res = -1

    while (!ok) {
      try {
        val stdin = new BufferedReader(new InputStreamReader(System.in))
        val s = stdin.readLine
        if (s.startsWith("0x") || s.startsWith("0X")) res = Integer.parseInt(s.substring(2), 16)
        else res = Integer.parseInt(s, 10)
        ok = true
      } catch {
        case ex: Exception =>
          System.out.println("This is not a valid number. Try again")
      }
    }
    res
  }

  /**
   * Reads a valid double value from the console.
   * @return The typed double value
   * @see Double
   */
  def readDouble(): Double = {
    var ok = false
    var res = -1.0

    while (!ok) {
      try {
        val stdin = new BufferedReader(new InputStreamReader(System.in))
        res = stdin.readLine.toDouble
        ok = true
      } catch {
        case ex: Exception =>
          System.out.println("This is not a valid number. Try again")
      }
    }
    res
  }

  /**
   * Reads a valid boolean value from the console.
   * @return the value true if the typed value is true, false otherwise.
   */
  def readBoolean(): Boolean = {
    var ok = false
    var res = false

    while (!ok) {
      try {
        val stdin = new BufferedReader(new InputStreamReader(System.in))
        res = stdin.readLine.toBoolean
        ok = true
      } catch {
        case ex: Exception =>
          System.out.println("This is not a valid boolean. Try again")
      }
    }
    res
  }
}
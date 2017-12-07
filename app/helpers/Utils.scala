package helpers

import java.nio.charset.StandardCharsets

//Singleton Object
object Utils {

  def fromBytes(x: Array[Byte]) = new String(x, StandardCharsets.UTF_8)

  def toBytes(x: Long) =  x.toString.getBytes(StandardCharsets.UTF_8)
}

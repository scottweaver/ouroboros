package ouroboros.lfsr

import xor._

object Quickie {

  def main(args: Array[String]) {
    
    val result = max32bitLfsr.stream(1)
    
    result take 100 foreach println
    
  }

}

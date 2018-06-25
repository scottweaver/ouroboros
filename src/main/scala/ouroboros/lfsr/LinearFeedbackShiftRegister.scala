package ouroboros.lfsr

class LinearFeedbackShiftRegister(binOp: BitwiseOp, taps: List[Long]) {

  def generate(seed: Long): Long = {
    val bit = taps.map(seed >> _).reduce(binOp(_, _)) & 1
    seed >> 1 | bit << 31
  }
  
  def stream(seed: Long): Stream[Long] = {
    lazy val lfsrs: Stream[Long] = generate(seed) #:: lfsrs.map(generate)
    lfsrs
  }
  
}

/**
  * Bitwise operation that can be used in the creation of an LFSR. 
  * 
  */
sealed trait BitwiseOp {
  def apply(lhs: Long, rhs: Long): Long

}

object BitwiseOp {

  case object BitwiseXor extends BitwiseOp {
    def apply(lhs: Long, rhs: Long): Long = lhs ^ rhs
  }

  case object BitwiseXnor extends BitwiseOp {
    def apply(lhs: Long, rhs: Long): Long = ~(lhs ^ rhs)
  }

}

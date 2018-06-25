package ouroboros
import ouroboros.lfsr.BitwiseOp._


package object lfsr {

  object xor extends op {
    def useOp: BitwiseOp = BitwiseXor
  }

  object xnor extends op {
    def useOp: BitwiseOp = BitwiseXnor
  }

  sealed trait op {
    
    def useOp: BitwiseOp

    def instance(taps: Long*) = new LinearFeedbackShiftRegister(useOp, taps.toList)
    /**
      * 2 bit Lfsr that yields the maximum length of 3.
      */
    lazy val max2bitLfsr = instance(0, 1)

    /**
      * 3 bit Lfsr that yields the maximum length of 7.
      */
    lazy val max3bitLfsr = instance(0, 2)

    /**
      * 16 bit Lfsr that yields the maximum length of 65,535.
      */
    lazy val max16bitLfsr = instance(1, 2, 4, 15)

    /**
      * 32 bit Lfsr that yields the maximum length of 4,294,967,295.
      */
    lazy val max32bitLfsr = instance(0, 10, 30, 31)
  }

}

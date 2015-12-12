package scala.parser


/**
  * Created by Johan on 2015-12-11.
  */

object Language {

  val bitMask = 65535

  abstract class Expr() {
    def eval(memory: Memory): Int = {
      memory.getCached(this) match {
        case Some(a: Int) => a
        case _ => {
          val result: Int = calculate(memory)
          memory.putCache(this, result)
          result
        }
      }
    }

    def calculate(memory: Memory): Int
  }

  abstract class BinOp() extends Expr {
  }

  abstract class UnaryOp() extends Expr

  case class And(a: Expr, b: Expr) extends BinOp {
    override def calculate(memory: Memory) = bitMask & (a.eval(memory) & b.eval(memory))
  }

  case class Or(a: Expr, b: Expr) extends BinOp {
    override def calculate(memory: Memory): Int = bitMask & (a.eval(memory) | b.eval(memory))
  }

  case class LShift(a: Expr, b: Expr) extends BinOp {
    override def calculate(memory: Memory): Int = bitMask & (a.eval(memory) << b.eval(memory))
  }

  case class RShift(a: Expr, b: Expr) extends BinOp {
    override def calculate(memory: Memory): Int = bitMask & (a.eval(memory) >> b.eval(memory))
  }

  case class Not(a: Expr) extends UnaryOp {
    override def calculate(memory: Memory): Int = bitMask & (~a.eval(memory))
  }

  case class Id(name: String) extends Expr {
    override def calculate(memory: Memory): Int = memory.get(this).get.eval(memory)
  }

  case class Num(value: Int) extends Expr {
    override def eval(memory: Memory): Int = value
    override def calculate(memory: Memory): Int = value
  }

  case class NilValue() extends Expr {
    override def calculate(memory: Memory): Int = throw new NullPointerException
  }

}
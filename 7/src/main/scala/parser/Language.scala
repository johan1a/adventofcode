package scala.parser


/**
  * Created by Johan on 2015-12-11.
  */

object Language {

  val bitMask = 65535

  abstract class Expr() {
    def eval(memory: Memory): Int
  }

  abstract class BinOp() extends Expr {
  }

  abstract class UnaryOp() extends Expr

  case class And(a: Expr, b: Expr) extends BinOp {
    override def eval(memory: Memory) = {
      val result: Int = bitMask & (a.eval(memory) & b.eval(memory))
      result
    }
  }

  case class Or(a: Expr, b: Expr) extends BinOp {
    override def eval(memory: Memory): Int = {
      bitMask & (a.eval(memory) | b.eval(memory))
    }
  }

  case class LShift(a: Expr, b: Expr) extends BinOp {
    override def eval(memory: Memory): Int = {
      bitMask & (a.eval(memory) << b.eval(memory))
    }
  }

  case class RShift(a: Expr, b: Expr) extends BinOp {
    override def eval(memory: Memory): Int = {
      bitMask & (a.eval(memory) >> b.eval(memory))
    }
  }

  case class Not(a: Expr) extends UnaryOp {
    override def eval(memory: Memory): Int = {
      bitMask & (~a.eval(memory))
    }
  }

  case class Id(name: String) extends Expr {
    override def eval(memory: Memory): Int = {
      val ref: Option[Expr] = memory.get(this)
      ref.get.eval(memory)
    }
  }

  case class Num(value: Int) extends Expr {
    override def eval(memory: Memory): Int = value
  }


  case class NilValue() extends Expr {
    override def eval(memory: Memory): Int = {
      throw new NullPointerException
    }
  }

}
package parser

import parser.Memory

/**
  * Created by Johan on 2015-12-11.
  */

object Language {

  val bitMask = 65535

  abstract case class Expr() {
    def value(memory: Memory): Int
  }

  abstract case class BinOp() extends Expr {
  }


  case class And(a: Expr, b: Expr) extends BinOp {
    override def value(memory: Memory) = {
      bitMask & (value(memory) & b.value(memory))
    }
  }

  case class Or(a: Expr, b: Expr) extends BinOp {
    override def value(memory: Memory): Int = {
      bitMask & (value(memory) | b.value(memory))
    }
  }

  case class LShift(a: Expr, b: Expr) extends BinOp {
    override def value(memory: Memory): Int = {
      bitMask & (a.value(memory) << b.value(memory))
    }
  }

  case class RShift(a: Expr, b: Expr) extends BinOp {
    override def value(memory: Memory): Int = {
      bitMask & (a.value(memory) >> b.value(memory))
    }
  }

  abstract case class UnaryOp() extends Expr

  case class Not(a: Expr) extends UnaryOp {
    override def value(memory: Memory): Int = {
      bitMask & (~a.value(memory))
    }
  }

  case class Id(name: String) extends Expr {
    override def value(memory: Memory): Int = {
      val ref = memory.get(this)
      ref.get.value(memory)
    }
  }

  case class NilValue() extends Expr {
    override def value(memory: Memory): Int = {
      throw new NullPointerException
    }
  }

}
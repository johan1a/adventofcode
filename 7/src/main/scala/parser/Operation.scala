package parser

import parser.Memory

/**
  * Created by Johan on 2015-12-11.
  */
abstract case class Operation() extends Expr {
  def getValue(memory: Memory): Value
}

case class And(a: Id, b: Id) extends Operation {
  override def getValue(memory: Memory): Value = ???
}

case class Or(a: Id, b: Id) extends Operation {
  override def getValue(memory: Memory): Value = ???
}

case class LShift(a: Id, b: Id) extends Operation {
  override def getValue(memory: Memory): Value = ???
}

case class RShift(a: Id, b: Id) extends Operation {
  override def getValue(memory: Memory): Value = ???
}

abstract case class Value() extends Expr

case class Id(name: String) extends Value {
}

case class Expr()

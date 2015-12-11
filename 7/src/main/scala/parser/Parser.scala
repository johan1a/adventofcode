package parser

import _root_.parser.Language._

/**
  * Created by Johan on 2015-12-11.
  */
class Parser {
  def get(s: String): Int = {
    memory.get(Id(s)).get.value(memory)
  }

  val ASSIGN: String = "->"

  val memory = new Memory

  def parse(line: String) = {
    val splitted = line.split(ASSIGN)
    val lhs = splitted(0)
    val rhs = splitted(1)
    val lhsTokens = lhs.split(" ")

    lhsTokens.length match {
      case 1 => parseAssign(lhsTokens, rhs)
      case 2 => parseUnary(lhsTokens, rhs)
      case 3 => parseBinary(lhsTokens, rhs)
    }
  }

  def parseAssign(lhsTokens: Array[String], rhs: String) = {
    val a = lhsTokens(0)
    memory.put(Id(rhs), Id(a))
  }

  def parseUnary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(0)
    val a = lhsTokens(1)
    memory.put(Id(dest), operation(op, Id(a)))
  }

  def parseBinary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(1)
    val a = Id(lhsTokens(0))
    val b = Id(lhsTokens(1))
    memory.put(Id(dest), operation(op, a, b))
  }

  def operation(op: String, a: Id): UnaryOp = op match {
    case "NOT" => Not(a)
  }

  def operation(op: String, a: Id, b: Id): BinOp = op match {
    case "AND" => And(a, b)
    case "OR" => Or(a, b)
    case "RSHIFT" => RShift(a, b)
    case "LSHIFT" => LShift(a, b)
  }


}

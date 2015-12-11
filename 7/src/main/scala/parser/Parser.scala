package parser

import parser._

/**
  * Created by Johan on 2015-12-11.
  */
class Parser {

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

  def parseAssign(lhsTokens: Array[String], rhs: String) = ???

  def parseUnary(lhsTokens: Array[String], rhs: String) = ???

  def operation(op: String, a: Id, b: Id): Operation = op match {
    case "AND" => And(a, b)
    case "OR" => Or(a, b)
    case "RSHIFT" => RShift(a, b)
    case "LSHIFT" => LShift(a, b)
  }

  def store(value: Nothing, dest: String) = ???

  def parseBinary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(1)
    val a = Id(lhsTokens(0))
    val b = Id(lhsTokens(1))
    memory.put(Id(dest), operation(op, a, b))
  }

  def get(s: String): Int = ???

}

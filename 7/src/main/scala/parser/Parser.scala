package scala.parser

import scala.parser.Language._


/**
  * Created by Johan on 2015-12-11.
  */
class Parser {
  def get(s: String): Int = {
    memory.get(makeId(s)).getOrElse(NilValue()).value(memory)
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

  def makeId(name: String): Id = {
    Id(name.trim)
  }

  def parseAssign(lhsTokens: Array[String], rhs: String) = {
    val a = lhsTokens(0)
    memory.put(makeId(rhs), makeId(a))
  }

  def parseUnary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(0)
    val a = lhsTokens(1)
    memory.put(makeId(dest), operation(op, makeId(a)))
  }

  def parseBinary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(1)
    val a = makeId(lhsTokens(0))
    val b = makeId(lhsTokens(1))
    memory.put(makeId(dest), operation(op, a, b))
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

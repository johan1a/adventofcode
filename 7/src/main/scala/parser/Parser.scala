package scala.parser

import java.lang.NumberFormatException

import scala.parser.Language._


/**
  * Created by Johan on 2015-12-11.
  */
class Parser {
  def get(s: String): Int = {
    memory.get(makeId(s)).getOrElse(NilValue()).eval(memory)
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
    memory.put(makeId(rhs), numOrdId(a))
  }

  def parseUnary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(0)
    val a = lhsTokens(1)
    memory.put(makeId(dest), operation(op, numOrdId(a)))
  }

  def parseBinary(lhsTokens: Array[String], dest: String) = {
    val op = lhsTokens(1)
    val a = numOrdId(lhsTokens(0))
    val b = numOrdId(lhsTokens(1))
    memory.put(makeId(dest), operation(op, a, b))
  }

  def operation(op: String, a: Expr): UnaryOp = op match {
    case "NOT" => Not(a)
  }

  def operation(op: String, a: Expr, b: Expr): BinOp = op match {
    case "AND" => And(a, b)
    case "OR" => Or(a, b)
    case "RSHIFT" => RShift(a, b)
    case "LSHIFT" => LShift(a, b)
  }

  def makeId(name: String): Id = {
    Id(name.trim)
  }

  def numOrdId(a: String) = {
    try {
      Num(a.toInt)
    } catch {
      case _ : Throwable => makeId(a)
    }
  }
}

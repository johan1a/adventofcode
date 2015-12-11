package scala.parser

import scala.collection.mutable.HashMap
import scala.parser.Language.{Num, Id, Expr}

/**
  * Created by Johan on 2015-12-11.
  */
class Memory {
  def eval(s: String): Int = {
    println(s"Getting $s")
    get(Id(s)) match {
      case Some(Num(a)) => a.toInt
      case Some(a : Expr) => a.eval(this)
      case _ => throw new NullPointerException
    }
  }

  val storage = new HashMap[Id, Expr]()

  def put(id: Id, expr: Expr) = {
    println(storage)
    storage.put(id, expr)
  }


  def get(id: Id): Option[Expr] = {
    println(storage)
    storage.get(id)
  }

}

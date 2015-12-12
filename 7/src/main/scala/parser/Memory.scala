package scala.parser

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.parser.Language.{And, Expr, Id, Num}

/**
  * Created by Johan on 2015-12-11.
  */
class Memory {
  def getCached(expr: Expr) : Option[Int] = cache.get(expr)

  val cache = new mutable.HashMap[Expr, Int]()

  val storage = new HashMap[Id, Expr]()

  def eval(s: String): Int = {
    val id: Id = Id(s)
    try {
      val cache1: Int = cache(id)
      cache1
    } catch {
      case _: Exception =>
        get(id) match {
          case Some(Num(a)) => a.toInt
          case Some(a: Expr) => {
            a.eval(this)
          }
          case _ => throw new NullPointerException
        }
    }
  }

  def putCache(expr: Expr, value: Int) = {
    cache.put(expr, value)
  }

  def put(id: Id, expr: Expr) = {
    storage.put(id, expr)
  }


  def get(id: Id): Option[Expr] = {
    storage.get(id)
  }

  def pretty(): String = {
    var str: String = ""
    for (key <- storage.keys) {
      str += new String(key + " => " + storage.get(key).get + "\n")
    }
    str
  }

}

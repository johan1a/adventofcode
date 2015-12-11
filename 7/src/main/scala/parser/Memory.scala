package scala.parser

import scala.collection.mutable.HashMap
import scala.parser.Language.{Id, Expr}

/**
  * Created by Johan on 2015-12-11.
  */
class Memory {
  def get(s: String) : Option[Expr] = get(Id(s))

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

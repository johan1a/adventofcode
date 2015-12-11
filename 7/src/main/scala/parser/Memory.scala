package parser

import _root_.parser.Language.{Id, Expr}

import scala.collection.mutable.HashMap

/**
  * Created by Johan on 2015-12-11.
  */
class Memory {
  val storage = new HashMap[Id, Expr]()

  def put(id: Id, expr: Expr) = {
    storage.put(id, expr)
  }


  def get(id: Id): Option[Expr] = {
    storage.get(id)
  }

}

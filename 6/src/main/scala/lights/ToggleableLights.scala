package lights


/**
  * Created by Johan on 2015-12-11.
  */
class ToggleableLights() extends Lights {
  val lights = {
    val lights = new collection.mutable.HashMap[(Int, Int), Boolean]()
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        lights.put((x, y), false)
      }
    }
    lights
  }

  override def toggleFunc(x: Int, y: Int): Unit = {
    lights.put((x, y), !lights(x, y))
  }

  override def turnOnFunc(x: Int, y: Int): Unit = {
    lights.put((x, y), true)
  }

  override def turnOffFunc(x: Int, y: Int): Unit = {
    lights.put((x, y), false)
  }

  override def count = {
    lights.count((a) => a._2)
  }
}


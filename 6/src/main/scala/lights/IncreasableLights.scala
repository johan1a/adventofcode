package lights

/**
  * Created by Johan on 2015-12-11.
  */
class IncreasableLights() extends Lights() {
  val lights = {
    val lights = new collection.mutable.HashMap[(Int, Int), Int]()
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        lights.put((x, y), 0)
      }
    }
    lights
  }

  override def toggleFunc(x: Int, y: Int): Unit = {
    lights.put((x, y), lights(x, y) + 2)
  }

  override def turnOnFunc(x: Int, y: Int): Unit = {
    lights.put((x, y), lights(x, y) + 1)
  }

  override def turnOffFunc(x: Int, y: Int): Unit = {
    val old: Int = lights(x, y)
    if (old > 0) {
      lights.put((x, y), old - 1)
    }
  }

  override def brightness = {
    lights.values.sum
  }
}


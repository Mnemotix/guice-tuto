package com.mnemotix.tuto.services

import javax.inject.Inject

import com.google.inject._

/*
 * Service interfaces
 */
trait OnOffDevice {
  def on: Unit

  def off: Unit
}

trait SensorDevice {
  def isCoffeePresent: Boolean
}

trait IWarmer {
  def trigger
}

trait Client

/*
 * Service implementation
 */
class Heater extends OnOffDevice {
  def on = println("heater.on")

  def off = println("heater.off")
}

class PotSensor extends SensorDevice {
  def isCoffeePresent = true
}

class Warmer (@Inject val potSensor: SensorDevice, @Inject val heater: OnOffDevice) extends IWarmer {

  def trigger = {
    if (potSensor.isCoffeePresent) heater.on
    else heater.off
  }
}

/*
 * Client
 */
class MyClient (@Inject val warmer: Warmer) extends Client {
  warmer.trigger
}


/*
 * Guice's configuration class that is defining the interface-implementation bindings
 */
class DependencyModule extends Module {
  def configure(binder: Binder) = {

    binder.bind(classOf[OnOffDevice]).to(classOf[Heater])
    binder.bind(classOf[SensorDevice]).to(classOf[PotSensor])
    binder.bind(classOf[IWarmer]).to(classOf[Warmer])
    binder.bind(classOf[Client]).to(classOf[MyClient])
  }
}


// =======================
// Usage: val bean = new Bean with ServiceInjector
trait ServiceInjector {
  ServiceInjector.inject(this)
}

// helper companion object
object ServiceInjector {
  private val injector = Guice.createInjector(new DependencyModule)
  def inject(obj: AnyRef) = injector.injectMembers(obj)
}

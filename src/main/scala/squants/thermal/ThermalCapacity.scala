/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2014, Gary Keorkunian                                       **
**                                                                      **
\*                                                                      */

package squants.thermal

import squants._
import squants.energy.{ Energy, Joules }

/**
 * Represents the capacity of some substance or system to hold thermal energy.
 *
 * Also a representation of Entropy
 *
 * @author  garyKeorkunian
 * @since   0.1
 *
 * @param value the value in [[squants.thermal.JoulesPerKelvin]]
 */
final class ThermalCapacity private (val value: Double) extends Quantity[ThermalCapacity] {

  def valueUnit = ThermalCapacity.valueUnit

  def *(that: Temperature): Energy = Joules(toJoulesPerKelvin * that.toKelvinScale)

  def toJoulesPerKelvin = to(JoulesPerKelvin)
}

object ThermalCapacity extends QuantityCompanion[ThermalCapacity] {
  private[thermal] def apply[A](n: A)(implicit num: Numeric[A]) = new ThermalCapacity(num.toDouble(n))
  def apply(s: String) = parseString(s)
  def name = "ThermalCapacity"
  def valueUnit = JoulesPerKelvin
  def units = Set(JoulesPerKelvin)
}

trait ThermalCapacityUnit extends UnitOfMeasure[ThermalCapacity] with UnitMultiplier {
  def apply[A](n: A)(implicit num: Numeric[A]) = ThermalCapacity(convertFrom(n))
}

object JoulesPerKelvin extends ThermalCapacityUnit with ValueUnit {
  def symbol = "J/K"
}

object ThermalCapacityConversions {
  lazy val joulePerKelvin = JoulesPerKelvin(1)

  implicit class ThermalCapacityConversions[A](n: A)(implicit num: Numeric[A]) {
    def joulesPerKelvin = JoulesPerKelvin(n)
  }

  implicit object ThermalCapacityNumeric extends AbstractQuantityNumeric[ThermalCapacity](ThermalCapacity.valueUnit)
}

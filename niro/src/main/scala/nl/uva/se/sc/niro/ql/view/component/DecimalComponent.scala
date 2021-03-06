package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, DecimalAnswer }
import nl.uva.se.sc.niro.ql.view.widget.Widget

import scala.collection.mutable

case class DecimalComponent(id: String, label: Label, control: Widget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {

  override def getValue: Option[DecimalAnswer] = Option(control.value).map(DecimalAnswer(_))

  override def setValue(value: Option[BigDecimal]): Unit = control.value(fromOption(value))

  override def updateValue(valueStore: mutable.Map[String, Answer]): Unit =
    setValue(valueStore.get(id).map(convert))

  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal = value.map(_.bigDecimal).orNull

  def convert(answer: Answer): BigDecimal = answer match {
    case DecimalAnswer(value) => value
  }
}

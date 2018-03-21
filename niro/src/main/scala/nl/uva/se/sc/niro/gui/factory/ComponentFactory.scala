package nl.uva.se.sc.niro.gui.factory

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql._

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

class QLComponentFactory(componentChangeListener: ComponentChangedListener, widgetFactory: WidgetFactory)
    extends ComponentFactory {

  def make(question: GUIQuestion): Component[_] = {
    val component = makeComponent(question)
    component.addComponentChangedListener(componentChangeListener)
    component.setReadOnly(question.isReadOnly)
    question.component = Some(component)
    component
  }

  def makeComponent(question: GUIQuestion): Component[_] = {
    question.answerType match {
      case StringType =>
        StringComponent(question.id, new Label(question.label), widgetFactory.makeStringWidget(question))
      case BooleanType =>
        BooleanComponent(question.id, new Label(question.label), widgetFactory.makeBooleanWidget(question))
      case DateType =>
        DateComponent(question.id, new Label(question.label), widgetFactory.makeDateWidget(question))
      case IntegerType =>
        IntegerComponent(question.id, new Label(question.label), widgetFactory.makeIntegerWidget(question))
      case DecimalType =>
        DecimalComponent(question.id, new Label(question.label), widgetFactory.makeDecimalWidget(question))
      case MoneyType =>
        MoneyComponent(question.id, new Label(question.label), widgetFactory.makeMoneyWidget(question))
    }
  }
}

object QLComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener) =
    new QLComponentFactory(componentChangeListener, new QLWidgetFactory())
}

object QLSComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener) =
    new QLComponentFactory(componentChangeListener, new QLSWidgetFactory())
}
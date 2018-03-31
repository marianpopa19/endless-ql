package nl.uva.se.sc.niro.gui.component.ql

import nl.uva.se.sc.niro.gui.component.{ ComponentFactory, ComponentFactoryAddValueChangedListenerDecorator, ComponentFactoryBindComponentToQuestion, ComponentFactoryIsReadOnlyDecorator }
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.gui.widget.WidgetFactory

class QLComponentFactoryBuilder {
  private var componentChangeListener: ComponentChangedListener = _
  private var widgetFactory: WidgetFactory = _
  private var isWithBind = false
  private var isWithReadOnly = false

  def buildWith(widgetFactory: WidgetFactory): QLComponentFactoryBuilder = {
    this.widgetFactory = widgetFactory
    this
  }

  def buildWith(componentChangeListener: ComponentChangedListener): QLComponentFactoryBuilder = {
    this.componentChangeListener = componentChangeListener
    this
  }

  def buildWithBind(): QLComponentFactoryBuilder = {
    isWithBind = true
    this
  }

  def buildWithIsReadonly(): QLComponentFactoryBuilder = {
    isWithReadOnly = true
    this
  }

  def build(): ComponentFactory = {
    var componentFactory: ComponentFactory = new QLComponentFactory(widgetFactory)
    if (componentChangeListener != null)
      componentFactory = new ComponentFactoryAddValueChangedListenerDecorator(componentChangeListener, componentFactory)
    if (isWithBind)
      componentFactory = new ComponentFactoryBindComponentToQuestion(componentFactory)
    if (isWithReadOnly)
      componentFactory = new ComponentFactoryIsReadOnlyDecorator(componentFactory)
    componentFactory
  }

}

object QLComponentFactoryBuilder extends QLComponentFactoryBuilder

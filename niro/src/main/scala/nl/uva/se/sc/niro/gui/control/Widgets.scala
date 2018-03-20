package nl.uva.se.sc.niro.gui.control

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control._
import javafx.scene.layout.HBox
import javafx.util.StringConverter
import javafx.util.converter.LocalDateStringConverter
import nl.uva.se.sc.niro.gui.builder.TextFormatterBuilder
import nl.uva.se.sc.niro.gui.factory.QLWidgetFactory._
import nl.uva.se.sc.niro.gui.listener.ValueChangedListener

import scala.collection.mutable.ArrayBuffer

trait QLWidget[T] extends Node {
  private val valueChangedListeners = ArrayBuffer[ValueChangedListener]()
  def setValue(value: T): Unit
  def getValue: T
  def setPrefWidth(width: Double): Unit
  def addValueChangedListener(valueChangedListener: ValueChangedListener): Unit =
    valueChangedListeners.append(valueChangedListener)
  protected def valueChanged: Unit =
    valueChangedListeners.foreach(_.valueChanged(this))
}

abstract class AbstractQLTextField[T]() extends TextField with QLWidget[T] {
  focusedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      if (oldValue) valueChanged
  })
}

class QLBooleanField extends CheckBox with QLWidget[Boolean] {
  selectedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      valueChanged
  })
  override def setValue(value: Boolean): Unit = setSelected(value)
  override def getValue: Boolean = isSelected
}

class QLComboBooleanField(trueLabel: String, falseLabel: String) extends ChoiceBox[Boolean] with QLWidget[Boolean] {
  setItems(FXCollections.observableArrayList(true, false))
  setConverter(new StringConverter[Boolean]() {
    override def toString(value: Boolean): String = if (value) trueLabel else falseLabel
    override def fromString(value: String): Boolean = value == trueLabel
  })
  valueProperty().addListener(new ChangeListener[Boolean] {
    override def changed(observable: ObservableValue[_ <: Boolean], oldValue: Boolean, newValue: Boolean): Unit =
      valueChanged
  })
}

class QLRadioBooleanField(trueLabel: String, falseLabel: String) extends HBox with QLWidget[Boolean] {
  val group = new ToggleGroup()
  val trueChoice = new RadioButton(trueLabel)
  val falseChoice = new RadioButton(falseLabel)
  trueChoice.setToggleGroup(group)
  falseChoice.setToggleGroup(group)
  getChildren.addAll(trueChoice, falseChoice)
  setPadding(new Insets(3.0, 0.0, 5.0, 0.0))
  setSpacing(5.0)

  override def setValue(value: Boolean): Unit = group.selectToggle(if (value) trueChoice else falseChoice)
  override def getValue: Boolean = group.getSelectedToggle == trueChoice
  group
    .selectedToggleProperty()
    .addListener(new ChangeListener[Toggle] {
      override def changed(observable: ObservableValue[_ <: Toggle], oldValue: Toggle, newValue: Toggle): Unit =
        valueChanged
    })
}

class QLDateField() extends DatePicker with QLWidget[LocalDate] {
  valueProperty().addListener(new ChangeListener[LocalDate] {
    override def changed(observable: ObservableValue[_ <: LocalDate], oldValue: LocalDate, newValue: LocalDate): Unit =
      valueChanged
  })
  private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
  setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
}

class QLTextField() extends AbstractQLTextField[String] {
  textProperty().addListener(new ChangeListener[String] {
    override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit =
      valueChanged
  })
  override def setValue(value: String): Unit = setText(value)
  override def getValue: String = getText
}

class QLIntegerField() extends AbstractQLTextField[java.lang.Integer] {
  private val integerFormatter =
    TextFormatterBuilder[Integer]().buildInputFilter(INTEGER_MASK).buildIntegerConverter().build()
  setTextFormatter(integerFormatter)
  override def setValue(value: java.lang.Integer): Unit = integerFormatter.setValue(value)
  override def getValue: java.lang.Integer = integerFormatter.getValue
}

class QLDecimalField() extends AbstractQLTextField[java.math.BigDecimal] {
  private val decimalFormatter =
    TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(DECIMAL_MASK).buildDecimalConverter().build()
  setTextFormatter(decimalFormatter)
  override def setValue(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def getValue: java.math.BigDecimal = decimalFormatter.getValue
}

class QLMoneyField() extends AbstractQLTextField[java.math.BigDecimal] {
  private val decimalFormatter =
    TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(MONEY_MASK).buildDecimalConverter().build()
  setTextFormatter(decimalFormatter)
  override def setValue(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def getValue: java.math.BigDecimal = decimalFormatter.getValue
}
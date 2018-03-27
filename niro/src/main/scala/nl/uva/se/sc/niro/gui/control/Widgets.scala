package nl.uva.se.sc.niro.gui.control

import java.lang
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory
import javafx.scene.control._
import javafx.scene.layout.HBox
import javafx.util.StringConverter
import javafx.util.converter.LocalDateStringConverter
import nl.uva.se.sc.niro.gui.builder.{ DecimalFormatterBuilder, IntegerFormatterBuilder }
import nl.uva.se.sc.niro.gui.listener.ValueChangedListener

import scala.collection.mutable.ArrayBuffer

trait QLWidget[T] extends Node {
  private val valueChangedListeners = ArrayBuffer[ValueChangedListener]()
  def value(value: T): Unit
  def value: T
  def setPrefWidth(width: Double): Unit
  def addValueChangedListener(valueChangedListener: ValueChangedListener): Unit =
    valueChangedListeners.append(valueChangedListener)
  protected def valueChanged(): Unit =
    valueChangedListeners.foreach(_.valueChanged(this))
}

abstract class AbstractQLTextField[T]() extends TextField with QLWidget[T] {
  focusedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      if (oldValue) valueChanged()
  })
}

class QLBooleanField extends CheckBox with QLWidget[Boolean] {
  selectedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      valueChanged()
  })
  override def value(value: Boolean): Unit = setSelected(value)
  override def value: Boolean = isSelected
}

class QLSComboBooleanField(trueLabel: String, falseLabel: String) extends ChoiceBox[Boolean] with QLWidget[Boolean] {
  setItems(FXCollections.observableArrayList(true, false))
  setConverter(new StringConverter[Boolean]() {
    override def toString(value: Boolean): String = if (value) trueLabel else falseLabel
    override def fromString(value: String): Boolean = value == trueLabel
  })
  valueProperty().addListener(new ChangeListener[Boolean] {
    override def changed(observable: ObservableValue[_ <: Boolean], oldValue: Boolean, newValue: Boolean): Unit =
      valueChanged()
  })
  override def value(newValue: Boolean): Unit = setValue(newValue)
  override def value: Boolean = getValue
}

class QLSRadioBooleanField(trueLabel: String, falseLabel: String) extends HBox with QLWidget[Boolean] {
  val group = new ToggleGroup()
  val trueChoice = new RadioButton(trueLabel)
  val falseChoice = new RadioButton(falseLabel)
  trueChoice.setToggleGroup(group)
  falseChoice.setToggleGroup(group)
  getChildren.addAll(trueChoice, falseChoice)
  setPadding(new Insets(3.0, 0.0, 5.0, 0.0))
  setSpacing(5.0)

  override def value(value: Boolean): Unit = group.selectToggle(if (value) trueChoice else falseChoice)
  override def value: Boolean = group.getSelectedToggle == trueChoice
  group
    .selectedToggleProperty()
    .addListener(new ChangeListener[Toggle] {
      override def changed(observable: ObservableValue[_ <: Toggle], oldValue: Toggle, newValue: Toggle): Unit =
        valueChanged()
    })
}

class QLDateField() extends DatePicker with QLWidget[LocalDate] {
  val DATE_FORMAT = "yyyy-MM-dd"
  valueProperty().addListener(new ChangeListener[LocalDate] {
    override def changed(observable: ObservableValue[_ <: LocalDate], oldValue: LocalDate, newValue: LocalDate): Unit =
      valueChanged()
  })
  private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
  setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
  override def value(value: LocalDate): Unit = setValue(value)
  override def value: LocalDate = getValue
}

class QLTextField() extends AbstractQLTextField[String] {
  textProperty().addListener(new ChangeListener[String] {
    override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit =
      valueChanged()
  })
  override def value(value: String): Unit = setText(value)
  override def value: String = getText
}

class QLIntegerField() extends AbstractQLTextField[java.math.BigInteger] {
  val INTEGER_MASK = "\\d*"
  private val integerFormatter =
    IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build()
  setTextFormatter(integerFormatter)
  override def value(value: java.math.BigInteger): Unit = integerFormatter.setValue(value)
  override def value: java.math.BigInteger = integerFormatter.getValue
}

class QLSIntegerSpinField()
    extends Spinner[Integer](new IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0))
    with QLWidget[java.math.BigInteger] {
  setEditable(true)
  valueProperty().addListener(new ChangeListener[Integer] {
    override def changed(observable: ObservableValue[_ <: Integer], oldValue: Integer, newValue: Integer): Unit =
      valueChanged()
  })
  focusedProperty().addListener(new ChangeListener[lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: lang.Boolean],
        oldValue: lang.Boolean,
        newValue: lang.Boolean): Unit = {
      if (!newValue) {
        increment(0)
        valueChanged()
      }
    }
  })
  override def value(newValue: java.math.BigInteger): Unit =
    if (newValue != null) getValueFactory.valueProperty().setValue(newValue.intValue())
  override def value: java.math.BigInteger =
    java.math.BigInteger.valueOf(getValueFactory.valueProperty().getValue.longValue())
}

class QLDecimalField() extends AbstractQLTextField[java.math.BigDecimal] {
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  private val decimalFormatter = DecimalFormatterBuilder().buildInputFilter(DECIMAL_MASK).buildConverter().build()
  setTextFormatter(decimalFormatter)
  override def value(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def value: java.math.BigDecimal = decimalFormatter.getValue
}

class QLMoneyField() extends AbstractQLTextField[java.math.BigDecimal] {
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  private val decimalFormatter = DecimalFormatterBuilder().buildInputFilter(MONEY_MASK).buildConverter().build()
  setTextFormatter(decimalFormatter)
  override def value(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def value: java.math.BigDecimal = decimalFormatter.getValue
}

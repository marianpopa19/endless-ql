package nl.uva.se.sc.niro.ql.model.ast.expressions

import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

object Conversions {
  implicit class IntAnswerConversions(integerAnswer: IntegerAnswer) {
    def toDecimal = DecimalAnswer(BigDecimal(integerAnswer.value))
    def toMoney = MoneyAnswer(BigDecimal(integerAnswer.value))
  }

  implicit class DecimalAnswerConversions(decimalAnswer: DecimalAnswer) {
    def toMoney = MoneyAnswer(decimalAnswer.value)
  }
}

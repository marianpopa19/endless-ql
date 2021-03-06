package nl.uva.se.sc.niro.ql.model.ast.expressions.answers

import nl.uva.se.sc.niro.model.ql.evaluation.Logicals.BooleanAnswerCanDoLogicals._
import nl.uva.se.sc.niro.ql.model.ast.evaluation.Orderings.BooleanAnswerCanDoOrderings._

final case class BooleanAnswer(value: Boolean) extends Answer {

  type T = Boolean

  override def isTrue: Boolean = value

  override def lessThan(right: Answer): Answer = this < right
  override def lessThanEquals(right: Answer): Answer = this <= right
  override def greaterThenEquals(right: Answer): Answer = this >= right
  override def greaterThen(right: Answer): Answer = this > right
  override def notEquals(right: Answer): Answer = this !== right
  override def equals(right: Answer): Answer = this === right

  override def and(right: Answer): Answer = this && right
  override def or(right: Answer): Answer = this || right
  override def negate: Answer = !this
}

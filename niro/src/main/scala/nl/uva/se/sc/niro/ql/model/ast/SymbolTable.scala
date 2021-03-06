package nl.uva.se.sc.niro.ql.model.ast

import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression

object SymbolTable {
  def createSymbolTable(qLForm: QLForm): Map[String, Symbol] = {
    Statement
      .collectAllQuestions(qLForm.statements)
      .map(q => q.id -> Symbol(q.answerType, q.expression))
      .toMap
  }

  type SymbolTable = Map[String, Symbol]
}

case class Symbol(answerType: AnswerType, expression: Option[Expression])

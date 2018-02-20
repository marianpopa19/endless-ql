package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.model.ql.AST.ExprType.ExprType

object AST {

  sealed trait Node {
    def getChildren: Seq[Node]
  }

  object ExprType extends Enumeration {
    type ExprType = Value
    val String, Date, Bool, Integer, Decimal, Money = Value
  }

  case class Form(name: String, statements: Seq[Statement]) extends Node {
    override def getChildren: Seq[Node] = statements
  }

  sealed trait Statement extends Node
  case class Question(label: String, answerValue: AnswerValue) extends Statement {
    override def getChildren: Seq[Node] = Seq(answerValue)
  }
  case class Conditional(condition: Expression, thenBlock: Seq[Statement], elseBlock: Seq[Statement]) extends Statement {
    override def getChildren: Seq[Node] = Seq(condition) ++ thenBlock ++ elseBlock
  }

  sealed trait Expression extends Node {
    def exprType: ExprType
  }

  sealed trait Leaf extends Expression {
    override def getChildren: Seq[Node] = Seq.empty
  }
  case class Constant(exprType: ExprType, value: Any) extends Leaf
  sealed trait AnswerValue extends Leaf
  sealed trait AnswerVariable extends AnswerValue
  case class VariableDefinition(name: String, exprType: ExprType) extends AnswerVariable
  case class VariableDeclaration(name: String, exprType: ExprType, expression: Expression) extends AnswerVariable {
    override def getChildren: Seq[Node] = Seq(expression)
  }
  case class Variable(name: String) extends AnswerValue {
    var answerVariable: AnswerVariable = null
    override def exprType: ExprType = answerVariable.exprType
  }

  // Operators
  sealed trait Operator

  sealed trait UnaryOperator extends Operator
  object UnaryOperator extends UnaryOperator {
    case object NEG extends UnaryOperator
    case object MIN extends UnaryOperator
  }

  sealed trait BinaryOperator extends Operator
  sealed trait LogicalOperator extends BinaryOperator
  object LogicalOperator extends LogicalOperator {
    case object AND extends LogicalOperator
    case object OR extends LogicalOperator
  }
  sealed trait ArithmeticOperator extends BinaryOperator
  object ArithmeticOperator extends ArithmeticOperator {
    case object SUB extends ArithmeticOperator
    case object ADD extends ArithmeticOperator
    case object DIV extends ArithmeticOperator
    case object MUL extends ArithmeticOperator
  }
  sealed trait ComparisonOperator extends BinaryOperator
  object ComparisonOperator extends ComparisonOperator {
    case object LT extends ComparisonOperator
    case object LTE extends ComparisonOperator
    case object GTE extends ComparisonOperator
    case object GT extends ComparisonOperator
    case object EQ extends ComparisonOperator
    case object NE extends ComparisonOperator
  }

  // Operation expressions
  sealed trait Operation extends Expression

  case class UnaryOperation(op: UnaryOperator, expr: Expression) extends Operation {
    override def getChildren: Seq[Node] = Seq(expr)
    override def exprType: ExprType = expr.exprType
  }

  case class BinaryOperation(op: BinaryOperator, left: Expression, right: Expression) extends Operation {
    override def getChildren: Seq[Node] = Seq(left, right)
    override def exprType: ExprType = {
      op match {
        case LogicalOperator => ExprType.Bool
        case ComparisonOperator => ExprType.Bool
        case ArithmeticOperator => (left.exprType, right.exprType) match {
          case (ExprType.Money, _) => ExprType.Money
          case (_, ExprType.Money) => ExprType.Money
          case (ExprType.Decimal, _) => ExprType.Decimal
          case (_, ExprType.Decimal) => ExprType.Decimal
          case _ => ExprType.Integer
        }
        case other => throw new IllegalArgumentException(s"Unsupported binary operation: $other")
      }
    }
  }

}

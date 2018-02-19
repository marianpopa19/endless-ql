package nl.uva.se.sc.niro.mode.ql

import nl.uva.se.sc.niro.model.ql.AST.{Question, _}
import nl.uva.se.sc.niro.model.ql.AST.ExprType._
import org.scalatest.FunSuite

class ASTTest extends FunSuite {

  test("Just check the console for now") {
    val expected = Form("NewASTForm", Seq(
      Question("Did you sell your house this year?", VariableDefinition("hasSoldHouse", Bool)),
      Conditional(Variable("hasSoldHouse"),
        Seq(
          Question("What is the price you sold your house for?", VariableDefinition("priceOfHouse", Money))
        ),
        Seq(
          Question("Are you going to sell your house this year?", VariableDefinition("willSellHouse", Bool)),
          Question("Tax when selling house", VariableDeclaration("houseSellingTax", Money, Constant(Money, 1500.00)))
        )
      )
    ))
    println(expected)
  }
}

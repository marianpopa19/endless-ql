import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class UnOpConditionalSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions/unop"
  val validator = new ConditionalValidator()

  describe("containing invalid not money expression in unary") {
    val filename = s"${resourceDir}/not_money_unop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should return an option exception") {
      a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
    }
  }

  describe("containing valid not boolean expression in unary") {
    val filename = s"${resourceDir}/not_boolean_unop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("containing valid binop expression in unary") {
    val filename = s"${resourceDir}/not_binop_unop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      noException should be thrownBy validator.execute(form)
    }
  }
}

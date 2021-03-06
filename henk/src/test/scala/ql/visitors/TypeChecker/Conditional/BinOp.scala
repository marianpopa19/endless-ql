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

class BinOpConditional extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions/binop"
  val validator = new ConditionalValidator()

  describe("conjunction") {
    describe("containing a left nested boolean and money binop") {
      val filename = s"${resourceDir}/nested/invalid_left_nested.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should return an option exception") {
        a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
      }
    }

    describe("containing a right nested boolean and money binop") {
      val filename = s"${resourceDir}/nested/invalid_right_nested.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should return an option exception") {
        a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
      }
    }

    describe("containing two valid binops on both sides") {
      val filename = s"${resourceDir}/nested/both_valid_nested.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
      }
    }

    describe("containing a not unary on the right side") {
      val filename = s"${resourceDir}/left_not_binop.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
      }
    }

    describe("containing two invalid binops on both sides") {
      val filename = s"${resourceDir}/nested/both_invalid_nested.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should return an option exception") {
        a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
      }
    }
  }

  describe("disjunction") {
    describe("containing two booleans") {
      val filename = s"${resourceDir}/dis/valid.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
      }
    }

    describe("containing a boolean and money") {
      val filename = s"${resourceDir}/dis/invalid.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should return an option exception") {
        a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
      }
    }
  }

  describe("less than") {
    describe("containing two money types") {
      val filename = s"${resourceDir}/lt/simple.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
      }
    }

    describe("containing two boolean types") {
      val filename = s"${resourceDir}/lt/boolean_lt_boolean.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should return an option exception") {
        a [ConditionalNotBoolean] should be thrownBy validator.execute(form)
      }
    }

    describe("containing a negate") {
      val filename = s"${resourceDir}/lt/simple_not.ql"
      val form = FormHelper.getRoot(getClass.getResource(filename))

      it("check should not return an option exception") {
        noException should be thrownBy validator.execute(form)
      }
    }
  }
}

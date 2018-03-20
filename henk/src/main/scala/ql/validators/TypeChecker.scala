package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

class TypeChecker() {

  var error: Exception = null
  var warnings: Option[List[String]] = None

  val validatorList: List[BaseValidator] = List(
    new IdentifierValidator(),
    new ConditionalValidator(),
    new DuplicateQuestionValidator(),
    new DuplicateLabelValidator()
  )

  def checkValidators(node: ASTNode): Option[Exception] = {
    validatorList.map(vc => {
      vc.execute(node) match {
        case bv @ Some(ex: IdentifierNotDeclared) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: ConditionalNotBoolean) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: DuplicateQuestionDeclaration) => {
          error = ex
          return bv
        }
        case Some(ex: DuplicateLabelDeclaration) => {
          warnings = vc.getWarnings()
        }
        case other => other
      }
    })
    None
  }

  def validate(node: ASTNode): Boolean = {
    checkValidators(node) match {
      case None => true
      case Some(_) => false
    }
  }
}

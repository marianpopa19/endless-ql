package nl.uva.se.sc.niro.gui.converter

import nl.uva.se.sc.niro.gui.factory.{ GUIConditionalFactory, GUIQuestionFactory }
import nl.uva.se.sc.niro.model.gui._
import nl.uva.se.sc.niro.model.ql.expressions.Expression
import nl.uva.se.sc.niro.model.ql.expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.ql.{ Conditional, QLForm, Statement }
import nl.uva.se.sc.niro.model.qls._
import nl.uva.se.sc.niro.util.StringUtil

/**
  * Converts a AST model into a GUI model. During this conversion the AST model is flattened. In the GUI model the
  * questions are represented as a list. If a question in the AST appears within a (nested) if-construct the
  * visibility property of GUI question will consist of all intermediate expressions logical 'and'ed to ensure the
  * desired behaviour.
  */
object GUIModelFactory {
  def makeFrom(form: QLForm): GUIForm = {
    GUIForm(StringUtil.addSpaceOnCaseChange(form.formName), make(BooleanAnswer(true), form.statements))
  }

  def make(visible: Expression, statements: Seq[Statement]): Seq[GUIQuestion] = {
    statements.flatMap(statement =>
      statement match {
        case question: nl.uva.se.sc.niro.model.ql.Question =>
          Seq(GUIQuestionFactory.makeGUIQuestion(visible, question))
        case conditional: Conditional =>
          GUIConditionalFactory.makeGUIConditional(visible, conditional)
        case _ =>
          Seq.empty
    })
  }

  def makeFrom(stylesheet: QLStylesheet): GUIStylesheet = {
    val defaultStyles = stylesheet.defaultStyles.mapValues(GUIStyle(_))
    GUIStylesheet(StringUtil.addSpaceOnCaseChange(stylesheet.name), stylesheet.pages.map(make), defaultStyles)
  }

  def make(page: Page): GUIPage = {
    val defaultStyles = page.defaultStyles.mapValues(GUIStyle(_))
    GUIPage(StringUtil.addSpaceOnCaseChange(page.name), page.sections.map(make), defaultStyles)
  }

  def make(section: Section): GUISection = {
    val defaultStyles = section.defaultStyles.mapValues(GUIStyle(_))
    GUISection(section.name, section.questions.map(make), defaultStyles)
  }

  def make(question: Question): GUIStyledQuestion =
    GUIStyledQuestion(question.name, question.widgetType.map(GUIStyle(_)))
}
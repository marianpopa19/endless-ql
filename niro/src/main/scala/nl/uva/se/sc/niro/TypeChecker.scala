package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.expressions.Reference
import nl.uva.se.sc.niro.model.expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ Conditional, QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

object TypeChecker extends Logging {

  def pipeline(qLForm: QLForm) =
    for {
      _ <- checkReferences(qLForm)
      _ <- checkNonBooleanPredicates(qLForm)
      _ <- checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm)
      _ <- checkCyclicDependenciesBetweenQuestions(qLForm)
      _ <- checkDuplicateLabels(qLForm)
    } yield qLForm

  // TODO implement checkOperandsOfInvalidTypeToOperators
  private def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 1 - Checking operands of invalid type to operators ...")

    Right(qLForm)
  }

  private def checkReferences(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 2 - Checking references to undefined questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val references: Seq[Reference] = Statement.collectAllReferences(questions)
    val undefinedReferences: Seq[String] = references.map(_.value).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      Left(TypeCheckError(message = s"Undefined references: $undefinedReferences"))
    } else {
      Right(qLForm)
    }
  }

  private def checkNonBooleanPredicates(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filter { conditional =>
      Evaluator.evaluateExpression(conditional.predicate, qLForm.symbolTable) match {
        case _: BooleanAnswer => false
        case _                => true
      }
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      Left(TypeCheckError(message = s"Non boolean predicate: $conditionalsWithNonBooleanPredicates"))
    } else {
      Right(qLForm)
    }
  }

  private def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Seq[Seq[Question]] = duplicateQuestions
      .filter(questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)
      .toList

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      Left(
        TypeCheckError(
          message = s"Duplicate question declarations with different types: $duplicateQuestionsWithDifferentTypes"))
    } else {
      Right(qLForm)
    }
  }

  private def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 5 - Checking cyclic dependencies between questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)

    val dependencyGraph: Graph = buildDependencyGraph(questions)

    val cyclicDependencies: Boolean =
      dependencyGraph.exists(element => detectCycle(element, dependencyGraph, Seq(element)))
    if (cyclicDependencies) {
      Left(TypeCheckError(message = s"Found cyclic dependency"))
    } else {
      Right(qLForm)
    }
  }

  type Edge = (String, String)
  type Graph = Seq[Edge]

  private def buildDependencyGraph(questions: Seq[Question]): Graph = {
    questions.flatMap {
      case q @ Question(_, _, _, r @ Reference(_), _) => Seq(q.id -> r.value)
      case q @ Question(_, _, _, expression, _)       => Statement.collectAllReferences(expression).map(r => q.id -> r.value)
    }
  }

  private def detectCycle(currentEdge: Edge, graph: Graph, followedPath: Graph): Boolean = {
    logger.info(
      s"Detecting cycles for starting element: ${followedPath.head} in graph: $graph. Now following: $currentEdge")

    val connectedEdges: Seq[Edge] = graph.filter { case (from, _) => from == currentEdge._2 }

    if (connectedEdges.isEmpty) {
      logger.info(s"No cycle detected in ${followedPath.tail :+ currentEdge} for element ${followedPath.head}")
      false
    } else {
      connectedEdges.exists { connectedEdge =>
        if (followedPath.head._1 == connectedEdge._2) {
          val completePath = ((followedPath.tail :+ currentEdge).map(_._1) :+ connectedEdge._2).mkString(" -> ")
          logger.info(s"Detected a cycle: $completePath")
          true
        } else {
          detectCycle(connectedEdge, graph, followedPath :+ currentEdge)
        }
      }
    }
  }

  // TODO this function should not throw an error. Somehow we should give a warning when duplicate labels are detected
  private def checkDuplicateLabels(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Seq[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1).toList

    if (questionsWithDuplicateLabels.nonEmpty) {
      Left(TypeCheckError(message = s"Found questions with duplicate labels"))
    } else {
      Right(qLForm)
    }
  }
}

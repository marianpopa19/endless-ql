package org.uva.sc.cr.ql.interpreter.service

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.concurrent.Callable
import javafx.beans.binding.Binding
import javafx.beans.binding.Bindings
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapper
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluator
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluatorMoney
import org.uva.sc.cr.ql.qL.Expression

@Singleton
class BindingBuilder {

	@Inject
	var ExpressionEvaluator evaluator

	@Inject
	var ExpressionEvaluatorMoney evaluatorMoney

	List<Binding> bindings;

	new() {
		bindings = new ArrayList();
	}

	def invalidateBindings() {
		bindings.forEach [
			it.invalidate()
		]
	}

	def buildBindingForTypeBoolean(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createBooleanBinding(new Callable<Boolean>() {

			override call() throws Exception {
				return evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Boolean)
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeString(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				return evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), String)
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeInteger(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				return evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Double).
					intValue().toString()
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeDecimal(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				return evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Double).
					toString()
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeMoney(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				return evaluatorMoney.evaluateExpression(
					expression,
					getExpressionArguments(controls, expression)
				).amount.toString()
			}

		})
		bindings.add(binding)
		return binding
	}

	def private getExpressionArguments(List<ControlWrapper> controls, Expression exp) {
		val result = new HashMap<String, Object>()
		controls.forEach [ control |
			result.put(control.name, control.value)
		]
		return result
	}

}

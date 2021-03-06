package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;

public class ErrorHandler extends ErrorReporter<String> {

	private class Error {
		private final String message;

		public Error(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	private class TypeError extends Error {

		public TypeError(String message) {
			super(message);
		}

		@Override
		public String toString() {
			return super.getMessage();
		}
	}

	private class IdentifierError extends Error {


		private final String name;
		private final int line;
		private final int column;

		public IdentifierError(String name, int line, int column, String message) {
			super(message);
			this.name = name;
			this.line = line;
			this.column = column;
		}

		public String getName() {
			return name;
		}

		public int getLine() {
			return line;
		}

		public int getColumn() {
			return column;
		}

		@Override
		public String toString() {
			return String.format("[%s] line: %d, column: %d: %s: %s", moduleName,
					getLine(),
					getColumn(),
					getMessage(),
					getName());
		}
	}

	private final String moduleName;
	private boolean printErrors;

	public ErrorHandler(String moduleName, boolean printErrors) {
		this.moduleName = moduleName;
		this.printErrors = printErrors;
	}

	public int getNumberOfErrors() {
		return this.getNumberOfReports();
	}

	public void addIdentifierError(QLToken token, String message) {
		Error error = new IdentifierError(token.getLexeme(), token.getLine(), token.getColumn(), message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addTypeError(Expression expr, QLType...allowedTypes) {

		ArrayList<String> typeNames = new ArrayList<>();
		Arrays.asList(allowedTypes).forEach((type) -> typeNames.add(type.getName()));

		String message = String.format("[%s] line: %d, column: %d: Type mismatch, requested type: %s, allowed types: %s",
				moduleName,
				expr.getLineNumber(),
				expr.getColumnNumber(),
				expr.getType(),
				String.join(" or ", typeNames));

		Error error = new TypeError(message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addTypeError(Expression parentExpr, Expression childExpr) {
		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				childExpr.getLineNumber(),
				childExpr.getColumnNumber(),
				parentExpr.getType(),
				childExpr.getType());

		Error error = new TypeError(message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addTypeError(Expression leftExpr, QLToken operator, Expression rightExpr) {
		String message = String.format("[%s] line: %d, column: %d: Operator %s is undefined for types: %s and %s",
				moduleName,
				operator.getLine(),
				operator.getColumn(),
				operator.getLexeme(),
				leftExpr.getType(),
				rightExpr.getType());

		Error error = new TypeError(message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addTypeError(ComputedQuestionStatement statement) {

		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				statement.getExpressionLineNumber(),
				statement.getExpressionColumnNumber(),
				statement.getType(),
				statement.getExpressionType());

		Error error = new TypeError(message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addTypeError(IfStatement statement) {

		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				statement.getExpressionLineNumber(),
				statement.getExpressionColumnNumber(),
				QLType.BOOLEAN,
				statement.getExpressionType());

		Error error = new TypeError(message);
		this.addReport(error.toString());
		if (printErrors)
			System.err.println(error.toString());
	}

	public void addCyclicQuestionError(ArrayList<Set<String>> cycleSet) {
		for (Set<String> set : cycleSet) {
			String error = String.format("[CyclicDependencyChecker] A cyclic dependency was found between the following questions: %s",
					String.join(", ", set));
			this.addReport(error);
			if (printErrors)
				System.err.println(error);
		 }
	}

	public void clearErrors() {
		this.clear();
	}

	public String getErrorAtIndex(int index) {
		return this.getReport(index);
	}
}

package org.uva.jomi.ql.interpreter;

public class StringValue implements GenericValue {
	private final String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	// Addition.

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return rightHandSideValue.add(this);
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		return new StringValue(leftHandSideValue.getValue().concat(this.getValue()));
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Subtraction.

	@Override
	public GenericValue subtract(GenericValue rightHandSideValue) {
		subtractionError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(IntegerValue leftHandSideValue) {
		subtractionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(StringValue leftHandSideValue) {
		subtractionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(BooleanValue leftHandSideValue) {
		subtractionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Multiplication.

	@Override
	public GenericValue multiply(GenericValue rightHandSideValue) {
		multiplicationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(IntegerValue leftHandSideValue) {
		multiplicationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(StringValue leftHandSideValue) {
		multiplicationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(BooleanValue leftHandSideValue) {
		multiplicationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Division.

	@Override
	public GenericValue divide(GenericValue rightHandSideValue) {
		divisionError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue divide(IntegerValue leftHandSideValue) {
		divisionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue divide(StringValue leftHandSideValue) {
		divisionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue divide(BooleanValue leftHandSideValue) {
		divisionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// And operation.

	@Override
	public GenericValue and(GenericValue rightHandSideValue) {
		andOperationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue and(IntegerValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue and(StringValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue and(BooleanValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Or operation.

	@Override
	public GenericValue or(GenericValue rightHandSideValue) {
		orOperationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue or(IntegerValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue or(StringValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue or(BooleanValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
}

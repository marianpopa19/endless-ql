package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class BooleanValue extends Value {
    private final boolean booleanValue;

    public BooleanValue(String value) {
        this.booleanValue = value.equals("true") || value.equals("TRUE");
    }

    public BooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return this.booleanValue;
    }

    @Override
    public Value and(Value value) throws EvaluationException {
        return value.and(this);
    }

    @Override
    public Value and(BooleanValue value) {
        return new BooleanValue(this.booleanValue && value.booleanValue);
    }

    @Override
    public Value isEqual(Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue == value.booleanValue);
    }

    @Override
    public Value isNotEqual(Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(BooleanValue value) {
        return new BooleanValue(this.booleanValue != value.booleanValue);
    }

    @Override
    public Value or(Value value) throws EvaluationException {
        return value.or(this);
    }

    @Override
    public Value or(BooleanValue value) {
        return new BooleanValue(this.booleanValue || value.booleanValue);
    }

    @Override
    public Value not() {
        return new BooleanValue(!this.booleanValue);
    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.BOOLEAN;
    }

    @Override
    public String toString() {
        return this.booleanValue ? "true" : "false";
    }

    public BooleanValue clone() throws CloneNotSupportedException {
        return (BooleanValue) super.clone();
    }
}

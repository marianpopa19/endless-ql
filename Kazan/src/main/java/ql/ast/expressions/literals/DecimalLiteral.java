package ql.ast.expressions.literals;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class DecimalLiteral extends Expression {

    private final double value;

    public DecimalLiteral(String value, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.value = Double.parseDouble(value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

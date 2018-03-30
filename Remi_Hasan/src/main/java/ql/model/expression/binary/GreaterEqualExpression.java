package ql.model.expression.binary;

import ql.model.expression.Expression;
import ql.visitor.IQLVisitor;

public class GreaterEqualExpression extends BinaryExpression {

    public GreaterEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

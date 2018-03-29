package ql.model.expression.binary;

import ql.IQLVisitor;
import ql.model.expression.Expression;

public class ExpressionLogicalOr extends ExpressionBinary {

    public ExpressionLogicalOr(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
package ql.model.expression.binary;

import ql.IQLVisitor;

import ql.model.expression.Expression;
import ql.model.expression.ExpressionBinary;
import org.antlr.v4.runtime.Token;

public class ExpressionComparisonEq extends ExpressionBinary {

    public ExpressionComparisonEq(Token start, Expression left, Expression right) {
        super(start, left, right);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

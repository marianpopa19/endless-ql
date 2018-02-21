package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.visitor.ExpressionVisitor;

public class LessThan extends BinaryOperation {

    public LessThan(Expression left, Expression right){
        super(left,right);
    }

    @Override
    public String toString() {
        return String.format("(%s < %s)", this.getLeft(), this.getRight());
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}

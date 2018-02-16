package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Greater extends Binary {

    public Greater(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Type getType() {
        return new Bool();
    }

    @Override
	public String toString() {
		return lhs.toString() + " > " + rhs.toString();
	}

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

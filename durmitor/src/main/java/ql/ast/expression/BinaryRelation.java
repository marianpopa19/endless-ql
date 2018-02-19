package ql.ast.expression;

import ql.ast.type.Date;
import ql.ast.type.Money;
import ql.ast.type.Numeric;

public abstract class BinaryRelation extends BinaryOperator {

    public BinaryRelation(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Numeric.class, Numeric.class));
        legalOperations.add(new BinaryOperation(this, Date.class, Date.class));
        legalOperations.add(new BinaryOperation(this, Money.class, Money.class));
    }
}

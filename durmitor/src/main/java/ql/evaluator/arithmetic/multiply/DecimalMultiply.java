package ql.evaluator.arithmetic.multiply;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.evaluator.AbstractEvaluator;

public class DecimalMultiply extends AbstractEvaluator<DecimalLiteral> {
    
    public DecimalMultiply(DecimalLiteral firstOperand) {
        super(firstOperand);
    }

    @Override
    public Literal<?> visit(BoolLiteral secondOperand) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(StrLiteral secondOperand) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(IntLiteral secondOperand) {
        return new DecimalLiteral(firstOperand.getValue() * secondOperand.getValue());
    }

    @Override
    public Literal<?> visit(DecimalLiteral secondOperand) {
        return new DecimalLiteral(firstOperand.getValue() * secondOperand.getValue());
    }

    @Override
    public Literal<?> visit(MoneyLiteral secondOperand) {
        return new MoneyLiteral(firstOperand.getValue() * secondOperand.getValue());
    }

    @Override
    public Literal<?> visit(DateLiteral secondOperand) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(UndefinedLiteral secondOperand) {
        return new UndefinedLiteral();
    }
}

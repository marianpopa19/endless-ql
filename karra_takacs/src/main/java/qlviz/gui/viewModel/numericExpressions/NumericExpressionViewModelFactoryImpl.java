package qlviz.gui.viewModel.numericExpressions;

import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.expressions.numericExpressions.BinaryNumericOperation;
import qlviz.model.expressions.numericExpressions.NumericExpression;
import qlviz.model.expressions.numericExpressions.NumericLiteral;
import qlviz.model.expressions.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestionReference;

public class NumericExpressionViewModelFactoryImpl implements NumericExpressionViewModelFactory, TypedNumericExpressionVisitor<NumericExpressionViewModel> {
    @Override
    public NumericExpressionViewModel create(NumericExpression expression) {
        return expression.accept(this);
    }

    @Override
    public NumericExpressionViewModel visit(BinaryNumericOperation binaryNumericOperation) {
        return new BinaryNumericOperationViewModel(
                binaryNumericOperation.getLeftSide().accept(this),
                binaryNumericOperation.getRightSide().accept(this),
                binaryNumericOperation.getOperator());
    }

    @Override
    public NumericExpressionViewModel visit(NumericLiteral numericLiteral) {
        return new NumericLiteralViewModel(numericLiteral.getValue());
    }

    @Override
    public NumericExpressionViewModel visit(NumericNegation numericNegation) {
        return new NumericNegationViewModel(numericNegation.getInnerExpression().accept(this));
    }

    @Override
    public NumericExpressionViewModel visit(NumericQuestionReference numericQuestionReference) {
        return new NumericQuestionReferenceViewModel(numericQuestionReference);
    }
}

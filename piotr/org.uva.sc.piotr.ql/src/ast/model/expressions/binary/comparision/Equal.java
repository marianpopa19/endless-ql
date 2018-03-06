package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class Equal extends BinaryExpression {
    public Equal(Expression leftSide, Expression rightSide, Integer startLine, Integer endLine) {
        super(leftSide, rightSide, startLine, endLine);
    }
}

package expression;

public class ExpressionArithmeticDivide extends ExpressionArithmetic {

    public ExpressionArithmeticDivide(Expression left, Expression right) {
        super(left, right, "/");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.divide(rightEvaluated);
    }
}
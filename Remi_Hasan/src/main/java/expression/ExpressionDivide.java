package expression;

import model.Form;

public class ExpressionDivide extends Expression<Double> {
    private final Expression left;
    private final Expression right;

    public ExpressionDivide(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form) && (double)right.evaluate(form) != 0){
            return (double)left.evaluate(form) * (double)right.evaluate(form);
        }
        return null;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return left.isEvaluable(form) && right.isEvaluable(form);
    }

    @Override
    public String toString() {
        return left.toString() + " / " + right.toString();
    }
}

package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.BinaryExpression;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

public class GreaterThanExpression extends BinaryExpression {

    public GreaterThanExpression(Expression left, Expression right, int line){
        super(">", left, right, line);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant(this.getLine());
        }

        Double left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());
        return new BooleanConstant(left > right, this.getLine());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }

    @Override
    public Boolean isLogical(){return true;}
}

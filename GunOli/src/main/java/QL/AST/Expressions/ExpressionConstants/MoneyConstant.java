package QL.AST.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;

public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Number value, int line){
        super(value == null ? 0.00 : value.doubleValue(), line);
    }

    public EvaluationType returnType(){
        return EvaluationType.Money;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }

    @Override
    public String toString(){
        return Double.toString(this.getValue());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}

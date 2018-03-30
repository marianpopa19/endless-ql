package QL.AST.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;

import java.time.LocalDate;

public class DateConstant extends Constant<LocalDate> {
    public DateConstant(LocalDate value, int line){
        super(value == null ? LocalDate.now(): value, line);
    }

    public EvaluationType returnType(){
        return EvaluationType.Date;
    }

    @Override
    public String toString(){
        return this.getValue().toString();
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
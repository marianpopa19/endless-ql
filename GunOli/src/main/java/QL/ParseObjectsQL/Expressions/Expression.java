package QL.ParseObjectsQL.Expressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.ASTNode;

public abstract class Expression extends ASTNode {

    Expression(int line){
        super(line);
    }

    public abstract EvaluationType returnType();
    public abstract Constant evaluate(); //should this also be done via visitor?

    public abstract <T> T accept(ExpressionVisitorInterface<T> visitor);

    public Boolean isArithmetic(){
        return false;
    }

    public Boolean isLogical(){
        return false;
    }

    @Override
    public String toString(){
       return this.evaluate().toString();
    }
}
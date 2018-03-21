package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;

public abstract class Expression extends AstNode {

    public abstract void evaluate(SymbolTable symbolTable);

    private ExpressionType expressionType;
    private ExpressionValue expressionValue;

    Expression(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType type) {
        this.expressionType = type;
    }

    public ExpressionValue getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(ExpressionValue expressionValue) {
        this.expressionValue = expressionValue;
    }
}
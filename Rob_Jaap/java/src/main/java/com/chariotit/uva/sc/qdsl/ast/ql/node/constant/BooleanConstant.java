package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class BooleanConstant extends Constant {

    private BooleanExpressionValue value;

    public BooleanConstant(BooleanExpressionValue value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public BooleanExpressionValue getValue() {
        return value;
    }

    public void setValue(BooleanExpressionValue value) {
        this.value = value;
    }


    @Override
    public void evaluate(SymbolTable symbolTable) {
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitBooleanConstant(this);
    }
}
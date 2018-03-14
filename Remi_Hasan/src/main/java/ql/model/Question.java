package ql.model;

import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import org.antlr.v4.runtime.Token;

public class Question extends Node {

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression defaultAnswer;
    public final Expression condition;
    private final boolean isComputed;

    // TODO: too many args?
    public Question(Token start, ReturnType type, String name, String text, Expression defaultAnswer,
                    boolean isComputed, Expression condition) {
        super(start);
        this.type = type;
        this.name = name;
        this.text = text;
        this.defaultAnswer = defaultAnswer;
        this.isComputed = isComputed;
        this.condition = condition;
    }

//    public boolean isVisible(SymbolTable symbolTable) {
//        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
//        return interpreterVisitor.visit(this.condition).getBooleanValue();
//    }

    public boolean isComputed() {
        return isComputed;
    }
}

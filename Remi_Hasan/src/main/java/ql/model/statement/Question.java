package ql.model.statement;

import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.visitor.IQLVisitor;

public class Question extends Statement {

    private final ReturnType type;
    private final String identifier;
    private final String label;
    private final Expression computedAnswer;

    public Question(ReturnType type, String identifier, String label) {
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = null;
    }

    public Question(ReturnType type, String identifier, String label, Expression defaultAnswer) {
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = defaultAnswer;
    }

    public ReturnType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public Expression getComputedAnswer() {
        return computedAnswer;
    }

    public boolean isComputed() {
        return this.computedAnswer != null;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

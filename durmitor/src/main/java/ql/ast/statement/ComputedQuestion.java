package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.type.Type;
import ql.helpers.Observer;
import ql.visitors.interfaces.StatementVisitor;

public class ComputedQuestion extends Question implements Observer {
    
    private Expression computation;

    public ComputedQuestion(String label, Identifier id, Type type, Expression expr) {
        super(label, id, type);
        this.computation = expr;
    }

    public Expression getComputation() {
        return computation;
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id.toString() + ": " + type.toString() + "( " + computation.toString() + " )";
    }
    
    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
    
    public void evaluate() {
        id.setValue(computation.evaluate());
    }

    @Override
    public void update() {
        evaluate();
    }
}

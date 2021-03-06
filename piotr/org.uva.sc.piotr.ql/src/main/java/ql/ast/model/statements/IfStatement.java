package ql.ast.model.statements;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

import java.util.List;

public class IfStatement extends Statement {

    private final Expression condition;
    private final List<Statement> statementList;
    private final List<Statement> elseStatementList;

    public IfStatement(Expression condition, List<Statement> statementList, List<Statement> elseStatementList, MetaInformation metaInformation) {
        super(metaInformation);
        this.condition = condition;
        this.statementList = statementList;
        this.elseStatementList = elseStatementList;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getElseStatementList() {
        return elseStatementList;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

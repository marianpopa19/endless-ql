package ql.ast.model.statements;

import ql.ast.model.declarations.TypeDeclaration;
import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class Question extends Statement {

    private final String label;
    private final String variableName;
    private final TypeDeclaration variableType;
    private Expression assignedExpression;

    public Question(String label, String variableName, TypeDeclaration variableType, MetaInformation metaInformation) {
        super(metaInformation);
        this.label = label;
        this.variableName = variableName;
        this.variableType = variableType;
    }

    public Question(String label, String variableName, TypeDeclaration variableType) {
        super();
        this.label = label;
        this.variableName = variableName;
        this.variableType = variableType;
    }

    public String getLabel() {
        return label;
    }

    public String getVariableName() {
        return variableName;
    }

    public TypeDeclaration getVariableType() {
        return variableType;
    }

    public Expression getAssignedExpression() {
        return assignedExpression;
    }

    public void setAssignedExpression(Expression assignedExpression) {
        this.assignedExpression = assignedExpression;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

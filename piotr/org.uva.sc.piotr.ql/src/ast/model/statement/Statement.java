package ast.model.statement;

import ast.model.ASTNode;

public abstract class Statement extends ASTNode {
    public Statement(Integer startLine, Integer endLine) {
        super(startLine, endLine);
    }
}
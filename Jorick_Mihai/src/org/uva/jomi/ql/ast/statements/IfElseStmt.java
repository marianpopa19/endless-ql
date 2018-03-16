package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expression;

public class IfElseStmt extends IfStmt {

	private final BlockStmt elseBlockStmt;
	
	public IfElseStmt(Expression expression, BlockStmt ifBlockStmt, BlockStmt elseBlockStmt) {
		super(expression, ifBlockStmt);
		this.elseBlockStmt = elseBlockStmt;
	}
	
	public BlockStmt getElseBlockStmt() {
		return elseBlockStmt;
	}

	public int getElseBlockStmtId() {
		return this.elseBlockStmt.getNodeId();
	}

	public <T> T visitElseBlockStmt(Visitor<T> visitor) {
		return this.elseBlockStmt.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
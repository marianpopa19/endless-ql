package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public class IdentifierExpression extends Expression {
	private final QLToken token;
	private boolean undefined = true;

	public IdentifierExpression(QLToken token) {
		this.token = token;
	}

	public IdentifierExpression(QLToken token, QLType type) {
		this.setType(type);
		this.token = token;
	}

	public QLToken getToken() {
		return token;
	}

	public int getLineNumber() {
		return token.getLine();
	}

	public int getColumnNumber() {
		return token.getColumn();
	}

	public String getName() {
		return token.getLexeme();
	}

	public boolean isUndefined() {
		return undefined;
	}

	public void setUndefined(boolean undefined) {
		this.undefined = undefined;
	}

	public void updateAllFields(IdentifierExpression idetifier) {
		this.undefined = idetifier.undefined;
		this.setType(idetifier.getType());
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
package ql.model;

import org.antlr.v4.runtime.Token;
import ql.visitor.IQLVisitor;

public abstract class QLNode {
    private transient Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getLocation() {
        if (token == null) {
            return "";
        }

        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }

    public abstract <T> T accept(IQLVisitor<T> visitor);
}

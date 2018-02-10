package org.uva.sea.ql.parser.elements;

public class Pos extends Expr {
    private Expr value;

    public Pos(Expr value) {
        this.value = value;
    }

    public Expr getValue() {
        return value;
    }

    public void setValue(Expr value) {
        this.value = value;
    }
}

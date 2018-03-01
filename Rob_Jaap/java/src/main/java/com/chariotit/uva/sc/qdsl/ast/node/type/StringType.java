package com.chariotit.uva.sc.qdsl.ast.node.type;

import com.chariotit.uva.sc.qdsl.ast.node.Type;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class StringType extends Type {

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringType(this);
    }
}

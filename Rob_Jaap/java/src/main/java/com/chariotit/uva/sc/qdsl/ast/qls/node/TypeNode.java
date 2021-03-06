package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

public abstract class TypeNode extends AstNode {


    public TypeNode(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public abstract ExpressionType getType();
}

package com.chariotit.uva.sc.qdsl.ast.ql;


import com.chariotit.uva.sc.qdsl.ast.ql.node.QLAstRoot;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.SymbolTableBuilderVisitor;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.TypeCheckVisitor;

import java.util.List;

/**
 * Should check:
 * <p>
 * cyclic dependencies between questions -> Need Symboltable
 * duplicate labels (warning) -> Can do
 * <p>
 * Check cyclic dependencies
 */
public class TypeChecker {

    public List<TypeCheckError> typeCheckAst(QLAstRoot astRoot) {

        // First run. Build symbol table
        SymbolTableBuilderVisitor symbolTableVisitor = new SymbolTableBuilderVisitor();
        astRoot.acceptVisitor(symbolTableVisitor);

        if (symbolTableVisitor.getErrors().size() > 0) {
            return symbolTableVisitor.getErrors();

        }

        // Second run. TypeNode checker
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(astRoot.getQuestionSymbolTable());
        astRoot.acceptVisitor(typeCheckVisitor);

        return typeCheckVisitor.getErrors();
    }
}
package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.Form;
import ql.model.Question;
import ql.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorStatement visitorStatement = new VisitorStatement();

        // Build all statements in the form (either question or if-else-block)
        List<Statement> statements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            Statement statement = visitorStatement.visit(statementContext);
            statements.add(statement);
        }

        return new Form(ctx.getStart(), ctx.IDENTIFIER().getText(), statements);
    }

}

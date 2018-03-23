package ql.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;

import java.util.List;

public class Form extends QLNode {

    public final String identifier;
    private final List<Statement> statements;

    public Form(Token token, String identifier, List<Statement> statements) {
        super(token);
        this.identifier = identifier;
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

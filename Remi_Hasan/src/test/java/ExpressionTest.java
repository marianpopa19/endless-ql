import antlr.QLLexer;
import antlr.QLParser;
import expression.*;
import model.BlockElement;
import model.Form;
import model.Question;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import visitor.VisitorForm;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    public Form parseForm(String fileName) throws IOException {
        InputStream stream = getClass().getResourceAsStream(fileName);
        System.out.println("stream: " + stream);
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        // Walk it and attach our listener
        VisitorForm visitor = new VisitorForm();
        return visitor.visit(parser.root());
    }

    @Test
    public void ExpressionVariableTest() throws IOException {
        // Actual
        String fileName = "test/variables.ql";
        Form actualForm = parseForm(fileName);

        // Expected
        ArrayList<BlockElement> elements = new ArrayList<>() {{
            add(new Question("q1", "Can you give me a boolean?", new ExpressionVariableBoolean(true)));
            add(new Question("q2", "Can you give me a string?", new ExpressionVariableString("hello")));
            add(new Question("q3", "Can you give me a integer?", new ExpressionVariableInteger(1)));
            add(new Question("q4", "Can you give me a date?", new ExpressionVariableDate("1-1-2018")));
            add(new Question("q5", "Can you give me a decimal?", new ExpressionVariableDecimal(1.2345)));
            add(new Question("q6", "Can you give me a money?", new ExpressionVariableMoney(BigDecimal.valueOf(1.99))));
        }};
        Form expectedForm = new Form("testForm", elements);
        assertEquals(expectedForm.toString().trim(), actualForm.toString().trim());
    }

    // TODO: test conditions, conditions within conditions, identifiers, etc


}

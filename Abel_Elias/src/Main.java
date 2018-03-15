import gui.FormBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parsing.visitors.BaseVisitor;
import parsing.gen.QLLexer;
import parsing.gen.QLParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Main {
    /**
     * parse and build the form file
     * @param inputStream - input stream of the given form file
     */
    public void parseAndBuild(InputStream inputStream){
        try{
            //Call the lexer and get the tokens
            QLLexer lexer = new QLLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //Parse the tokens/tree
            QLParser parser = new QLParser(tokens);
            QLParser.FormContext tree = parser.form();

            //Call the visitor and build the tree
            BaseVisitor builder = new BaseVisitor(tree);
            HashMap questionHashMap = builder.getQuestions();

//          Test output
//            Iterator it = memory.entrySet().iterator();
//            while (it.hasNext()) {
//                  Map.Entry pair = (Map.Entry)it.next();
//                  System.out.println(pair.getKey() + " = " + pair.getValue());
//                  it.remove();
//            }
            //System.out.println("done");

            //Construct the form
            //ParseTree parseTree = parser.form();
            //Form form = (Form) parseTree.accept(builder);

            //Call parse tree inspector: Show the tree
            //Trees.inspect(tree, parser);

            //Do typechecking
            //TypeChecker typeChecker = new TypeChecker();
            //typeChecker.initTypeChecking(form);

            //Pass the relevant questions to the UI builder
            FormBuilder formBuilder = new FormBuilder();
            formBuilder.initComponents(questionHashMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main method
     * @param args given arguments
     */
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                new Main().parseAndBuild(System.in);
            } else if (args.length == 1) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                new Main().parseAndBuild(fileInputStream);
            } else {
                System.out.println("Invalid arguments were given");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}

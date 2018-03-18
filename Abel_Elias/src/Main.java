import classes.Form;
import classes.Question;
import classes.values.BooleanValue;
import classes.values.DateValue;
import classes.values.IntegerValue;
import classes.values.StringValue;
import gui.FormBuilder;
import parsing.TreeBuilder;
import parsing.checkers.Checks;
import parsing.gen.QLParser;
import parsing.visitors.FormVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    /**
     * parse and build the form file
     */

    private HashMap<String, Question> getQuestionTemp() {
        LinkedHashMap<String, Question> questionHashMap = new LinkedHashMap<String, Question>();
        questionHashMap.put("1", new Question("Is this a question?", new BooleanValue(), false, true));
        questionHashMap.put("2", new Question("Is this a question?", new StringValue(), false, true));
        questionHashMap.put("3", new Question("Is this a question?", new IntegerValue(), false, true));
        questionHashMap.put("4", new Question("Is this a question?", new DateValue(), false, true));
        return questionHashMap;
    }

    private void printQuestionMap(HashMap<String, Question> memory){
        //Test output
        for (Map.Entry e : memory.entrySet()) {
            Question q = (Question) e.getValue();
            String id = (String) e.getKey();
            System.out.println(id + ":\t" + q) ;
        }
    }

    private void parseAndBuild(InputStream inputStream){
        try{
            QLParser.FormContext form = new TreeBuilder().build(inputStream);
            FormVisitor coreVisitor = new FormVisitor(form);
            Checks.checkForm(form);
            LinkedHashMap<String, Question> memory = coreVisitor.questionMap;
            //Pass the relevant questions to the UI builder
            FormBuilder formBuilder = new FormBuilder(coreVisitor, memory);
            formBuilder.initComponents();
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

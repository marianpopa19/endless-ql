package ql.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ql.ASTBuilder;
import ql.ast.expressions.Identifier;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;
import ql.gui.controls.ControlVisitor;
import ql.gui.controls.QLControl;
import ql.typechecker.SemanticAnalyzer;
import ql.typechecker.messages.Messages;
import ql.typechecker.messages.error.Error;
import ql.typechecker.messages.warning.Warning;
import ql.values.Value;

import java.io.IOException;
import java.io.InputStream;

public class Form extends Application implements OnValueChange{
    private final ControlVisitor controlVisitor = new ControlVisitor(this);
    private final Evaluator evaluator = new Evaluator();
    private static ql.ast.Form form;
    private GridPane formPane;
    private int row = 0;

    public static void main(String[] args) throws IOException {
        createForm();
        launch();
    }

    @Override
    public void start(Stage stage) {
        checkForMessages();
        formPane(stage);
        initializeForm();
        stage.show();
    }

    public static void createForm() throws IOException {
        ASTBuilder astBuilder = new ASTBuilder();
        InputStream input = Form.class.getResourceAsStream("/form.ql");
        form = astBuilder.build(input);
    }

    private void checkForMessages() {
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        Messages messages = semanticAnalyzer.checkForm(form);

        if (messages.hasErrors()) {
            messages.errors().forEach(this::printError);
            System.exit(1);
        }

        messages.warnings().forEach(this::printWarning);
    }

    private void printError(Error error) {
        System.out.println(error.message());
    }

    private void printWarning(Warning warning) {
        System.out.println(warning.message());
    }

    public void initializeForm() {
        this.reset();
        evaluator.clear();
        evaluator.visit(form);

        for (Question question: evaluator.questions()) {
            this.add(controlVisitor.createQLControl(question), question);
        }
    }

    private GridPane formPane(Stage stage) {
        stage.setTitle(form.getIdentifier().toString());
        stage.setResizable(true);
        formPane = new GridPane();
        formPane.setVgap(5);
        formPane.setHgap(5);
        formPane.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(formPane));
        return formPane;
    }

    private void reset() {
        row = 0;
        formPane.getChildren().clear();
    }

    private void add(QLControl qlControl, Question question) {
        qlControl.setValue(evaluator.valueTable().find(question.getIdentifier()));
        formPane.add(qlControl.gridPane(), 0, row++);
    }

    @Override
    public void changed(Identifier identifier, Value value) {
        evaluator.storeValues(identifier, value);
        initializeForm();
    }
}

package qls.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.DefaultStyle;
import qls.model.Question;
import qls.model.StyleSheet;
import qls.model.widget.WidgetType;

import java.util.HashMap;
import java.util.Map;

public class TypeChecker extends QLSVisitor<Void> {

    private final Form form;
    private final StyleSheet styleSheet;
    private Map<String, ReturnType> formQuestionTypes;

    public TypeChecker(Form form, StyleSheet styleSheet) {
        this.form = form;
        this.styleSheet = styleSheet;
    }

    public void typeCheck() {
        // Get question types
        this.formQuestionTypes = getFormQuestionTypes(this.form);

        // Compare to QLS question widget types
        this.styleSheet.accept(this);
    }

    @Override
    public Void visit(Question question) {
        if(question.getWidget() == null) {
            return super.visit(question);
        }

        // Check if QLS widget is compatible with question type
        WidgetType widgetType = question.getWidget().type;
        ReturnType questionType = formQuestionTypes.get(question.name);
        if(!widgetType.isCompatible(questionType)) {
            throw new IllegalArgumentException("Incompatible widget type " + widgetType
                    + " for question of type " + questionType + " " + question.getWidget().getLocation());
        }

        return super.visit(question);
    }

    @Override
    public Void visit(DefaultStyle defaultStyle) {
        if(defaultStyle.getWidget() == null) {
            return super.visit(defaultStyle);
        }

        // Check if widget type defined in the default style is compatible with the
        // question type that is being styled
        WidgetType widgetType = defaultStyle.getWidget().type;
        if(!widgetType.isCompatible(defaultStyle.type)) {
            throw new IllegalArgumentException("Incompatible widget type " + widgetType
                    + " for question of type " + defaultStyle.type + " " + defaultStyle.getWidget().getLocation());
        }

        return super.visit(defaultStyle);
    }

    private Map<String, ReturnType> getFormQuestionTypes(Form form) {
        Map<String, ReturnType> questionTypes = new HashMap<>();

        // Visit questions and add their return type to the map
        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(ql.model.Question question) {
                questionTypes.put(question.identifier, question.type);
                return super.visit(question);
            }
        });

        return questionTypes;
    }
}

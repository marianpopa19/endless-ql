package ql.gui.view;

import ql.ast.model.declarations.TypeDeclaration;
import ql.gui.model.QuestionModel;
import ql.gui.view.widgets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuestionView extends JPanel {
    private final Widget widget;
    private final QuestionModel questionModel;

    public QuestionView(QuestionModel questionModel, Widget widget) {
        super(new GridBagLayout());

        if (widget != null){
            this.widget = widget;
        } else {
            this.widget = createDefaultWidget(questionModel);
        }

        questionModel.setQuestionView(this);
        this.questionModel = questionModel;

        JLabel labelComponent = new JLabel(questionModel.getQuestionLabel());

        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        this.add(labelComponent, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.add(this.widget.getComponent(), gridBagConstraints);

        this.setVisible(questionModel.getVisibility());
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void refreshVisibility() {
        if (this.questionModel.getVisibility() != null) {
            this.setVisible(this.questionModel.getVisibility());
        }
    }

    public void refreshValue() {
        if (this.questionModel.getQLDataTypeValue() != null) {
            this.widget.updateValue();
        }
    }

    private static Widget createDefaultWidget(QuestionModel questionModel) {

        TypeDeclaration originalDataTypeDeclaration = questionModel.getOriginalDataTypeDeclaration();

        switch (originalDataTypeDeclaration.toDataType()) {
            case DECIMAL:
                return new DecimalFieldWidget(questionModel);
            case MONEY:
                return new MoneyFieldWidget(questionModel);
            case DATE:
                return new DatePickerWidget(questionModel);
            case INTEGER:
                return new IntegerSpinnerWidget(questionModel);
            case BOOLEAN:
                //return new BooleanCheckboxWidget(this.questionModel);
                return new BooleanRadioWidget(questionModel);
        }
        // string and any other
        return new TextFieldWidget(questionModel);
    }

}

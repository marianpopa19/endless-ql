package gui.questions;

import classes.Question;
import gui.FormBuilder;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.util.EventListener;

public class QuestionPanelTextString extends QuestionPanelText{
    public QuestionPanelTextString(String key, Question question) {
        super(key, question);
    }

    @Override
    public void setListener(EventListener listener) {
        FormBuilder.StringDocumentListener stringListener = (FormBuilder.StringDocumentListener) listener;
        JTextField textField = (JTextField) this.getComponent();
        textField.getDocument().addDocumentListener(stringListener);
    }
}

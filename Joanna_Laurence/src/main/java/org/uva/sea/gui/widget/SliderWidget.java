package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class SliderWidget extends Widget {

    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public SliderWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {

        Slider slider = new Slider();

        slider = this.createSlider(slider, this.questionModel.getStyleQLS());
        slider.setMin(0);
        slider.setValueChanging(true);
        slider.setMinorTickCount(1);
        slider.setMax(100);

        if (this.questionModel.getValue() != null) {
            System.out.println("Computed boolean value " + this.questionModel.displayValue());
            slider.valueProperty().setValue(new DecimalValue(this.questionModel.displayValue()).getDecimalValue());
        }

        //TODO: remove listeners repetitions
        Slider finalSlider = slider;
        slider.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
            this.controller.setLastFocused(this.questionModel.getVariableName());
            IQuestionModelVisitor<Value> textToValueVisitor = new TextToValueVisitor(String.valueOf(finalSlider.getValue()));
            Value value = this.questionModel.accept(textToValueVisitor);
            this.controller.updateGuiModel(this.questionModel.getVariableName(), value);
        });

        return slider;
    }

    private Slider createSlider(Slider slider, Style style) {
        //TODO: set color and font
        //TODO: consider what to do with pages and sections
        if (style != null) {
            if (style.getWidth() != null) {
                slider.setMinWidth(style.getWidth());
            } else {
                slider.setMinWidth(Widget.TEXT_WIDTH);
            }
        } else {
            System.out.println("Style is null");
        }

        return slider;
    }
}

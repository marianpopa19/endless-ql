package gui.components.widgets.slider;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class Slider extends HBox implements GUIWidget {
    javafx.scene.control.Slider slider;
    Label valueLabel;

    public Slider(double min, double max) {
        this.addSlider(min, max);
        this.addValueLabel();
    }

    private void addSlider(double min, double max) {
        javafx.scene.control.Slider slider = new javafx.scene.control.Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        this.slider = slider;
        this.getChildren().add(slider);
    }

    private void addValueLabel() {
        // Show label next to slider with the current value
        Label valueLabel = new Label("0.0");
        valueLabel.setPadding(new Insets(0, 0, 0, 5));
        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> valueLabel.setText(newVal.toString()));
        this.valueLabel = valueLabel;
        this.getChildren().add(valueLabel);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        slider.valueProperty().addListener(invalidationListener);
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }
}
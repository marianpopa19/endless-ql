package gui.model;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import qls.model.StyleSheet;

import java.util.List;

public class GUISection extends VBox implements GUIInterface {

    private final String identifier;
    private final List<GUIInterface> children;

    public GUISection(String identifier, List<GUIInterface> children) {
        this.identifier = identifier;
        this.children = children;

        Label label = new Label("Section " + identifier);
        this.getChildren().add(label);

        for (GUIInterface child : children) {
            this.getChildren().add(child.render());
        }

        this.setPadding(new Insets(0, 0, 0, 20));

        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void update(SymbolTable symbolTable) {
        for (GUIInterface child : children) {
            child.update(symbolTable);
        }
    }

    @Override
    public void update(StyleSheet styleSheet) {
        for (GUIInterface child : children) {
            child.update(styleSheet);
        }
    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        for (GUIInterface child : children) {
            child.setChangeListener(invalidationListener);
        }
    }
}
package qls.model.widget;

import gui.WidgetVisitor;
import gui.widgets.DateWidget;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetDefault extends Widget {
    public WidgetDefault(Token start, WidgetType type) {
        super(start, type);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

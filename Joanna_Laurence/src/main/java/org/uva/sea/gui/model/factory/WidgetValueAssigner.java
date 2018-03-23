package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.IQLSStaticAnalysis;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.TypeCheck;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

public class WidgetValueAssigner extends BaseValueVisitor<Void> {

    private final BaseWidget widget;

    public WidgetValueAssigner(BaseWidget widget) {
        this.widget = widget;
    }

    public BaseWidget updateWidget(Value value) {
        value.accept(this);
        return this.widget;
    }

    @Override
    public Void visit(BooleanValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(DateValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(DecimalValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(IntValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(MoneyValue value) {
        this.widget.updateValue(value);
        return null;
    }

    @Override
    public Void visit(StringValue value) {
        this.widget.updateValue(value);
        return null;
    }
}

package gui.widgets.textbox;

import gui.widgets.WidgetUtils;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableInteger;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class TextboxIntegerWidget extends TextboxWidget {

    public TextboxIntegerWidget(String identifier, boolean computed) {
        super(identifier, computed);
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpressionValue() {
        if (this.getText().isEmpty()) {
            return new ExpressionVariableUndefined(null, ReturnType.INTEGER);
        }

        return new ExpressionVariableInteger(null, Integer.parseInt(this.getText()));
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getIntValue().toString());
    }
}
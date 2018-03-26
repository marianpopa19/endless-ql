package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.parser.NodeType;

public enum WidgetType {
    UNKNOWN,

    DEFAULT,
    CHECKBOX,
    CHOICEBOX,
    RADIO,
    SLIDER,
    SPINBOX,
    TEXTFIELD;

    private boolean isBooleanCompatible(NodeType nodeType) {
        return ((this == CHECKBOX) ||
                (this == CHOICEBOX) ||
                (this == RADIO)) &&
                (nodeType == NodeType.BOOLEAN);
    }

    private boolean isNumberCompatible(NodeType nodeType) {
        return ((this == SLIDER) ||
                (this == SPINBOX)) && (
                (nodeType == NodeType.INTEGER) ||
                        (nodeType == NodeType.DECIMAL)
        );
    }

    private boolean isStringCompatible(NodeType nodeType) {
        return (this == TEXTFIELD) && (
                (nodeType == NodeType.STRING) ||
                        (nodeType == NodeType.INTEGER) ||
                        (nodeType == NodeType.DECIMAL)
        );
    }

    private boolean isNotUnknown() {
        return this != WidgetType.UNKNOWN;
    }

    public boolean isCompatible(NodeType nodeType) {
        return this.isNotUnknown() ||
                this.isBooleanCompatible(nodeType) ||
                this.isNumberCompatible(nodeType) ||
                this.isStringCompatible(nodeType);
    }
}

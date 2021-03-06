package qlviz.model.style;

public class StringParameter implements Parameter {

    private final String value;

    public StringParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(ParameterVisitor voidParameterVisitor) {
        voidParameterVisitor.visit(this);
    }
}

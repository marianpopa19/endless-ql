package ql.logic.type;

abstract public class QLSummable<T> extends QLDataTypeWrapper<T> {
    public QLSummable(T value) {
        super(value);
    }

    abstract public QLSummable add(QLSummable rhs);
}
package nl.uva.js.qlparser.models.ql.enums;

import java.util.List;
import java.util.function.BiFunction;

public interface Operator {
    List<DataType> requiredType();
    BiFunction getApply();
}

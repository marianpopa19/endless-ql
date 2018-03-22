package ql.evaluation.value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class UndefinedValue extends Value<Boolean> {

    public UndefinedValue() {
        super(false);
    }

    @Override
    public boolean isUndefined() {
        return true;
    }

    @Override
    public Boolean getBooleanValue() {
        return false;
    }

    @Override
    public Integer getIntValue() {
        return 0;
    }

    @Override
    public Double getDecimalValue() {
        return 0.0;
    }

    @Override
    public BigDecimal getMoneyValue() {
        return new BigDecimal(0);
    }

    @Override
    public String getStringValue() {
        return "";
    }

    @Override
    public LocalDate getDateValue() {
        return LocalDate.of(1970, 1, 1);
    }

    @Override
    public Value divide(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value multiply(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value subtract(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value sum(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value eq(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value ge(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value gt(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value le(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value lt(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value and(Value right) {
        return new UndefinedValue();
    }

    @Override
    public Value or(Value right) {
        return new BooleanValue(right.getBooleanValue());
    }

    @Override
    public Value not() {
        return new BooleanValue(true);
    }

    @Override
    public Value neg() {
        return new UndefinedValue();
    }
}
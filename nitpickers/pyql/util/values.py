import math
from decimal import Decimal, InvalidOperation

from util import errors
from util import types
from util.multimethods import multimethod


class Value:

    def __init__(self, type, value):
        if not self.is_valid_input(value):
            raise errors.Type(str(type) + " does not accept value: " + str(value))

        self._value = self._parse(value)
        self._type = type

    def __add__(self, other):
        return Addition().evaluate(self, other)

    def __sub__(self, other):
        return Subtraction().evaluate(self, other)

    def __mul__(self, other):
        return Multiplication().evaluate(self, other)

    def __truediv__(self, other):
        return Division().evaluate(self, other)

    def __eq__(self, other):
        return Equality().evaluate(self, other)

    def __ne__(self, other):
        return NonEquality().evaluate(self, other)

    def __lt__(self, other):
        return LowerThan().evaluate(self, other)

    def __le__(self, other):
        return LowerEqual().evaluate(self, other)

    def __gt__(self, other):
        return GreaterThan().evaluate(self, other)

    def __ge__(self, other):
        return GreaterEqual().evaluate(self, other)

    def __invert__(self):
        return Not().evaluate()

    def __bool__(self):
        raise errors.Syntax("Value " + str(self.value) + " can not be evaluated to a BooleanValue")

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    @staticmethod
    def is_valid_input(self):
        return True

    @staticmethod
    def _parse(value):
        return value

    def __repr__(self):
        return str(self.value)

    def __str__(self):
        return self.__repr__()


class StringValue(Value):

    def __init__(self, value):
        super().__init__(types.String(), value)

    @staticmethod
    def _parse(value):
        return str(value)


class IntegerValue(Value):

    def __init__(self, value):
        super().__init__(types.Integer(), value)

    @staticmethod
    def _parse(value):
        return int(value)

    @staticmethod
    def is_valid_input(value):
        try:
            is_digit = str(value).isdigit()
            return is_digit
        except (ValueError, InvalidOperation):
            return False


class DecimalValue(Value):

    def __init__(self, value):
        super().__init__(types.Decimal(), value)

    @staticmethod
    def _parse(value):
        return Decimal(value)

    @staticmethod
    def is_valid_input(value):
        try:
            is_decimal = Decimal(value) == Decimal(str(value))
            return is_decimal
        except (ValueError, InvalidOperation):
            return False


class MoneyValue(Value):

    def __init__(self, value):
        super().__init__(types.Money(), value)

    @staticmethod
    def _parse(value):
        return round(Decimal(value), 2)

    @staticmethod
    def is_valid_input(value):
        is_decimal = DecimalValue.is_valid_input(value)

        try:
            decimal_places = 0 - Decimal(value).as_tuple().exponent
        except InvalidOperation:
            return False

        if is_decimal and decimal_places <= 2:
            return True
        return False

    def __repr__(self):
        return "$" + str(self.value)


class BooleanValue(Value):

    def __init__(self, value):
        super().__init__(types.Boolean(), value)

    @staticmethod
    def _parse(value):
        return value is True

    @staticmethod
    def is_valid_input(value):
        try:
            is_boolean = value is True or value is False
            return is_boolean
        except (ValueError, InvalidOperation):
            return False

    def __bool__(self):
        return self.value


class Evaluation:

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        raise errors.Type("Operation not possible between instances of " + str(left.type) + " & " + str(right.type))

    @multimethod(Value)
    def evaluate(self, expression):
        raise errors.Type("Operation not possible on " + str(expression.type))


class Addition(Evaluation):

    @multimethod([(StringValue, StringValue)])
    def evaluate(self, left, right):
        return StringValue(left.value + right.value)

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value + right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value + right.value)

    @multimethod([(MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return MoneyValue(left.value + right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class Subtraction(Evaluation):

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value - right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value - right.value)

    @multimethod([(MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return MoneyValue(left.value - right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class Multiplication(Evaluation):

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(left.value * right.value)

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value * right.value)

    @multimethod(
        [(IntegerValue, MoneyValue), (DecimalValue, MoneyValue), (MoneyValue, IntegerValue), (MoneyValue, DecimalValue),
         (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return MoneyValue(left.value * right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class Division(Evaluation):

    @multimethod([(IntegerValue, IntegerValue)])
    def evaluate(self, left, right):
        return IntegerValue(math.floor(left.value / right.value))

    @multimethod([(IntegerValue, DecimalValue), (DecimalValue, IntegerValue), (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return DecimalValue(left.value / right.value)

    @multimethod(
        [(MoneyValue, IntegerValue), (MoneyValue, DecimalValue)])
    def evaluate(self, left, right):
        return MoneyValue(left.value / right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class LowerThan(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value < right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class LowerEqual(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value <= right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class GreaterThan(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value > right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class GreaterEqual(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value >= right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        super().evaluate(left, right)


class Equality(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (DecimalValue, DecimalValue), (MoneyValue, MoneyValue),
                  (StringValue, StringValue), (BooleanValue, BooleanValue), (IntegerValue, DecimalValue),
                  (DecimalValue, IntegerValue)])
    def evaluate(self, left, right):
        return BooleanValue(left.value == right.value)

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return BooleanValue(False)


class NonEquality(Evaluation):

    @multimethod(Value, Value)
    def evaluate(self, left, right):
        return not Equality().evaluate(left, right)


class Not(Evaluation):

    @multimethod(BooleanValue)
    def evaluate(self, expression):
        return BooleanValue(not expression.value)

    @multimethod(Value)
    def evaluate(self, expression):
        super().evaluate(expression)


class Invert(Evaluation):

    @multimethod([(IntegerValue, IntegerValue), (IntegerValue, DecimalValue), (DecimalValue, IntegerValue),
                  (DecimalValue, DecimalValue),
                  (MoneyValue, MoneyValue)])
    def evaluate(self, expression):
        return Multiplication().evaluate(IntegerValue(-1), expression.value)

    @multimethod(Value)
    def evaluate(self, expression):
        super().evaluate(expression)

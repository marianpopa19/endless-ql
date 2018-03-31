from ql.ast.nodes.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.undefined import QLUndefined


class DivisionOperatorNode(BinaryOperatorNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(DivisionOperatorNode, self).__init__(metadata, expression_type, left_expression, right_expression, value)
        self.__valid_types = {(QLDecimal, QLDecimal): QLDecimal,
                              (QLDecimal, QLInteger): QLDecimal,
                              (QLInteger, QLInteger): QLDecimal,
                              (QLInteger, QLDecimal): QLDecimal,
                              (QLMoney, QLDecimal): QLMoney,
                              (QLMoney, QLInteger): QLMoney,
                              }

    def get_result_type(self, type1, type2):
        if self.__valid_types.get((type1, type2)):
            return self.__valid_types.get((type1, type2))
        return QLUndefined

    def evaluate(self):
        try:
            self.value = self.left_expression.value / self.right_expression.value
        except ZeroDivisionError:
            self.value = QLUndefined()

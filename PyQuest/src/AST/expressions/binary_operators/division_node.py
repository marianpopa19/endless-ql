from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode


class DivisionOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(DivisionOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)

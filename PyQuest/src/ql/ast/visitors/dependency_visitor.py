from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.expressions.literals.literal_node import LiteralNode
from multimethods import multimethod


class DependencyVisitor(object):

    def __init__(self):
        self.__all_dependencies = []
        self.__current_dependencies = []

    @property
    def identifier_dependencies(self):
        return self.__all_dependencies

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)
        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        if node.computed:
            self.__current_dependencies = []
            node.answer.accept(self)
            self.__all_dependencies.append((node.identifier,
                                            self.__current_dependencies,
                                            node.position))

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @multimethod(VariableNode)
    def visit(self, node):
        self.__current_dependencies.append(node.identifier)

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
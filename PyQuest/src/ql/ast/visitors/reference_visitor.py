from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.expressions.literals.literal_node import LiteralNode
from multimethods import multimethod


class ReferenceVisitor(object):

    def __init__(self):
        self.__current_block = []
        self.__current_scope = {}

    @property
    def identifier_scopes(self):
        return self.__current_scope

    @multimethod(FormNode)
    def visit(self, node):
        self.__current_scope = {'content': [],
                                'children': []}

        self.__current_block = []

        for child in node.block:
            child.accept(self)

        self.__current_scope['content'] = self.__current_block

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        self.__current_scope['content'] += self.__current_block
        previous_scope = self.__current_scope
        previous_block = self.__current_block

        self.__current_block = []
        self.__current_scope = {'content': [],
                                'children': []}

        for child in node.block:
            child.accept(self)

        self.__current_scope['content'] = self.__current_block

        previous_scope['children'].append(self.__current_scope)

        self.__current_block = previous_block
        self.__current_scope = previous_scope

    @multimethod(QuestionNode)
    def visit(self, node):
        self.__current_block.append(dict({'name': node.identifier,
                                          'type': node.answer_type,
                                          'position': node.position}))

        if node.computed:
            node.answer.accept(self)

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @multimethod(VariableNode)
    def visit(self, node):
        name = node.identifier
        self.__current_block.append({'name': name,
                                     'type': [],
                                     'position': node.position})

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
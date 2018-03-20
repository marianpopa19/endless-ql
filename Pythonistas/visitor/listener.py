import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes


def listen(tree):
    # print(tree.toStringTree())
    ql = QLListener()
    walker = ParseTreeWalker()
    walker.walk(ql, tree)
    return [ql.errorMessage,ql.questionIDs,ql.questions]


class QLListener(ParseTreeListener):
    def __init__(self):

        self.inIf = False
        self.errorMessage = None
        self.questionIDs = [] # Ordered list of question IDs.
        self.questions = {}  # Ordered list of question objects

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx:QLParser.FormContext):
        pass

    # Exit a parse tree produced by QLParser#form.
    def exitForm(self, ctx:QLParser.FormContext):
        pass


    # Enter a parse tree produced by QLParser#block.
    def enterBlock(self, ctx:QLParser.BlockContext):
        pass

    # Exit a parse tree produced by QLParser#block.
    def exitBlock(self, ctx:QLParser.BlockContext):
        pass


    # Enter a parse tree produced by QLParser#stmt.
    def enterStmt(self, ctx:QLParser.StmtContext):
        pass

    # Exit a parse tree produced by QLParser#stmt.
    def exitStmt(self, ctx:QLParser.StmtContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        # Filters necessary information from the node
        children = ctx.getChildren()
        question = children.__next__().getText()
        questionID = children.__next__().getText()

        if questionID in self.questionIDs:
            self.errorMessage = "Error: duplicate question IDs: {}".format(questionID)
            return
        children.__next__()
        datatype = children.__next__().getText()
        if datatype == 'boolean':
            questionObject = question_classes.BooleanQuestion(questionID, question)
            choices = ['Yes','No']  # todo: make flexible

            truebutton = QtWidgets.QRadioButton(choices[0])
            truebutton.pressed.connect(questionObject.set_answer_true)
            questionObject.set_truebutton(truebutton)

            falsebutton = QtWidgets.QRadioButton(choices[1])
            falsebutton.pressed.connect(questionObject.set_answer_false)
            questionObject.set_falsebutton(falsebutton)

        elif datatype == 'money':
            questionObject = question_classes.MoneyQuestion(questionID, question)

        else:
            self.errorMessage = "Error: unknown datatype: {}".format(datatype)
            return

        self.questionIDs.append(questionID)
        self.questions[questionID] = questionObject

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#declaration.
    def enterDeclaration(self, ctx:QLParser.DeclarationContext):
        pass

    # Exit a parse tree produced by QLParser#declaration.
    def exitDeclaration(self, ctx:QLParser.DeclarationContext):
        pass


    # Enter a parse tree produced by QLParser#expression.
    def enterExpression(self, ctx:QLParser.ExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#expression.
    def exitExpression(self, ctx:QLParser.ExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#if_.
    def enterIf_(self, ctx:QLParser.If_Context):
        pass

    # Exit a parse tree produced by QLParser#if_.
    def exitIf_(self, ctx:QLParser.If_Context):
        # todo: cleanup
        children = ctx.getChildren()

        # Picks out the ID of the question that is the argument of the if
        children.__next__()
        children.__next__()
        conditionalID = children.__next__().getText()

        # If the ID of the question that is the argument of the if does not exist, throws an error
        if conditionalID not in self.questionIDs:
            self.errorMessage = "Error: if argument is undefined: {}".format(conditionalID)
            return
        elif self.questions[conditionalID].get_datatype() != 'boolean':
            self.errorMessage = "Error: if argument is not boolean: {}".format(conditionalID)
            return

        children.__next__()
        ifquestion = children.__next__()  # picks out the question within the if

        ifquestionchildren = ifquestion.getChildren()
        ifquestionchildren.__next__()
        for ifchild in ifquestionchildren:  # If there's multiple questions within the if, all are picked out
            if ifchild.getText() == '}':
                break
            grandchildren = ifchild.getChildren()
            grandchild = grandchildren.__next__()

            ggrandchildren = grandchild.getChildren()
            ggrandchildren.__next__()
            ifquestionID = ggrandchildren.__next__().getText()  # Specifically: picks out the IDs of the questions

            conditionalQuestion = self.questions[conditionalID]
            ifQuestion = self.questions[ifquestionID]
            conditionalQuestion.add_if_question(ifQuestion)


    # Enter a parse tree produced by QLParser#type.
    def enterType(self, ctx:QLParser.TypeContext):
        pass

    # Exit a parse tree produced by QLParser#type.
    def exitType(self, ctx:QLParser.TypeContext):
        pass


    # Enter a parse tree produced by QLParser#value.
    def enterValue(self, ctx:QLParser.ValueContext):
        pass

    # Exit a parse tree produced by QLParser#value.
    def exitValue(self, ctx:QLParser.ValueContext):
        pass


    # Enter a parse tree produced by QLParser#compute.
    def enterCompute(self, ctx:QLParser.ComputeContext):
        pass

    # Exit a parse tree produced by QLParser#compute.
    def exitCompute(self, ctx:QLParser.ComputeContext):
        pass


    # Enter a parse tree produced by QLParser#arithmetic.
    def enterArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        pass

    # Exit a parse tree produced by QLParser#arithmetic.
    def exitArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        pass


    # Enter a parse tree produced by QLParser#boolean.
    def enterBoolean_(self, ctx:QLParser.Boolean_Context):
        pass

    # Exit a parse tree produced by QLParser#boolean.
    def exitBoolean_(self, ctx:QLParser.Boolean_Context):
        pass
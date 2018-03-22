from QLSast import *
import sys

class QLSTypeChecker(object):
	
    def __init__(self, ql_ast, qls_ast):
    	self.ql_ast = ql_ast
    	self.qls_ast = qls_ast
        self.ql_variables = {}
        self.qls_variables = {}


    def startQLSTypeCheck(self):
        self.retrieveVariables(self.ql_ast.statements, self.ql_variables, "ql")
        self.retrieveVariables(self.qls_ast.pages, self.qls_variables, "qls")

        self.checkReferencesToQL(self.ql_variables, self.qls_variables)

    # Checks if every question in QL is referenced in QLS.
    def checkReferencesToQL(self, ql_variables, qls_variables):
        for key, value in ql_variables.iteritems():
            if key not in qls_variables:
                exitProgram("Variable {} is not referenced in QLS, but should be.".format(key))


    # Checks whether the types of the questions are compatible with the assigned widgets.
    def checkWidgetQuestionCompatibility(self):
    	pass


    # Check if every question is only placed once in the qls ast.
    def checkQuestionUniqueness(self, variable_name):
    	if variable_name in self.qls_variables:
            exitProgram("Question {} is getting placed twice by QLS.".format(variable_name))


    def retrieveVariables(self, elements, variables, flag):
        for element in elements:
            node_type = element.getNodeType()
            if node_type == "question" or node_type == "assignment":
                variable_name = element.getVariableName()

                if flag == "ql":
                    variable_type = element.getVariableType()
                    variables[variable_name] = variable_type
                elif flag == "qls":
                    self.checkQuestionUniqueness(variable_name)
                    variable_widget = element.getWidget()
                    variables[variable_name] = variable_widget

            elif node_type == "if" or node_type == "elif" or node_type == "else":
                self.retrieveVariables(element.statements, variables, flag)

            elif node_type == "page":
                self.retrieveVariables(element.sections, variables, flag)

            elif node_type == "section":
                self.retrieveVariables(element.sections, variables, flag)
                self.retrieveVariables(element.questions, variables, flag)

        return

def exitProgram(message):
    print message
    sys.exit()
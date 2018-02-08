# Jordy Bottelier
# Dennis Kruidenberg

# Needed variable declarations
grammarName = "QLGrammar"
pythonVersion = "Python3"
destinationFolder = "LexParser"

import sys
from antlr4 import *
from parse_grammar import main_parser

# Generate the lexer and parser for the grammar
main_parser(grammarName, pythonVersion, destinationFolder)

# Import the generated files
from visitor import Visitor
from LexParser.QLGrammarLexer import QLGrammarLexer
from LexParser.QLGrammarParser import QLGrammarParser


def main(argv):
	input = FileStream(argv[1])
	lexer = QLGrammarLexer(input)
	stream = CommonTokenStream(lexer)
	parser = QLGrammarParser(stream)
	tree = parser.form()
	# print(tree.toStringTree(recog=parser))
	print(parser.question().toStringTree(recog=parser))

	#pass tree to visitor
	visitor = Visitor()
	visitor.visit(tree)
	

if __name__ == '__main__':
	main(sys.argv)
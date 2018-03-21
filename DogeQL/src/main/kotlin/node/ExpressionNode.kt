package node

import common.Name
import data.question.Question
import data.question.SymbolType
import data.symbol.SymbolTable
import expression.SourceLocation
import typechecker.pass.CircularDependencyPass
import typechecker.pass.DuplicatePass
import typechecker.pass.ScopePass
import typechecker.pass.TypePass

class ExpressionNode(symbolTable: SymbolTable, val reference: Name, val sourceLocation: SourceLocation)
    : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {

        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            throw IllegalStateException("TODO")
        }

        val castedValue = symbol.value.castTo(SymbolType.BOOLEAN)
                ?: throw IllegalStateException("Unable to cast to boolean") // TODO: better exception

        if (castedValue.booleanValue.value) {
            return children.flatMap { child ->
                child.getEnabledQuestions()
            }
        }
        return listOf()
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }

    override fun accept(pass: DuplicatePass) {
        pass.visit(this)
    }

    override fun accept(pass: TypePass) {
        pass.visit(this)
    }

    override fun accept(pass: CircularDependencyPass) {
        pass.visit(this)
    }
}
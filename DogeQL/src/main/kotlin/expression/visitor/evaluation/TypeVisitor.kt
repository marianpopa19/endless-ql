package expression.visitor.evaluation

import data.question.SymbolType
import data.symbol.SymbolTable
import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor


class TypeVisitor(private val symbolTable: SymbolTable) : ExpressionVisitor<SymbolType> {

    override fun visit(literal: LiteralExpression): SymbolType {
        return literal.value.type
    }

    override fun visit(reference: ReferenceExpression): SymbolType {
        if (reference.type != SymbolType.UNDEFINED) {
            return reference.type
        }

        val symbol = symbolTable.findSymbol(reference.name)

        if (symbol == null) {
            throw IllegalStateException("No symbol registered for reference ${reference.name}")
        }

        if (symbol.expression == null) {
            return symbol.value.type
        } else {
            val resolvedType = symbol.expression.accept(this)

            reference.type = resolvedType

            return resolvedType
        }
    }

    override fun visit(unary: UnaryExpression): SymbolType {
        val nextResolvedType = unary.next.accept(this)

        if (nextResolvedType == SymbolType.UNDEFINED) {
            return SymbolType.UNDEFINED
        }

        return unary.operation.getResolveType(nextResolvedType)
    }

    override fun visit(binary: BinaryExpression): SymbolType {
        val leftResolvedType = binary.left.accept(this)
        val rightResolvedType = binary.right.accept(this)

        if (leftResolvedType == SymbolType.UNDEFINED || rightResolvedType == SymbolType.UNDEFINED) {
            return SymbolType.UNDEFINED
        }

        return binary.operation.getResolvedType(leftResolvedType, rightResolvedType)
    }

}
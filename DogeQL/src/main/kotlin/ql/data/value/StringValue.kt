package ql.data.value

import ql.data.symbol.SymbolType

class StringValue(var value: String) : BaseSymbolValue(SymbolType.STRING) {

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is StringValue -> StringValue(value + that.value)
        else -> super.plus(that)
    }

    override fun equals(other: Any?) = when (other) {
        is StringValue -> value == other.value
        else -> false
    }

}
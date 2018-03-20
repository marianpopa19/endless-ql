package data.symbol

sealed class SymbolRegistrationResult(val name: String) {
    class Registered(name: String, val symbol: Symbol) : SymbolRegistrationResult(name)
    class AlreadyRegistered(name: String) : SymbolRegistrationResult(name)
    class AlreadyRegisteredTypeMismatch(name: String, val previous: Symbol) : SymbolRegistrationResult(name)
}
class Type:

    def __init__(self, type_name="UnknownType"):
        self._type_name = type_name

    def accept(self, visitor):
        return visitor.visit(self)

    @property
    def type_name(self):
        return self._type_name

    def infer_type(self):
        return self.__class__

    def __repr__(self):
        return self.type_name

    def __str__(self):
        return self.__repr__()

    def __eq__(self, other):
        if not isinstance(other, Type):
            raise TypeError("Type {0} and {1} cannot be compared".format(self.infer_type(), other.__class__))
        if type(self) == type(other):
            return True
        return False


class String(Type):

    def __init__(self):
        super().__init__("String")


class Integer(Type):

    def __init__(self):
        super().__init__("Integer")


class Boolean(Type):

    def __init__(self):
        super().__init__("Boolean")


class Decimal(Type):

    def __init__(self):
        super().__init__("Decimal")


class Money(Type):

    def __init__(self):
        super().__init__("Money")

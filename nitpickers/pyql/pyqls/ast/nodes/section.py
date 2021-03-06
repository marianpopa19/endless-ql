from util.ast import ASTNode


class Section(ASTNode):

    def __init__(self, location, name, block):
        super().__init__(location)
        self._name = name
        self._block = block

    @property
    def name(self):
        return self._name

    @property
    def block(self):
        return self._block

    def __repr__(self):
        return "Section at {0}".format(self.location)

    __str__ = __repr__


class InlineSection(ASTNode):

    def __init__(self, location, name, question):
        super().__init__(location)
        self._name = name
        self._question = question

    @property
    def name(self):
        return self._name

    @property
    def question(self):
        return self._question

    def __repr__(self):
        return "Section at {0}".format(self.location)

    __str__ = __repr__

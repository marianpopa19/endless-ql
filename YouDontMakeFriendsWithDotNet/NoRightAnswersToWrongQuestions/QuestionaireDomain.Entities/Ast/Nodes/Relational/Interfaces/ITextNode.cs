﻿using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface ITextNode : IAstNode
    {
        string Value { get; }
    }
}
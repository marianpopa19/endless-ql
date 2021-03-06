﻿using Antlr4.Runtime;

namespace QLS.Api.Ast
{
    public sealed class WidgetOptionNode : Node
    {
        public WidgetOptionNode(IToken token, string option) : base(token)
        {
            Label = option;
        }

        protected override T VisitNode<T>(IVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public string Label { get; }
    }
}

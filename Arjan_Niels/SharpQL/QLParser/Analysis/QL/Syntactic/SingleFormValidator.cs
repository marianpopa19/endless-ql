﻿using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;

namespace QLParser.Analysis.QL.Syntactic
{
    public class SingleFormValidator : IQLAnalyser, IQLVisitor
    {
        private int formNodeCount;

        /// <summary>
        /// A recursive function to find out if the AST contains multiple FormNode
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public bool Analyse(QLNode node)
        {
            //Reset the formNodeCount
            formNodeCount = 0;
            if (node.Type == NodeType.Form)
                this.Visit(node as FormNode);
            else
                this.Visit(node);

            this.Visit(node);

            if (formNodeCount == 0)
                Analyser.AddMessage("There has no form been defined", Language.QL, MessageType.ERROR);
            else if (formNodeCount > 1)
                Analyser.AddMessage("There can only be one form", Language.QL, MessageType.ERROR);

            return formNodeCount == 1;
        }

        public void Visit(QuestionNode node)
        {
            return;
        }

        public void Visit(ComputedNode node)
        {
            return;
        }

        public void Visit(FormNode node)
        {
            formNodeCount++;
            VisitChildren(node);
        }

        public void Visit(QLNode node)
        {
            return;
        }

        public void Visit(ExpressionNode node)
        {
            return;
        }

        public void Visit(ConditionalNode node)
        {
            VisitChildren(node);
        }

        public void Visit(QLCollectionNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLCollectionNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }
    }
}
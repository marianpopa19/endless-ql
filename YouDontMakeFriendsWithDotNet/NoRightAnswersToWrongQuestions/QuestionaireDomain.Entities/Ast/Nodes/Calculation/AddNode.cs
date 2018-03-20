﻿using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class AddNode : 
        CalculationBinaryExpressionNodeBase, 
        IAddNode
    {
        public AddNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : 
            base(id, definition,leftCalculation,rightCalculation)
        {
        }
    }
}
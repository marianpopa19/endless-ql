﻿using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class UnknownTypeValidator : IUnknownTypeValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IVariableService m_variableService;
        private readonly ITypeService m_typeService;

        public UnknownTypeValidator(
            IDomainItemLocator domainItemLocator,
            IVariableService variableService,
            ITypeService typeService)
        {
            m_domainItemLocator = domainItemLocator;
            m_variableService = variableService;
            m_typeService = typeService;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var untypedOperators = GetUntypedOperators();

            foreach (var untypedOperator in untypedOperators)
            {
                var leftName = m_domainItemLocator
                    .Get<IUntypedVariableNode>(untypedOperator.LeftExpression.Id)
                    .VariableName;

                var rightName = m_domainItemLocator
                    .Get<IUntypedVariableNode>(untypedOperator.RightExpression.Id)
                    .VariableName;

                if (m_variableService.AreCompatible(leftName, rightName))
                {
                    var type = m_variableService.GetQuestionType(leftName);
                    if (!m_typeService.IsValidOperationForType(untypedOperator, type))
                    {
                        yield return IncompatableOperationError(untypedOperator, leftName, rightName);
                    }

                    continue;
                }

                yield return IncompatibleTypeError(leftName, rightName, untypedOperator);
            }
        }

        private ValidationMetaData IncompatibleTypeError(string leftName, string rightName, IRelationalLogicNode untypedOperator)
        {
            var leftType = m_variableService.GetQuestionType(leftName);
            var rightType = m_variableService.GetQuestionType(rightName);
            
            var leftTypeText = m_typeService.GetTypeDisplay(leftType);
            var rightTypeText = m_typeService.GetTypeDisplay(rightType);

            return new UnkownTypeExpressionValidationMetaData
            {
                Message =
                    $@"The expression '{untypedOperator.Definition}' contains the {leftTypeText} variable " +
                    $@"'{leftName}' and {rightTypeText} variable '{rightName}'. The types {leftTypeText} " +
                    $@"and {rightTypeText} cannot be compared.",

                Source = m_domainItemLocator.GetRef<IRelationalLogicNode>(untypedOperator.Id)
            };
        }

        private ValidationMetaData IncompatableOperationError( IRelationalLogicNode untypedOperator, string leftName, string rightName)
        {
            var type = m_variableService.GetQuestionType(leftName);
            var displayType = m_typeService.GetTypeDisplay(type);
            return new UnkownTypeExpressionValidationMetaData
            {
                Message =
                    $@"The expression '{untypedOperator.Definition}' contains the {displayType} variables " + 
                    $@"'{leftName}' and '{rightName}'. A {displayType} can only be compared for equality.",
                Source = m_domainItemLocator.GetRef<IRelationalLogicNode>(untypedOperator.Id)
            };
        }

        private IEnumerable<IRelationalLogicNode> GetUntypedOperators()
        {
            var untypedOperands = m_domainItemLocator
                .GetAll<IUntypedVariableNode>()
                .Select(x => x.Id);

            var untypedOperators = m_domainItemLocator
                .GetAll<IRelationalLogicNode>()
                .Where(x => untypedOperands.Contains(x.LeftExpression.Id));
            return untypedOperators;
        }
    }
}
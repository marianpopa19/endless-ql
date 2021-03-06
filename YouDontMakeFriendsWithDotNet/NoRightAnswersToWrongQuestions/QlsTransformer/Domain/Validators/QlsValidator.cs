﻿using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Validators.CorrectWidgetForDefaults;
using QlsTransformer.Domain.Validators.UnkownQuestion;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators
{
    public class StyleSheetTypeChecker : IStyleSheetTypeChecker
    {
        private readonly List<IStyleSheetValidator> m_validators
            = new List<IStyleSheetValidator>();

        public StyleSheetTypeChecker(
            IUnknownQuestionValidator unknownQuestionValidator,
            ICorrectWidgetValidator correctWidgetValidator)
        {
            m_validators.Add(unknownQuestionValidator);
            m_validators.Add(correctWidgetValidator);
        }

        public IList<ValidationMetaData> Results { get; set; }
            = new List<ValidationMetaData>();

        public bool Validate(DomainId<IStyleSheetRootNode> styleSheetRootNode)
        {
            foreach (var validator in m_validators)
                Results = Results
                    .Concat(validator.Validate(styleSheetRootNode))
                    .ToList();

            return Results.All(x => x.Severity != Severity.Error);
        }
    }

    public interface IStyleSheetTypeChecker
    {
        IList<ValidationMetaData> Results { get; set; }

        bool Validate(
            DomainId<IStyleSheetRootNode> styleSheetRootNode);
    }
}
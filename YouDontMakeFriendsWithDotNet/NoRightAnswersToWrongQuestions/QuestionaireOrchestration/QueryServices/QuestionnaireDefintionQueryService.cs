﻿using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireOrchestration.QueryServices
{
    internal sealed class QuestionnaireDefintionQueryService
        : ModelQueryServiceBase<QuestionnaireDefinitionModel>
    {
        public QuestionnaireDefintionQueryService(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public override IEnumerable<QuestionnaireDefinitionModel> GetAll()
        {
            return DomainItemLocator
                .GetAll<IQuestionnaireRootNode>()
                .Select(x => new QuestionnaireDefinitionModel(x.Id, x.DisplayName));
        }
    }
}
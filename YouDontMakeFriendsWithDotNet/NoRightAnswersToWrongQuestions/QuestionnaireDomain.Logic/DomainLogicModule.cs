﻿using Microsoft.Extensions.DependencyInjection;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireDomain.Logic
{
    public class DomainLogicModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IQuestionnaireModelCreator), typeof(QuestionnaireModelCreator));
            appRegistration.AddSingleton(typeof(IQuestionnaireAstCreator), typeof(QuestionnaireAstCreator));
            appRegistration.AddSingleton(typeof(IPredicateCreator), typeof(PredicateCreator));
            appRegistration.AddSingleton(typeof(IDomainItemLocator), typeof(DomainItemLocator));
            appRegistration.AddSingleton(typeof(IBuildOutputVisitor), typeof(BuildOutputVisitor));
            appRegistration.AddSingleton(typeof(IBooleanLogicVisitor), typeof(BooleanLogicVisitor));
            appRegistration.AddSingleton(typeof(ICalculationVisitor), typeof(CalculationVisitor));
            appRegistration.AddSingleton(typeof(IVariableUpdater), typeof(VariableUpdater));
        }
    }
}
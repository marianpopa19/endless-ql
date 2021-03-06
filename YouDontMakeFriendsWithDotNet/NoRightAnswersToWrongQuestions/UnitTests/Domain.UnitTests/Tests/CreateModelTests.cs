﻿using System;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QL.UnitTests.Domain.UnitTests.Data;
using QuestionnaireDomain.Entities;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace QL.UnitTests.Domain.UnitTests.Tests
{
    [TestFixture]
    public class CreateModelTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;
        private IQuestionnaireOutputCreator m_outputCreator;
        private IVariableUpdater m_variableUpdater;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            m_outputCreator = m_serviceProvider.GetService<IQuestionnaireOutputCreator>();
            m_variableUpdater = m_serviceProvider.GetService<IVariableUpdater>();
        }

        [TearDown]
        public void Cleanup()
        {
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
        }
        
        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.SimpleQuestionnaireCases))]
        public void GivenAValidForm_CreatesAQuestionnaireOutputWithSameName(
            string validDescription,
            string expectedQuestionnaireName)
        {
            CreateOutputForm(validDescription);

            var actualDisplayName = m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault()
                ?.DisplayName;

            Assert.AreEqual(
                expected: expectedQuestionnaireName,
                actual: actualDisplayName);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.QuestionTypes))]
        public void GivenAValidType_ReturnsQuestionOfCorrectType(
           string validDefinition,
           IQuestionType questionType) 
        {
            CreateOutputForm(validDefinition);
            var domainItem = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault();

            Assert.NotNull(domainItem);
            Assert.AreEqual(
                expected: questionType.GetType(), 
                actual: domainItem.QuestionType.GetType());
        }
        
        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.DefaultQuestionValues))]
        public void GivenAnUninitiatedValue_ReturnsDefaultValue(
            string validDefinition,
            string questionValue)
        {
            CreateOutputForm(validDefinition);
            var outputItem = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault();

            Assert.NotNull(outputItem);
            Assert.AreEqual(
                expected: questionValue,
                actual: outputItem.Value);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.IfQuestionValues))]
        public void GivenIfElseCondition_ReturnsCorrectVisibility(
            string validDefinition,
            int expectedVisible,
            int expectedInvisible)
        {
            CreateOutputForm(validDefinition);
            var actualVisibleCount = GetVisibleCount();
            var actualInvisibleCount = GetInvisibleCount();

            Assert.AreEqual(
                expected: expectedVisible,
                actual: actualVisibleCount);
            Assert.AreEqual(
                expected: expectedInvisible,
                actual: actualInvisibleCount);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationIntVariableTrueToFalseValues))]
        public void GivenIntPredicateVariableThatChangeFromTrueToFalse_ReturnsCorrectVisibility(
            string validDefinition,
            int newValueVariable1,
            int newValueVariable2)
        {
            CreateOutputForm(validDefinition);
            TrueToFalse(newValueVariable1, newValueVariable2);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationIntVariableFalseToTrueValues))]
        public void GivenIntPredicateThatChangeFromFalseToTrue_ReturnsCorrectVisibility(
            string validDefinition,
            int newValueVariable1,
            int newValueVariable2)
        {
            CreateOutputForm(validDefinition);
            FalseToTrue(newValueVariable1, newValueVariable2);
          }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationDecimalVariableTrueToFalseValues))]
        public void GivenDecimalPredicateVariableThatChangeFromTrueToFalse_ReturnsCorrectVisibility(
            string validDefinition,
            decimal newValueVariable1,
            decimal newValueVariable2)
        {
            CreateOutputForm(validDefinition);
            TrueToFalse(newValueVariable1, newValueVariable2);
        }

        private void TrueToFalse<T>(T newValueVariable1, T newValueVariable2)
        {
            ChangeVisibilityCheck(
                newValueVariable1, 
                newValueVariable2,
                3,2,4,1);
        }

        private void FalseToTrue<T>(T newValueVariable1, T newValueVariable2)
        {
            ChangeVisibilityCheck(
                newValueVariable1,
                newValueVariable2,
                4,1,3,2);
        }

        private void ChangeVisibilityCheck<T>(
            T newValueVariable1,
            T newValueVariable2,
            int expectedInitialVisibleCount,
            int expectedInitialInvisibleCount,
            int expectedNewVisibleCount,
            int expectedNewInvisibleCount)
        {
            var actualInitialVisibleCount = GetVisibleCount();
            var actualInitialInvisibleCount = GetInvisibleCount();

            UpdateVariable(@"q1", newValueVariable1);
            UpdateVariable(@"q2", newValueVariable2);

            var actualNewVisibleCount = GetVisibleCount();
            var actualNewInvisibleCount = GetInvisibleCount();

            Assert.AreEqual(
                expected: expectedInitialVisibleCount, 
                actual: actualInitialVisibleCount);
            Assert.AreEqual(
                expected: expectedInitialInvisibleCount, 
                actual: actualInitialInvisibleCount);
            Assert.AreEqual(
                expected: expectedNewVisibleCount, 
                actual: actualNewVisibleCount);
            Assert.AreEqual(
                expected: expectedNewInvisibleCount, 
                actual: actualNewInvisibleCount);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationDecimalVariableFalseToTrueValues))]
        public void GivenDecimalPredicateThatChangeFromFalseToTrue_ReturnsCorrectVisibility(
            string validDefinition,
            decimal newValueVariable1,
            decimal newValueVariable2)
        {
            CreateOutputForm(validDefinition);
            FalseToTrue(newValueVariable1, newValueVariable2);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculationDateVariableTrueToFalseValues))]
        public void GivenDatePredicateVariableThatChangeFromTrueToFalse_ReturnsCorrectVisibility(
            string validDefinition,
            DateTime newValueVariable1,
            DateTime newValueVariable2)
        {
            CreateOutputForm(validDefinition);
            TrueToFalse(newValueVariable1, newValueVariable2);
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.CalculatedValues))]
        public void GivenCalculatedValue_ReturnsCorrectReadonlyValue(
            string validDefinition,
            decimal expectedValue)
        {
            CreateOutputForm(validDefinition);
            var outputQuestion = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault(x => x.QuestionText == "\"x\"");

            Assert.IsTrue(outputQuestion.ReadOnly);
            Assert.AreEqual(
                expected: expectedValue,
                actual: decimal.Parse(outputQuestion.Value));
        }

        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.UpdateCalculatedValues))]
        public void GivenVariableValueChange_CalculatedValueIsUpdated(
            string validDefinition,
            int newValueVariable1,
            string expectedCalculatedValue)
        {
            CreateOutputForm(validDefinition);
            UpdateVariable(@"f1", newValueVariable1);
            var actualCalcualteValue = GetCalculatedValue("\"ctext1\"");
            Assert.AreEqual(
                expected: expectedCalculatedValue, 
                actual: actualCalcualteValue);
        }

        private string GetCalculatedValue(string questionText)
        {
            return m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .FirstOrDefault(x => x.QuestionText == questionText)
                ?.Value;
        }
        
        private int GetVisibleCount()
        {
            return GetVisibilityCount(x => x.Visible);
        }

        private int GetInvisibleCount()
        {
            return GetVisibilityCount(x => !x.Visible);
        }
        
        private int GetVisibilityCount(Func<IQuestionOutputItem, bool> predicate)
        {
            return m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Count(predicate);
        }
        
        private void UpdateVariable(string variableName, dynamic value)
        {
            var questionItem = GetQuestionByName(variableName);
            m_variableUpdater.Update(questionItem, value);
        }

        private DomainId<IQuestionNode> GetQuestionByName(string variableName)
        {
            var variableItem = m_domainItemLocator
                .GetAll<IVariableNode>()
                .FirstOrDefault(x => x.VariableName == variableName);

            var questionItem = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .Where(x => x.QuestionName == variableItem.VariableName)
                .Select(x => new DomainId<IQuestionNode>(x.Id))
                .FirstOrDefault();

            return questionItem;
        }

        private void CreateOutputForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireAstCreator>();

            questionnaireCreator.Create(validText);

            var questionnaireNodes = m_domainItemLocator
                .GetAll<IQuestionnaireRootNode>();

            foreach (var questionnaireRootNode in questionnaireNodes)
            {
                var questionnaireRef = new DomainId<IQuestionnaireRootNode>(questionnaireRootNode.Id);
                m_outputCreator.CreateOrUpdate(questionnaireRef);
            }
        }
    }
}
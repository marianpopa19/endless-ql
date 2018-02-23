﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;

namespace Assignment1
{
    internal class QLListener : QLBaseListener
    {
        public List<QuestionForm> Forms { get; private set; }
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();
        public List<string> Errors = new List<string>();
        public List<string> Warnings = new List<string>();
        public bool FormHasErrors
        {
            get
            {
                return Errors.Count > 0;
            }
        }

        private bool QuestionIdExists(string questionId)
        {
            return _questions.ContainsKey(questionId);
        }

        private bool QuestionLabelExists(string questionLabel)
        {
            List<Question> questionList = _questions.Values.ToList();
            foreach (Question questionItem in questionList)
            {
                if (questionItem.Label.Equals(questionLabel))
                    return true;
            }
            return false;
        }

        private void AddError(ParserRuleContext context, string message)
        {
            Errors.Add("Line " + context.Start.Line + ": " + message);
        }

        public override void ExitFile(QL.FileContext context)
        {
            Console.WriteLine(Errors.Count + " errors found.");
            foreach (string error in Errors)
            {
                Console.WriteLine(error);
            }
            Forms = context.result;
        }

        public override void EnterQuestion(QL.QuestionContext context)
        {
            string questionLabel = context.result.Label;

            // Should be warnings, move to separate list
            if (QuestionLabelExists(questionLabel))
            {
                AddError(context, "The question label '" + questionLabel + "' has already been used.");
            }
        }

        public override void ExitQuestion(QL.QuestionContext context)
        {
            string questionId = context.result.Id;
            
            if (QuestionIdExists(questionId))
            {
                AddError(context, "The question id '" + questionId + "' already exists in the current context.");
            } else
            {
                _questions.Add(context.result.Id, context.result);
            }
        }

        /* Check for each if statement if the expression in the condition is of type boolean.
         */
        public override void ExitIfstatement(QL.IfstatementContext context)
        {
            object conditionType = context.result.Expression.Evaluate();
            if (!(conditionType is bool))
            {
                AddError(context, "The expression '" + context._expression.GetText() + "' is not of type boolean.");
            }
        }

        /* Check for each expression if the left and right operands are of the correct type.
         * For example, for an arithmetic expression the left and right operands should be numeric.
         */
        public override void ExitExpression(QL.ExpressionContext context)
        {
            Expression expression = context.result;
            try
            {
                var expressionType = expression.Evaluate();
            } catch (Exception exception)
            {
                AddError(context, exception.Message);
            }
        }

        /* Check for each expressionId if the referenced questionId exists. Adds an error message
         * if this is not the case.
         */
        public override void ExitExpressionId(QL.ExpressionIdContext context)
        {
            try
            {
                context.result.Question = _questions[context.result.Id];
            }
            catch (KeyNotFoundException e)
            {
                AddError(context, "The question id '" + context.result.Id + "' does not exist in the current context.");
                //throw;
            }
        }
    }
}

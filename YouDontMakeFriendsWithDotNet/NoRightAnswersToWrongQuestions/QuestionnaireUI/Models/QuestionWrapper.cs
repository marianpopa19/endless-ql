﻿using System;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireUI.Models
{
    public class QuestionWrapper : ModelWrapperBase<QuestionModel>
    {
        public Guid QuestionOutputId => GetValue<Guid>();
        public Guid QuestionVariableId => GetValue<Guid>();
        public string QuestionText => GetValue<string>();
        public Type QuestionType => GetValue<Type>();
        public bool ReadOnly => GetValue<bool>();
        public bool Visible => GetValue<bool>();

        public dynamic Value
        {
            get { return GetValue<dynamic>(); }
            set
            {
                Model.Value = value;
                RaisePropertyChanged(nameof(Value));
            }
        }

        public QuestionWrapper(QuestionModel model) : base(model)
        {
        }
    }
}
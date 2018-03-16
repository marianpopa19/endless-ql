﻿using QLVisualizer.Elements;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetCreator<T>
    {
        public T CreateWidget(ElementManager elementManager, T holder)
        {
            if (elementManager as PageManager != null)
                RegisterPageTab(elementManager as PageManager);

            if (elementManager.Active)
            {
                switch (elementManager)
                {
                    case FormManager form: return CreateWidget(form, holder);
                    case PageManager page: return CreateWidget(page, holder);
                    case SectionManager section: return CreateWidget(section, holder);
                    case BoolQuestionManager boolQuestion: return CreateWidget(boolQuestion, holder);
                    case IntQuestionManager intQuestion: return CreateWidget(intQuestion, holder);
                    case MoneyQuestionManager moneyQuestion: return CreateWidget(moneyQuestion, holder);
                    case StringQuestionManager stringQuestion: return CreateWidget(stringQuestion, holder);
                }

                throw new NotImplementedException();
            }
            return CreateEmpty();
        }

        protected abstract T CreateEmpty();

        protected abstract void RegisterPageTab(PageManager page);

        protected abstract T CreateWidget(FormManager form, T holder);
        protected abstract T CreateWidget(PageManager page, T holder);
        protected abstract T CreateWidget(SectionManager section, T holder);

        protected abstract T CreateWidget(BoolQuestionManager boolQuestion, T holder);
        protected abstract T CreateWidget(IntQuestionManager intQuestion, T holder);
        protected abstract T CreateWidget(MoneyQuestionManager moneyQuestion, T holder);
        protected abstract T CreateWidget(StringQuestionManager stringQuestion, T holder);

    }
}

﻿using QLParser.AST.QL;
using QLParser.AST.QLS.Enums;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class StringBuilderWindows : WidgetLeafBuilderWindows<StringQuestionManager>
    {
        public StringBuilderWindows(StringQuestionManager elementManagerLeaf) : base(elementManagerLeaf)
        {
        }

        public override Control Create()
        {
            // Initialize inputcreator options
            WidgetType widgetType = WidgetType.Default;
            List<string> widgetOptions = new List<string>() { _elementManager.Text };

            if (_elementManager.GetStyle() != null)
            {
                widgetType = _elementManager.GetStyle().GetQLSWidgetSpecification().WidgetType;
                widgetOptions.AddRange(_elementManager.GetStyle().GetQLSWidgetSpecification().WidgetTypeArguments);
            }

            // Get inputcreator
            IInputCreator<Control, string> inputCreator = null;
            switch (widgetType)
            {
                case WidgetType.Default:
                case WidgetType.Textfield:
                    inputCreator = new TextBoxCreator<string>();
                    break;

                case WidgetType.Radio:
                    inputCreator = new RadioButtonCreator<string>();
                    break;

                case WidgetType.Checkbox:
                default:
                    throw new NotImplementedException();
            }

            // Return created input
            return inputCreator.CreateInput(_styler, widgetOptions.ToArray(), _elementManager as StringQuestionManager);
        }

        protected override QValueType GetQValueType()
        {
            return QValueType.Text;
        }
    }
}

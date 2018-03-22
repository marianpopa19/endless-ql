﻿using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class BoolBuilderWindows : WidgetLeafBuilderWindows<BoolQuestionManager>
    {
        public BoolBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, BoolQuestionManager elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, widgetSpecification, elementManagerLeaf, parent)
        {
        }

        public override Control Create()
        {
            throw new NotImplementedException();
        }
    }
}
